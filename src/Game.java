import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.Dimension;
 
public class Game extends BasicGame{
	Dimension screenSize;
	
    Image plane = null;
    Image land = null;
    
    static float x = 400;
    static float y = 300;
    
    static int sizeX = 800;
    static int sizeY = 600;
    
    boolean start = true;
    float scale = 1;
    float[] oldMouseCoords = {x+1, y/2};
    float[] oldPlayerCoords = {x, y};
 
    public Game()
    {
    	super("CQC - Close Quarters Combat");
    	
    	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	sizeX = (int) screenSize.getWidth();
        sizeY = (int) screenSize.getHeight();
        
    }
 
    @Override
    public void init(GameContainer gc)
			throws SlickException {
        plane = new Image("data/player.png");
        land = new Image("data/land.jpg");
    }
 
    @Override
    public void update(GameContainer gc, int delta)
			throws SlickException
    {
        float r = 0;
    	
        Input input = gc.getInput();
        float mX, mY;
        mX = input.getMouseX();
        mY = input.getMouseY();
        r = (float) (Math.atan((mY-y)/(mX-x))-(Math.atan((oldMouseCoords[1] - oldPlayerCoords[1])/(oldMouseCoords[0]-oldPlayerCoords[0]))));
        
        //r = (float) (Math.atan2((mX-x)-(oldMouseCoords[1] - oldPlayerCoords[1]), (mY-y)-(oldMouseCoords[0]-oldPlayerCoords[0])));
        
        if( ((mX > x && oldMouseCoords[0] < x) || (mX < x && oldMouseCoords[0] > x)) ){
        	r+=Math.PI;
        }
        
        if(mX==x || oldMouseCoords[0]==x || mX==oldPlayerCoords[0] || oldMouseCoords[0]==oldPlayerCoords[0]){
        	r+=Math.PI;
        }
        
        
        if( ((x > mX && oldPlayerCoords[0] < mX) || (x < mX && oldPlayerCoords[0] > mX)) ){
        	r+=Math.PI;
        }
        
        oldMouseCoords[0] = mX;
        oldMouseCoords[1] = mY;
        oldPlayerCoords[0] = x;
        oldPlayerCoords[1] = y;
        
        plane.rotate((float) (Math.toDegrees(r)));
        
        if(input.isKeyDown(Input.KEY_W))
        {
        	if(y>0){
        		y-=0.5;
        	}
        }
        if(input.isKeyDown(Input.KEY_A))
        {
        	if(x>0){
        		x-=0.5;
        	}
        }
        if(input.isKeyDown(Input.KEY_S))
        {
        	if(y<sizeY-50){
        		y+=0.5;
        	}
        }
        if(input.isKeyDown(Input.KEY_D))
        {
        	if(x<sizeX-50){
        		x+=0.5;
        	}
        }
        
//      if(input.isKeyDown(Input.KEY_A))
//      {
//          plane.rotate(-0.2f * delta);
//      }
//
//      if(input.isKeyDown(Input.KEY_D))
//      {
//          plane.rotate(0.2f * delta);
//      }
// 
//        if(input.isKeyDown(Input.KEY_2))
//        {
//            scale += (scale >= 5.0f) ? 0 : 0.1f;
//            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
//        }
//        if(input.isKeyDown(Input.KEY_1))
//        {
//            scale -= (scale <= 1.0f) ? 0 : 0.1f;
//            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
//        }
    }
 
    public void render(GameContainer gc, Graphics g)
			throws SlickException
    {
        land.draw(0, 0);
 
        plane.draw(x, y, scale);
 
    }
 
    public static void main(String[] args)
			throws SlickException
    {
         AppGameContainer app =
			new AppGameContainer( new Game() );
 
         app.setDisplayMode(sizeX, sizeY, false);
         x = sizeX/2;
         y = sizeY/2;
         //app.setTargetFrameRate(60);
         app.setFullscreen(true);
         app.start();
         
    }
}