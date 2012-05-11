import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player implements java.io.Serializable{
	private String name;
	private float x, y;
	transient private Image image = null;
	private float radius;
	transient private Vector2f position;
	transient private Vector2f direction;
	
	public Player(String s){
		name = s;
		position = new Vector2f();
		//radius = image.getWidth()/2;
	}
	
	/**
	 * returns the player's image
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * sets the player's image
	 * 
	 * @param Path to image
	 */
	public void setImage(String s){
		try {
			image = new Image(s);
		} catch (SlickException e) {}
	}
	
	/**
	 * returns the player's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * returns the player's x coordinate
	 */
	public float getX(){
		return x;
	}
	
	/**
	 * Returns a position vector for the player
	 */
	public Vector2f getPosition(){
		position.set(x, y);
		return position;
	}
	
	/**
	 * Returns a normalized vector in the players 
	 * direction.
	 */
	public Vector2f getDirection(){
		direction = new Vector2f(0, -1);
		direction.sub(-image.getRotation());
		return direction;
	}
	
	/**
	 * sets the player's x coordinate
	 * 
	 * @param Coordinate
	 */
	public void setX(float i){
		x=i;
	}
	
	/**
	 * returns the player's y coordinate
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * sets the player's y coordinate
	 * 
	 * @param Coordinate
	 */
	public void setY(float i){
		y=i;
	}
	
	/**
	 * Checks if the 2 coordinates is colliding
	 * with the player.
	 * @return true if coordinates is colliding
	 */
	public boolean checkCollision(float x, float y){
		if(x < this.x + radius && x > this.x - radius && 
				y < this.y + radius && y > this.y - radius){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the players radius
	 */
	float getRadius(){
		return radius;
	}
	
	/**
	 * Renders the player on the screen
	 */
	public void render(){
		
	}
	
	/**
	 * Kills the player
	 */
	public void die() throws SlickException{
		image = new Image("data/dead.png");
	}
	
}
