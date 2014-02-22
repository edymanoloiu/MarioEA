package stancuflor.in.GameObjects;

import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.math.Vector2;

public class Teava {

	public Vector2 position;
	
	public int width;
	public int height;
	
	private Singleton settings;
	
	public Teava(float x, float y, int width, int height) {
		
		this.width = width;
		this.height = height;
		this.position = new Vector2(x, y);
		
		settings = Singleton.getInstance();
		
	}
	
	public void update(float delta) {
		
		this.position.x -= 5;
		
		if(settings.getGameOver())
			return;
		
		if (this.position.x < -40)
			this.position.x = 850;
		
	}
	
}
