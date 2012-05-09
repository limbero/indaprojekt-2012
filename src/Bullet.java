/**
 * A class that will create a simple bullet
 * with an image, speed and coordinates.
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	private int speed;
	private Image image;
	private Vector2f direction;
	private Vector2f position;

	/**
	 * Creates an bullet with image, speed, direction 
	 * and position.
	 * 
	 * @param the image of the bullet
	 * @param thie position where to spawn the bullet
	 * @param the direction in wish to fire the bullet
	 * @throws SlickException if image fails
	 */
	public Bullet(String image, Vector2f position, float rotation) throws SlickException{
		// Creates the image and vectors for position and direction
		this.image = new Image(image);
		direction = new Vector2f();
		this.position = new Vector2f(position);
		this.image.rotate(rotation);
		direction.set(0, -1);
		direction.sub(-rotation);
		direction.normalise();
		position.set(position);
		position.add(direction.copy().scale(15));
		direction.scale(30);
	}

	public Image getImage(){
		return image;
	}

	public int speed(){
		return speed;
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