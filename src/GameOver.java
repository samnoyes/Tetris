import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//class for the object that will be the GameOver image shown when the user loses.
public class GameOver {
	private Image gameOver;
	
	
	public GameOver(){// give GameOver sign attributes.
		
		//set the image for the GameOver sign.
		try{
			gameOver = new Image("res/gameover.png"); 
			
		
		} catch(SlickException e){
			e.printStackTrace();
		}
		
		
	}
	
	
	//getter for the GameOver sign Image.
	public Image getImage(){
		return gameOver;
	}
}

