/**
 * A class that will create a simple bullet
 * with an image, speed and coordinates.
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	private int speed;
	private float x;
	private float y;
	private Image image;
	private float directionX;
	private float directionY;
	private Vector2f direction;
	private Vector2f position;

	public Bullet(Image image){
		this.image = image;
		direction = new Vector2f();
		position = new Vector2f();
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
		
	}
	
	public float getDirectionY(){
		return directionY;
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public void setPosition(Vector2f position){
		this.position = position;
	}
	
	public Vector2f getDirection(){
		return direction;
	}
	
	public void setDirection(Vector2f direction){
		this.direction = direction;
	}
	
}