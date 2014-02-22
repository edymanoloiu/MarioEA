package stancuflor.in.Helpers;

import stancuflor.in.GameObjects.Mario;
import stancuflor.in.Screens.Singleton;

import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

	private Mario mario;
	private Singleton settings;
	
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
		
		mario.onClick();
		if(settings.getRestart()){
			System.out.println(settings.getRestart() + " " + settings.getRestartGame());
			settings.setRestartGame(true);
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
		return false;
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
