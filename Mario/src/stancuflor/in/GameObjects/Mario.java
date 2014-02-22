package stancuflor.in.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Mario {

	private Vector2 position;
	
	private int width;
	private int height;
	
	private boolean sare;
	private float cat_sare;
	private static float cat_poate_sa_sara_maxim = 150;
	private int urca_coboara;
	
	public Mario(float x, float y, int width, int height) {
		
		this.setWidth(width);
		this.setHeight(height);
		
		this.setPosition(new Vector2(x, y));
		
		this.sare = false;
		this.cat_sare = 0;
		this.urca_coboara = 1;
		
	}
	
	public void update(float delta) {
		
		if (sare) {
			
			if (cat_sare > 100) {
				sare = !sare;
				urca_coboara = 1;
				cat_sare = 0;
				return;
			}
			
			cat_sare += urca_coboara * 3;
			
			if (cat_sare > cat_poate_sa_sara_maxim) {
			
				urca_coboara = -1;
			
			}
			
			if (cat_sare <= 0) {
			
				sare = !sare;
				urca_coboara = 1;
			
			}
				
			this.position.add(0, -urca_coboara * 3);
		}
		
		//System.out.println(this.position);
		
	}

	public void onClick() {
		
		if (!sare)
			sare = !sare;
		
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

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
