import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main{
	
	public static void main (String args[]) throws SlickException{
		GameWithStates game = new GameWithStates("CQC - Close Quarters Combat");
		AppGameContainer app = new AppGameContainer(game);
		//app.setDisplayMode(app.getScreenWidth()-((int) (100*app.getAspectRatio())), app.getScreenHeight()-100, false);
		app.setDisplayMode(800, 600, false);
		
        //app.setTargetFrameRate(60);
		app.setShowFPS(true);
        
        app.start();
        game.enterState(0);
	}
	
}