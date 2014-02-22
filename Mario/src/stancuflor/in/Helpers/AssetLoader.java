package stancuflor.in.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture;
	
    public static Animation marioAnimation;
    public static TextureRegion road;
    public static TextureRegion grass;
	
    public static void load() {
    	
    	texture = new Texture(Gdx.files.internal("data/texture.png"));
    	texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	
    	TextureRegion[] mario = new TextureRegion[8];
    	
    	for (int i = 0; i < 8; i++) {
    		
    		mario[i] = new TextureRegion(texture, i * 40, 0, 40, 40);
    		mario[i].flip(false, true);

    		marioAnimation = new Animation(0.06f, mario);
    		marioAnimation.setPlayMode(Animation.LOOP_PINGPONG);
    		
    	}
    	
    	road = new TextureRegion(texture, 8 * 40, 0, 40, 40);
    	road.flip(false, true);
    	
    	grass = new TextureRegion(texture, 9 * 40, 0, 51, 40);
    	grass.flip(false, true);
    	
    }
    
    public static void dispose() {
    	
        texture.dispose();
        
    }
    
}
