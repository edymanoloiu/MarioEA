package stancuflor.in.GameObjects;

import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.math.Vector2;

public class Road {

	public Vector2 position;
	
	private int width;
	private int height;
	private boolean isVisible;
	
	private Singleton settings;
	
	public Road(float x, float y, int width, int height) {
		
		this.setWidth(width);
		this.setHeight(height);
		this.isVisible = true;
		
		this.position = new Vector2(x, y);
		this.settings = Singleton.getInstance();
		
	}
	
	public void update(float delta) {
		
		float x = this.position.x;
		
		if (x < -width + 10) {
			
			// il mut in spate
			this.position.x = (int)(this.settings.getGameWidth() / 40) * 40;
			int rand = (int) (Math.random()*10);
			if(rand == 0)
				isVisible = false;
			else
				isVisible = true;
		} else {
			
			// il translatez un pic la stanga
			
			this.position.add(-5, 0);
			
		}
		
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean getVisible() {
		return isVisible;
	}
	
}
