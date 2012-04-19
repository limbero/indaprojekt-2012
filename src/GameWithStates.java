import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.GameState;


public class GameWithStates extends StateBasedGame {
	MainMenu mainMenu;
	MultiPlayer multiPlayerGame = new MultiPlayer();
	SettingsMenu settings;
	SinglePlayer singlePlayerGame;
	
	public GameWithStates(String name){
		super(name);
	}
	
	public void initStatesList(GameContainer game){
		//addState(mainMenu);
		addState(multiPlayerGame);
		//addState(settings);	
		//addState(singlePlayerGame);	
	}
}
