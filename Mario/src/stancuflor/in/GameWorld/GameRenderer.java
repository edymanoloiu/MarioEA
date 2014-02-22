package stancuflor.in.GameWorld;

import stancuflor.in.GameObjects.Grass;
import stancuflor.in.GameObjects.Mario;
import stancuflor.in.GameObjects.Road;
import stancuflor.in.Helpers.AssetLoader;
import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;

	private SpriteBatch batcher;
	private ShapeRenderer shapeRenderer;

	private float gameWidth;
	private float gameHeight;

	private boolean stopGame = false;
	private boolean startGame = true;

	private int score;
	private String scoreName;
	private String finalMes = "You Lose!!!";
	private String restart = "Touch the screen to restart the game !!";
	BitmapFont fontName;
	BitmapFont finalMessage;
	BitmapFont restartMessage;

	private Singleton settings;
	private boolean gameRestarted = false;
	private boolean jos = true;

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

		score++;
		scoreName = "Score: " + score;
		gameRestarted = false;

		if (settings.getRestartGame()) {
			score = 0;
			scoreName = "Score: " + score;
			settings.setRestartGame(false);
			settings.setRestart(false);
			stopGame = false;
			gameRestarted = true;
		}

		// Fill the entire screen with black, to prevent potential
		// flickering.
		Gdx.gl.glClearColor(0.4f, 0.5f, 0.9f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (mario.getPosition().y < 305)
			jos = false;
		else
			jos = true;

		// System.out.println(mario.getPosition().y + " " + jos);

		if (!stopGame) {
			// Begin SpriteBatch
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
				Vector2 MarioPos = myWorld.getMario().getPosition();
				for (Road block : myWorld.getRoad()) {
					if (block.getVisible())
						batcher.draw(AssetLoader.road, block.position.x,
								block.position.y, block.getWidth(),
								block.getHeight());
					else {
						if (400.0 - MarioPos.y <= 0
								&& MarioPos.x == block.position.x) {
							stopGame = true;
							System.out.println("Stop!!!" + block.getVisible()
									+ " " + MarioPos.x);
						}
					}
				}
			} else {
				for (Road block : myWorld.getRoad()) {
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
					if (290.0 - mario.getPosition().y <= 0) {
						System.out.println(mario.getPosition().x + " "
								+ block.position.x);
						if (mario.getPosition().x + 10 > block.position.x + 5 ) {
							if (!jos) {
								mario.setPosition(new Vector2(mario
										.getPosition().x, mario
										.getPosition().y + 105));
								jos = true;
							}
						}
					}
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

			// End SpriteBatch
			batcher.end();
		} else {
			batcher.begin();
			finalMessage.setScale((float) 2, (float) -2);
			finalMessage.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			finalMessage.draw(batcher, finalMes, gameWidth / 2 - 100,
					gameHeight / 2 - 50);
			restartMessage.setScale((float) 2, (float) -2);
			restartMessage.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			restartMessage.draw(batcher, restart, gameWidth / 2 - 250,
					gameHeight / 2);
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
