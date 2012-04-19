/**
 * A class that will create a simple bullet
 * with an image, speed and coordinates.
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet {
	private int speed;
	private float x;
	private float y;
	private Image image;
	private float directionX;
	private float directionY;

	public Bullet(Image image){
		this.image = image;
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}

	public Image getImage(){
		return image;
	}

	public int speed(){
		return speed;
	}

	public void setX(float x){
		this.x = x;
	}

	public void setY(float y){
		this.y = y;
	}
	
	public void setDirectionX(float direction){
		this.directionX = direction;
	}
	
	public void setDirectionY(float direction){
		this.directionY = direction;
	}
	
	public float getDirectionX(){
		return directionX;
		
	}public float getDirectionY(){
		return directionY;
	}

}