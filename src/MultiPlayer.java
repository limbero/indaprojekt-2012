import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

public class MultiPlayer implements GameState {

	// Creates a map and a camera to use the entire game
	TiledMap map;
	Camera camera;
	// Map dimensions of the tiled map
	int mapWidth, mapHeight;
	// The position of the screen/camera/window in the "game world"
	float screenX, screenY;
	
	// An array to store players
	ArrayList<Player> players;

	// Stores the old x and y of the player
	float oldX, oldY;

	//NetworkServer netserv;
	//NetworkClient netcli;

	// Mod is the speed of the player
	float mod = (float) 3;
	// The cursors coordinates
	float mouseX, mouseY;

	// Array for storing bullets, will take new bullets from
	// server is necessary,
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	/**
	 * The constructor of the MultiPlayer class.
	 */
	public MultiPlayer(){
	}

	/**
	 * This method will be run before the game loop
	 * is initiated.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sg)
			throws SlickException {
		/*try{
			netserv = new NetworkServer();
		}catch (IOException e){}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}

		System.out.println("server uppe");

		netcli = new NetworkClient("localhost", p1, p1);

		System.out.println("klient inledd");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}

		netcli.start();

		System.out.println("klient startad");*/

		// Gives all the variables their initial values.
		map = new TiledMap("data/untitled.tmx");
		camera = new Camera();
		mapWidth = map.getTileWidth() * map.getWidth();
		mapHeight = map.getTileHeight() * map.getHeight();

		players = new ArrayList<Player>();

		players.add(new Player("player1"));

		players.get(0).setX(500);
		players.get(0).setY(300);

