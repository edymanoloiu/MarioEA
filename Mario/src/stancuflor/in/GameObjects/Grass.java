package stancuflor.in.GameObjects;

import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.math.Vector2;

public class Grass {

	public Vector2 position;
	
	private int width;
	private int height;
	
	private Singleton settings;
	
	public Grass(float x, float y, int width, int height) {
		
		this.setWidth(width);
		this.setHeight(height);
		
		this.position = new Vector2(x, y);
		this.settings = Singleton.getInstance();
		
	}
	
	public void update(float delta) {
		
		float x = this.position.x;
		
		if (x < -width) {
			
			// il mut in spate
			this.position.x = this.settings.getGameWidth();
			
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
	
}
