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

}