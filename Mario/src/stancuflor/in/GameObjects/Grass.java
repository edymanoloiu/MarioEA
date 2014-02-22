package stancuflor.in.GameObjects;

import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.math.Vector2;

public class Grass {

	public Vector2 position;

	private int width;
	private int height;
	private boolean isVisible;

	public int nivel;

	private Singleton settings;

	public Grass(float x, float y, int width, int height, int nivel) {

		this.setWidth(width);
		this.setHeight(height);

		this.position = new Vector2(x, y);
		this.settings = Singleton.getInstance();

		isVisible = true;

		this.nivel = nivel;

	}

	public void update(float delta) {

		float x = this.position.x;

		if (settings.getGameOver())
			return;

		if (x < -width) {

			// il mut in spate
			this.position.x = this.settings.getGameWidth();
			int rand;
			if (nivel == 1) {
				rand = (int) (Math.random() * 8);
			} else {
				rand = (int) (Math.random() * 3);
			}

			if (rand == 0)
				isVisible = false;
			else
				isVisible = true;
		} else {

			// il translatez un pic la stanga

			this.position.add(-3, 0);

		}

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean getVisible() {
		return isVisible;
	}

}
