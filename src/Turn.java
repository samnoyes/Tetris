import org.newdawn.slick.geom.Rectangle;

//class to create different forms of the blocks depending on the given location and the side length
//The first digit of the two digit number after "Rotate__" method is the type # of the block
//The second digit is the alternate forms of the blocks
public class Turn{

	public static Rectangle[] Rotate11(Rectangle[] L, int s, int x, int y){
				
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x-s);
		L[3].setY(y+s);
		
		return L;
		
	}
	
	public static Rectangle[] Rotate12(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x+s);
		L[1].setY(y+s);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x-s);
		L[3].setY(y);
		
		return L;
		
	}
	
	public static Rectangle[] Rotate13(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x+s);
		L[3].setY(y-s);
		
		return L;
		
	}
	public static Rectangle[] Rotate14(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x+s);
		L[1].setY(y);
		L[2].setX(x-s);
		L[2].setY(y);
		L[3].setX(x-s);
		L[3].setY(y-s);
		
		return L;
		
	}
	public static Rectangle[] Rotate21(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x+s);
		L[3].setY(y+s);
		
		return L;
		
	}
	public static Rectangle[] Rotate22(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x-s);
		L[1].setY(y+s);
		L[2].setX(x-s);
		L[2].setY(y);
		L[3].setX(x+s);
		L[3].setY(y);
		
		return L;
		
	}
	public static Rectangle[] Rotate23(Rectangle[] L, int s, int x, int y){
	
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x-s);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate24(Rectangle[] L, int s, int x, int y){
	
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x-s);
		L[1].setY(y);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x+s);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate31(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x+s);
		L[1].setY(y+s);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x+s);
		L[3].setY(y);
	
		return L;
	
	}
	public static Rectangle[] Rotate32(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x-s);
		L[1].setY(y);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x+s);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate41(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x+s);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate42(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x+s);
		L[1].setY(y);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x-s);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate51(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x);
		L[3].setY(y-2*s);
	
		return L;
	
	}
	public static Rectangle[] Rotate52(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x-s);
		L[1].setY(y);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x+2*s);
		L[3].setY(y);
	
		return L;
	
	}
	public static Rectangle[] Rotate61(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x+s);
		L[1].setY(y);
		L[2].setX(x);
		L[2].setY(y-s);
		L[3].setX(x+s);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate71(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate72(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x-s);
		L[3].setY(y);
	
		return L;
	
	}
	public static Rectangle[] Rotate73(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x);
		L[1].setY(y+s);
		L[2].setX(x-s);
		L[2].setY(y);
		L[3].setX(x);
		L[3].setY(y-s);
	
		return L;
	
	}
	public static Rectangle[] Rotate74(Rectangle[] L, int s, int x, int y){
		
		L[0].setX(x);
		L[0].setY(y);
		L[1].setX(x-s);
		L[1].setY(y);
		L[2].setX(x+s);
		L[2].setY(y);
		L[3].setX(x);
		L[3].setY(y-s);
	
		return L;
	
	}
	
}
