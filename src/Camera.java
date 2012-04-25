import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;


public class Camera{
	Rectangle playerView;
	int viewHeight=600, viewWidth=800;
	
	public Camera(){
		playerView = new Rectangle(0, 0, viewWidth, viewHeight);
	}
	
	public void centerArea(Graphics g, Player player){
		playerView.setCenterX(player.getX());
		playerView.setCenterY(player.getY());
		g.setWorldClip(playerView);
	}
	
	public Rectangle getView(){
		return playerView;
	}
}