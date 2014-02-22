package stancuflor.in.Mario;

import stancuflor.in.Helpers.AssetLoader;
import stancuflor.in.Screens.GameScreen;

import com.badlogic.gdx.Game;

public class MarioGame extends Game {

	@Override
	public void create() {

		AssetLoader.load();
		setScreen(new GameScreen());
		
	}
	
    @Override
    public void dispose() {
    	
        super.dispose();
        AssetLoader.dispose();
        
    }

}
