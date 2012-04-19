import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MultiPlayer implements GameState {
	Image player, map, esctest;
	
	boolean esc = false;
	
	float mod = (float) 0.3;
	
	float playerX=400, playerY=300;
	int mouseX, mouseY;
	int mapWidth=2560, mapHeight=1570;
	float viewBottomRightX, viewBottomRightY, viewTopLeftX, viewTopLeftY;
	
	Bullet bullet = null;
	// Array for storing bullets
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public MultiPlayer(){
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg)
			throws SlickException {

		viewBottomRightX=(mapWidth+1600)/2;
		viewBottomRightY=(mapHeight+1200)/2;
		viewTopLeftX=(mapWidth-1600)/2;
		viewTopLeftY=(mapHeight-1200)/2;
		
		System.out.println(-viewTopLeftX+viewBottomRightX);
		
        player = new Image("data/player.png");
        map = new Image("data/map.jpeg");
		//esctest = new Image("data/player.png");
        
        bullet = new Bullet(new Image("data/bullet.jpg"));
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics gfx)
			throws SlickException {
        map.draw(0, 0, viewTopLeftX, viewTopLeftY, viewBottomRightX, viewBottomRightY);
        player.drawCentered(playerX, playerY);
        //map.setAlpha(0);
		//esctest.drawCentered(10, 10);
        
        // Draws the bullet in the new position
	    for(int i = 0; i < bullets.size()-1; i++){
	    	bullets.get(i).getImage().draw(bullets.get(i).getX(), bullets.get(i).getY());
	    }
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sg, int delta)
			throws SlickException {
		
		Input input = gc.getInput();
		
		double r = 0;
		
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
        
        r = Math.atan2(mouseY-playerY, mouseX-playerX);
        player.setRotation((float) Math.toDegrees(r+(Math.PI/2)));
        
        // Will update the bullets so they go in the direction of the player
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).setX((float) (bullets.get(i).getDirectionX()));
            bullets.get(i).setY((float) (bullets.get(i).getDirectionY()));
        }
        
        if(input.isMousePressed(1)){
        	float hip = 0.4f * delta;
        	float rotation = player.getRotation();
        	bullets.add(new Bullet(new Image("data/bullet.jpg")));
        	int i = bullets.size()-1;
        	bullets.get(i).setX(playerX);
        	bullets.get(i).setY(playerY);
        	bullets.get(i).setDirectionX((float) (bullets.get(i).getX() + 
        			hip * Math.sin(Math.toRadians(rotation))));
        	bullets.get(i).setDirectionY((float) (bullets.get(i).getY() - 
        			hip * Math.cos(Math.toRadians(rotation))));
        }
		if(input.isKeyDown(Input.KEY_W))
        {
			if(viewTopLeftY>0 && playerY>300){
				playerY-=mod;
			}
			else if(viewTopLeftY>0){
				viewTopLeftY-=mod;
				viewBottomRightY-=mod;
			}
			else if(playerY>25){
        		playerY-=mod;
        	}
        }
        if(input.isKeyDown(Input.KEY_A))
        {
        	if(viewTopLeftX>0 && playerX>400){
				playerX-=mod;
			}
			else if(viewTopLeftX>0){
				viewTopLeftX-=mod;
				viewBottomRightX-=mod;
			}
			else if(playerX>25){
        		playerX-=mod;
        	}
        }
        if(input.isKeyDown(Input.KEY_S))
        {
        	if(viewTopLeftY<600 && playerY<300){
				playerY+=mod;
			}
			else if(viewTopLeftY<600){
				viewTopLeftY+=mod;
				viewBottomRightY+=mod;
			}
			else if(playerY<575){
        		playerY+=mod;
        	}
        }
        if(input.isKeyDown(Input.KEY_D))
        {
        	if(viewTopLeftX<800 && playerX<400){
				playerX+=mod;
			}
			else if(viewTopLeftX<800){
				viewTopLeftX+=mod;
				viewBottomRightX+=mod;
			}
			else if(playerX<775){
        		playerX+=mod;
        	}
        }
		
		/*if(input.isKeyDown(Input.KEY_ESCAPE)){
			if(!esc){
				esctest.setAlpha(1);
	        	esc = true;
	        	System.out.println("EscMenu Alpha:"+esctest.getAlpha());
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){}
			}
			else{
				esctest.setAlpha(0);
		        esc = false;
	        	System.out.println("EscMenu Alpha:"+esctest.getAlpha());
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){}
			}
		}*/
	}

	@Override
	public void keyPressed(int key, char c) {
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	//SPACING
	//SPACING
	//SPACING
	//SPACING
	//SPACING

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
