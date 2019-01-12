import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

//Class for the individual block objects that will be used to make tetris pieces and will fill the board.
public class Block extends Rectangle{
	
	private Image img;
	//create rectangle
	public Block(float x, float y, float w, float h, String i) {
	
		super(x, y, w, h);
		//receive image
		try {
			
			img = new Image(i);
			img.getScaledCopy((int)(w) , (int)(h));
			
		} 
		catch (SlickException e) {
		
			e.printStackTrace();
		
		}
		
	}
	//get image
	public Image getImage(){
		
		return img;
		
	}
	
	public Rectangle getSkinnyRect(Block a){ /*getSkinnyRect is used for vertical collisions, 
												so the intersects method doesn't make blocks 
												freeze when the falling block collides horizontally
												with a block */
		Rectangle rect = new Rectangle(a.getX()+5, a.getY(), 20,30);
		return rect;
	}
	
}
