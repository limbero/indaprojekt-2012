import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main{
	static final int gameWith = 1000;
	static final int gameHeight = 700;
		
	public static void main (String args[]) throws SlickException{
		
		GameWithStates game = new GameWithStates("CQC - Close Quarters Combat");
		AppGameContainer app = new AppGameContainer(game);
		//app.setDisplayMode(app.getScreenWidth()-((int) (100*app.getAspectRatio())), app.getScreenHeight()-100, false);
		app.setDisplayMode(gameWith, gameHeight, false);
		
        app.setTargetFrameRate(60);
		app.setShowFPS(true);
        
        app.start();
        game.enterState(0);
	}
	
}