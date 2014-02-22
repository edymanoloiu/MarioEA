package stancuflor.in.Helpers;

import stancuflor.in.GameObjects.Mario;
import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

	private Mario mario;
	private Singleton settings;
	
	private int x, y;
	
	public InputHandler(Mario mario) {
		
		this.mario = mario;
		settings = Singleton.getInstance();
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		//mario.onClick();
		
		this.x = screenX;
		this.y = screenY;
		
		if(settings.getRestart()){
			System.out.println(settings.getRestart() + " " + settings.getRestartGame());
			settings.setRestartGame(true);
			settings.setGameOver(false);
		}
		return true;
		
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		
		if (y > screenY) {
			// am tras in sus
			mario.onClick(1);
			return true;
		} else {
			// am tras in jos
			mario.onClick(-1);
			return true;
		}
		
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
