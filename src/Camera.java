import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;


public class Camera{
	Rectangle playerView;
	
	public Camera(){
		playerView = new Rectangle(0, 0, 800, 600);
	}
	
	public void centerArea(TiledMap map, Player player){
		playerView.setX(player.getX());
		playerView.setY(player.getY());
	}
}