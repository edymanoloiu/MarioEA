package stancuflor.in.GameWorld;

import stancuflor.in.GameObjects.Grass;
import stancuflor.in.GameObjects.Mario;
import stancuflor.in.GameObjects.Road;
import stancuflor.in.Helpers.AssetLoader;
import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;

	private SpriteBatch batcher;
	private ShapeRenderer shapeRenderer;

	private float gameWidth;
	private float gameHeight;

	private boolean stopGame = false;

	private int score;
	private String scoreName;
	private String finalMes = "You Lose!!!";
	private String restart = "Touch the screen to restart the game !!";
	BitmapFont fontName;
	BitmapFont finalMessage;
	BitmapFont restartMessage;

	private Singleton settings;
	private boolean gameRestarted = false;
	private boolean stopedByGoku = false;
	private boolean stopedByTeava = false;

	public GameRenderer(GameWorld world) {

		setMyWorld(world);

		this.gameHeight = world.getGameHeight();
		this.gameWidth = world.getGameWidth();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 800, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		score = 0;
		scoreName = "Score: 0";
		fontName = new BitmapFont();
		finalMessage = new BitmapFont();
		restartMessage = new BitmapFont();

		settings = Singleton.getInstance();
	}

	public void render(float runTime) {

		Mario mario = myWorld.getMario();

		scoreName = "Score: " + score;
		gameRestarted = false;

		if (settings.getRestartGame()) {
			score = 0;
			scoreName = "Score: " + score;
			settings.setRestartGame(false);
			settings.setRestart(false);
			stopGame = false;
			gameRestarted = true;
			stopedByGoku = false;
			stopedByTeava = false;
		}
		System.out.println(myWorld.goku.position.x);
		if ((myWorld.goku.position.x - mario.getPosition().x < 0)
				&& ((mario.getPosition().y > 255) && (mario.getPosition().y < 295))) {
			stopGame = true;
			stopedByGoku = true;
		}
		if ((mario.getPosition().x + 40 == myWorld.teava.position.x)
				&& ((mario.getPosition().y > 365) && (mario.getPosition().y < 405))) {
			stopGame = true;
			stopedByTeava = true;
		}

		// Fill the entire screen with black, to prevent potential
		// flickering.
		Gdx.gl.glClearColor(0.4f, 0.5f, 0.9f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (!stopGame) {
			// Begin SpriteBatch
			score++;
			batcher.begin();

			fontName.setScale((float) 1.5, (float) -1.5);
			fontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			fontName.draw(batcher, scoreName, gameWidth - 180, 100);

			// Disable transparency
			// This is good for performance when drawing images that do not
			// require
			// transparency.
			batcher.disableBlending();

			if (!gameRestarted) {
				for (Road block : myWorld.getRoad()) {
					if (block.getVisible())
						batcher.draw(AssetLoader.road, block.position.x,
								block.position.y, block.getWidth(),
								block.getHeight());
					else {

						if ((mario.getPosition().x > block.position.x)
								&& ((mario.getPosition().y > 365) && (mario
										.getPosition().y < 405)))
							stopGame = true;
					}
				}
			} else {
				for (Road block : myWorld.getRoad()) {
					block.setVisible(true);
					batcher.draw(AssetLoader.road, block.position.x,
							block.position.y, block.getWidth(),
							block.getHeight());
				}
			}

			for (Grass block : myWorld.grass) {
				if (block.getVisible())
					batcher.draw(AssetLoader.grass, block.position.x,
							block.position.y, block.getWidth(),
							block.getHeight());
				else {

					if ((mario.getPosition().x > block.position.x)
							&& ((mario.getPosition().y > 255) && (mario
									.getPosition().y < 295))
							&& (block.nivel == 1))
						mario.onClick(-1);

					if ((mario.getPosition().x > block.position.x)
							&& ((mario.getPosition().y > 135) && (mario
									.getPosition().y < 185))
							&& (block.nivel == 2))
						mario.onClick(-1);

				}
			}

			// The bird needs transparency, so we enable that again.
			batcher.enableBlending();

			// Draw bird at its coordinates. Retrieve the Animation object
			// from
			// AssetLoader
			// Pass in the runTime variable to get the current frame.
			batcher.draw(AssetLoader.marioAnimation.getKeyFrame(runTime),
					mario.getPosition().x, mario.getPosition().y,
					mario.getWidth(), mario.getHeight());

			batcher.draw(AssetLoader.gokuAnimation.getKeyFrame(runTime),
					myWorld.goku.position.x, myWorld.goku.position.y,
					myWorld.goku.width, myWorld.goku.height);

			batcher.draw(AssetLoader.teava, myWorld.teava.position.x,
					myWorld.teava.position.y, myWorld.teava.width,
					myWorld.teava.height);

			// End SpriteBatch
			batcher.end();
		} else {
			FileHandle file = Gdx.files.local("highscore.in");
			if (file.exists()) {
				String text = file.readString();
				int highScore = Integer.parseInt(text);
				if (score > highScore) {
					file.writeString(score + "", false);
					finalMes = "New High Score : " + score + " !!!!";
				}
			} else {
				file.writeString(score + "", false);
				finalMes = "New High Score : " + score + " !!!!";
			}
			batcher.begin();
			settings.setGameOver(true);
			for (Road block : myWorld.getRoad())
				if (block.getVisible())
					batcher.draw(AssetLoader.road, block.position.x,
							block.position.y, block.getWidth(),
							block.getHeight());
			for (Grass block : myWorld.grass)
				if (block.getVisible())
					batcher.draw(AssetLoader.grass, block.position.x,
							block.position.y, block.getWidth(),
							block.getHeight());
			finalMessage.setScale((float) 2, (float) -2);
			finalMessage.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			finalMessage.draw(batcher, finalMes, gameWidth / 2 - 100,
					gameHeight / 2 - 100);
			restartMessage.setScale((float) 2, (float) -2);
			restartMessage.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			restartMessage.draw(batcher, restart, gameWidth / 2 - 250,
					gameHeight / 2 - 50);
			if (stopedByGoku) {
				batcher.draw(AssetLoader.gokuAnimation.getKeyFrame(runTime),
						mario.getPosition().x + 40, myWorld.goku.position.y,
						myWorld.goku.width, myWorld.goku.height);
				batcher.draw(AssetLoader.marioAnimation.getKeyFrame(runTime),
						mario.getPosition().x, mario.getPosition().y,
						mario.getWidth(), mario.getHeight());
			} else if (stopedByTeava) {
				batcher.draw(AssetLoader.marioAnimation.getKeyFrame(runTime),
						mario.getPosition().x, mario.getPosition().y,
						mario.getWidth(), mario.getHeight());
				batcher.draw(AssetLoader.teava, mario.getPosition().x + 40,
						myWorld.teava.position.y, myWorld.teava.width,
						myWorld.teava.height);
			} else {
				batcher.draw(AssetLoader.marioAnimation.getKeyFrame(runTime),
						mario.getPosition().x, mario.getPosition().y + 30,
						mario.getWidth(), mario.getHeight());
			}
			settings.setRestart(true);
			batcher.end();
		}

	}

	public GameWorld getMyWorld() {
		return myWorld;
	}

	public void setMyWorld(GameWorld myWorld) {
		this.myWorld = myWorld;
	}

}