		//bullet = new Bullet(new Image("data/bullet.jpg"));
		//time=0;
	}

	
	/**
	 * Will draw the graphics changed in update on the screen.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics gx)
			throws SlickException {
		// From now till pop every coordinate will transform the same
		gx.pushTransform();
		// Stores the view like an rectangle
		Rectangle view = camera.getView();
		// This translation will be applied on everything
		gx.translate(-players.get(0).getX() + view.getWidth()/2,
				-players.get(0).getY() + view.getHeight()/2);
		
		// Draws the bullets in the new position
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).getImage().drawCentered(bullets.get(i).getPosition().getX(), 
					bullets.get(i).getPosition().getY());
		}
		
		// Draws the players in their new position
		for(int i = 0; i < players.size(); i++){
			players.get(i).getImage().drawCentered(players.get(i).getX(), players.get(i).getY());
		}

		// Centers the area around the player
		camera.centerArea(gx, players.get(0));

		//map.render((int) view.getX(), (int) view.getY(), (int) view.getX()/map.getTileWidth(), (int) view.getY()/map.getTileHeight(),
		//	(int) view.getWidth()/map.getTileWidth(), (int) view.getHeight()/map.getTileHeight());
		
		// Render the entire tiled map
		map.render(0, 0);

		// Applyes the transformation on everything
		gx.popTransform();
	}

	/**
	 * This will apply changes to every object that needs
	 * changing.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sg, int delta)
			throws SlickException {

		// This will get the input from the user
		Input input = gc.getInput();

		double r = 0;

		// Stores the players old coordinates for collision
		oldX = players.get(0).getX();
		oldY = players.get(0).getY();

		// Stores where the screen currently are
		screenX = camera.getView().getX();
		screenY = camera.getView().getY();

		// Stores the cursor position
		mouseX = input.getMouseX() + screenX;
		mouseY = input.getMouseY() + screenY;

		// The rotation to apply to the player according to the cursor
		r = Math.atan2(mouseY-players.get(0).getY(), mouseX-players.get(0).getX());
		players.get(0).getImage().setRotation((float) Math.toDegrees(r+(Math.PI/2)));

		// Will update the bullets so they go in the direction of the player
		// Destroys the bullets outside the screen
		for(int i = 0; i < bullets.size(); i++){
			// This will store the effect of the tide map
			int tileID = map.getTileId((int) bullets.get(i).getPosition().getX()/map.getTileWidth(), 
					(int) bullets.get(i).getPosition().getY()/map.getTileHeight(), 0);

			// Sets the new positions of the bullets
			bullets.get(i).getPosition().add(bullets.get(i).getDirection());
			// remove bullets at collision
			if(bullets.get(i).getPosition().getX() < 0){
				bullets.remove(i);
			}
			else if(bullets.get(i).getPosition().getY() < 0){
				bullets.remove(i);
			}
			else if(bullets.get(i).getPosition().getX() >  map.getWidth() * map.getTileWidth()){
				bullets.remove(i);
			}
			else if(bullets.get(i).getPosition().getY() > map.getHeight() * map.getTileHeight()){
				bullets.remove(i);
			}
			else if(tileID == 1){
				bullets.remove(i);
			}else{
				for(int j = 0; j < players.size(); j++){
					if(players.get(j).checkCollision(bullets.get(i).getPosition().getX(), 
							bullets.get(i).getPosition().getY())){
					players.get(j).die();
					}
				}
			}
		}

		// Creates a bullet with direction of the player
		if(input.isMousePressed(0)){
			if(bullets.size()<100){
				float rotation = players.get(0).getImage().getRotation();
				bullets.add(new Bullet(new Image("data/bullet.jpg")));
				int i = bullets.size()-1;
				bullets.get(i).getImage().rotate(rotation);
				Vector2f direction = new Vector2f();
				Vector2f position = new Vector2f();
				direction.set(0, -1);
				direction.sub(-rotation);
				direction.normalise();
				position.set(players.get(0).getX(), players.get(0).getY());
				position.add(direction.copy().scale(players.get(0).getRadius()+1));
				direction.scale(20);
				bullets.get(i).setPosition(position);
				bullets.get(i).setDirection(direction);
			}
		}

		// This will apply changes to the player
		if(input.isKeyDown(Input.KEY_W)){
			players.get(0).setY(players.get(0).getY()-2*mod);
		}
		if(input.isKeyDown(Input.KEY_A)){
			players.get(0).setX(players.get(0).getX()-2*mod);
		}
		if(input.isKeyDown(Input.KEY_S)){
			players.get(0).setY(players.get(0).getY()+2*mod);
		}
		if(input.isKeyDown(Input.KEY_D)){
			players.get(0).setX(players.get(0).getX()+2*mod);
		}

		if(players.get(0).getX() < 0){
			players.get(0).setX(0);
		}
		if(players.get(0).getY() < 0){
			players.get(0).setY(0);
		}
		if(players.get(0).getX() >  mapWidth){
			players.get(0).setX(mapWidth-1);
		}
		if(players.get(0).getY() > mapHeight){
			players.get(0).setY(mapHeight-1);
		}
		// Handles player collision
		float playerRadius = players.get(0).getRadius();
		int tileID = map.getTileId((int) (players.get(0).getX() - playerRadius)/map.getTileWidth(), 
				(int) (players.get(0).getY() - playerRadius)/map.getTileHeight(), 0);
		if(tileID == 1){
			players.get(0).setX(oldX);
			players.get(0).setY(oldY);
		}
		tileID = map.getTileId((int) (players.get(0).getX() + playerRadius)/map.getTileWidth(), 
				(int) (players.get(0).getY() + playerRadius)/map.getTileHeight(), 0);
		if(tileID == 1){
			players.get(0).setX(oldX);
			players.get(0).setY(oldY);
		}
		tileID = map.getTileId((int) (players.get(0).getX() + playerRadius)/map.getTileWidth(), 
				(int) (players.get(0).getY() - playerRadius)/map.getTileHeight(), 0);
		if(tileID == 1){
			players.get(0).setX(oldX);
			players.get(0).setY(oldY);
		}
		tileID = map.getTileId((int) (players.get(0).getX() - playerRadius)/map.getTileWidth(), 
				(int) (players.get(0).getY() + playerRadius)/map.getTileHeight(), 0);
		if(tileID == 1){
			players.get(0).setX(oldX);
			players.get(0).setY(oldY);
		}
	}

	//SPACING
	//SPACING
	//SPACING
	//SPACING
	//SPACING

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
