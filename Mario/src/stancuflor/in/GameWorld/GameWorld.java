package stancuflor.in.GameWorld;

import java.util.ArrayList;

import stancuflor.in.GameObjects.Grass;
import stancuflor.in.GameObjects.Mario;
import stancuflor.in.GameObjects.Goku;
import stancuflor.in.GameObjects.Road;
import stancuflor.in.GameObjects.Teava;

public class GameWorld {

	private Mario mario;
	public Goku goku;
	private ArrayList<Road> road;
	public ArrayList<Grass> grass;
	public Teava teava;
	
    private float gameWidth;
    private float gameHeight;
	
	public GameWorld(float gameWidth, float gameHeight) {
		
        this.setGameHeight(gameHeight);
        this.setGameWidth(gameWidth);
		
		setMario(new Mario(20, this.gameHeight - 80, 40, 40));
		
		this.goku = new Goku(700, gameHeight - 150 - 46, 30, 46);
		this.teava = new Teava(600, gameHeight - 100, 40, 60);
		
		this.road = new ArrayList<Road>();
		for (int i = 0; i <= gameWidth / 40; i++) {
			road.add(new Road(i * 40, gameHeight - 40, 40, 40));
		}
		
		this.grass = new ArrayList<Grass>();
		for (int i = 0; i <= (int)(gameWidth / 51) + 1; i++) {
			grass.add(new Grass(i * 51, gameHeight - 150, 51, 40, 1));
		}
		
		for (int i = 0; i <= (int)(gameWidth / 51) + 1; i++) {
			grass.add(new Grass(i * 51, gameHeight - 260, 51, 40, 2));
		}
		
	}
	
	public void update(float delta) {
		
		mario.update(delta);
		goku.update(delta);
		teava.update(delta);
		
		for (Road block : road)
			block.update(delta);
		
		for (Grass block : grass)
			block.update(delta);
	}

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}

	public float getGameWidth() {
		return gameWidth;
	}

	public void setGameWidth(float gameWidth) {
		this.gameWidth = gameWidth;
	}

	public float getGameHeight() {
		return gameHeight;
	}

	public void setGameHeight(float gameHeight) {
		this.gameHeight = gameHeight;
	}
	
	public ArrayList<Road> getRoad() {
		return this.road;
	}
	
}
