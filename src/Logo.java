import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//This class gives attributes to the Logo sign object that will be on the screen... To make the board look cooler.
public class Logo {
	private Image logo;
	
	
	public Logo(){// give Logo attributes.
		
		//set the image for the Logo.
		try{
			logo = new Image("res/Log.png"); 
			
		
		} catch(SlickException e){
			e.printStackTrace();
		}
		
		
	}
	
	
	//getter for the Logo image 
	public Image getImage(){
		return logo;
	}
}
