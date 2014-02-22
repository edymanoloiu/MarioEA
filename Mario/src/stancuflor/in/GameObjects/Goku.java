package stancuflor.in.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Goku {

	public Vector2 position;
	
	public int width;
	public int height;
	
	public Goku(float x, float y, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		this.position = new Vector2(x, y);
		
	}
	
	public void update(float delta) {
		
		this.position.x -= 3;
		
		if (this.position.x < -40)
			this.position.x = 850;
		
	}
	
}
