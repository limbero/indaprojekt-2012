public class Player {
	String name;
	String image;
	int health;
	
	public Player(String name, String image){
		this.name = name;
		this.image = image;
		health = 100;
	}
	
	public String name(){
		return name;
	}
	
	public String image(){
		return image;
	}
	
	public int health(){
		return health;
	}
}
