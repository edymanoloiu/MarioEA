package stancuflor.in.Screens;

public class Singleton {

	private static Singleton instance = new Singleton();

    private float gameWidth;
    private float gameHeight;
    private boolean restart;
    private boolean restartGame;
    private boolean gameOver;
    
    public Singleton() {}
    
	public Singleton(float gameWidth, float gameHeight) {

		Singleton.getInstance().setGameHeight(gameHeight);
		Singleton.getInstance().setGameWidth(gameWidth);
		restart = false;
		restartGame = false;
		gameOver = false;
	}

	public static Singleton getInstance() {
		return instance;
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

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public boolean getRestart(){
		return restart;
	}

	public void setRestartGame(boolean restartGame) {
		this.restartGame = restartGame;
	}

	public boolean getRestartGame(){
		return restartGame;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean getGameOver(){
		return gameOver;
	}

}