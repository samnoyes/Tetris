import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameApp extends BasicGame{
	
	private Logo logo; //object for the Tetris Logo that shows on screen.
	private GameOver skull; //object for GameOver image.
	private TetrisBlocks a = new TetrisBlocks(); //new tetris Block
	private Next b = new Next(); //new Block that shows the following block after the current block
	private Hold c = new Hold(); //new Block that shows the block that is in hold
	private Block[][] blocks = new Block[7][4]; //array of the blocks that will be falling on the screen
	private Block[][] next = new Block[7][4]; //array of the blocks that will come after the falling block
	private Block[][] hold = new Block[7][4]; // array of blocks that will be the block that is in hold
	private int k; //number between 0 and 6 for the type# for the current block
	private int l; //number for a back up type# for block on hold
	private int h; //number between 0 and 6 for the type# for hold
	private int s; //side length of a single block
	private int count; //# of times that the turn button pressed
	private Input input; //key input 
	private static ArrayList<Block> frozens = new ArrayList<Block>(); //list of frozen blocks that aren't falling.
	private double speed; //speed of the falling block
	private Random rand; //random # generator
	private boolean hitR; //boolean for hitting the right edge of the game window 
	private boolean hitL; //boolean for hitting the lift edge of the game window
	private boolean holdcheck; // boolean for checking holds 
	private boolean reset; // boolean to reset or not
	private int k2; // the type # for the upcoming block after the current block
	private double addSpeed; //added speed when you press the down arrow key.
	private double addSpeed2; //the drastic up in speed added when pressing up key, that makes the immediately down effect.
	private static int score; //# of score
	private boolean rtnL; //set true if the falling block has blocks to the left, set false if the falling blocks don't have any blocks to the left.
	private boolean rtnR; //set true if the falling block has blocks to the right, set false if the falling blocks don't have any blocks to the right.
	private boolean gameOver; //boolean set true when game is over, set false when game is going on.
	
	public GameApp(String title) {
		super(title);
	}
	
	public static void main( String[] args) throws SlickException{ 
		
		GameApp app = new GameApp("Tetris");
		AppGameContainer ac = new AppGameContainer(app);
		ac.setTargetFrameRate(500);
		ac.setShowFPS(false);
		ac.setDisplayMode(600, 540, false); //set frame size
		ac.start();
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		logo = new Logo(); //make the Logo object.
		skull = new GameOver(); //make the GameOver object.
		hitL = false; //initialize boolean to make sure it is false when the game starts
		hitR = false; //initialize boolean to make sure it is false when the game starts
		holdcheck = false; //initialize boolean to make sure the game allows holding blocks when it starts
		reset = false; // initialize boolean to make sure the game game does not reset every time
		blocks = a.getTetrisBlocks(); // initialize variable to new blocks
		next = b.getNext();
		h = -1; //initialize variable to make sure it is not a number between 0 and 7
		hold = null; // set null to make sure there are no blocks in the hold
		k = (int) (Math.floor(Math.random()*7)); //type# for the falling block of the game
		k2 = (int) (Math.floor(Math.random()*7)); //type# for the following block after current block
		s = 30; //setting the side length of a block
		score = 0; //setting score to 0
		count = 1; //setting # of times the turning button;
		rand = new Random(); // set random to Random class
		input = gc.getInput();
		addSpeed = 0; //initialize the added speed to 0.
		addSpeed2 = 0;
		gameOver = false; //initialize gameOver as false.

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		/*the default for these booleans are false because we assume that the falling block does not have
		 * blocks to the left and right until we check and prove our assumption wrong.
		 */
		rtnL = false; 
		rtnR = false;
		
		speed = 80 * (delta/1000.0); //set speed
		
		//check so that the next type of block that spawns after the current block is different
		if(k == k2){
			k2 = rand.nextInt(7);
		}
		lineChecker(); //calls the lineChecker method.
		
		//move the selected block down.
		if(!gameOver){ //tetris blocks stop falling if gameOver is true.
			for(int i = 0; i < 4; i++){
				blocks[k][i].setY(blocks[k][i].getY() + (float)speed + (float)addSpeed + (float)addSpeed2);
			}
		}
		
		/*checks vertical collisions with dropped blocks. Makes the blocks frozen once 
		 * they fall on another block, then makes another tetris block fall.
		 */
		for(int i = 0; i < blocks[k].length; i++){
			for(int j = 0; j < frozens.size(); j++){
				if(blocks[k][i].intersects(frozens.get(j).getSkinnyRect(frozens.get(j))) && blocks[k][i].getY()>= frozens.get(j).getY()-s){
					for(int l = 0; l < blocks[k].length; l++){
						blocks[k][l].setY(30*(int)(blocks[k][l].getY()/30));
						frozens.add(blocks[k][l]);
					}
					blocks = a.getTetrisBlocks();
					k = k2;
					k2 = rand.nextInt(7);
					count = 1;
					hitR = false;
					hitL = false;
					reset = false;
					holdcheck = false;
					addSpeed2 = 0;
				}
			}
		}
		
		//check collisions with both edges of the game 
		for(int i=0; i<4; i++){
			if(blocks[k][i].getX() == 0){
				hitL = true;
			}
			if(blocks[k][i].getX() == 300-s){
				hitR = true;
			}
		}
		
		//freezes the box when it collides or reaches the bottom of the screen
		//spawns the upcoming piece to current
		//reset all the booleans to initial state
		if(blocks[k][1].getY()>=gc.getHeight()-s || reset){
			if(blocks[k][1].getY() >= gc.getHeight()-s){
				for(int i = 0; i < 4; i++){
					blocks[k][i].setY(((int)(blocks[k][i].getY()/30)) *30);
					frozens.add(blocks[k][i]);
				}
			}
			blocks = a.getTetrisBlocks();
			k = k2;
			k2 = rand.nextInt(7);
			addSpeed2 = 0;
			count = 1;
			hitR = false;
			hitL = false;
			reset = false;
			holdcheck = false;
		}
		
		
		/*Checks whether a tetris block gets frozen at the top of the screen. If a
		 * tetris block reaches the top, then gameOver is set true.
		 */
		for(int i=0; i<frozens.size(); i++){
			if(frozens.size() > 0 && frozens.get(i).getY() <= 0){
				gameOver = true;
			}
					
		}
		
		//Do rotations when SpaceBar is pressed
		//returns the rotated form of the current block by counting the "count" variable
		//also returns a shifted, rotated form of the current block if the block is on the side edges of the game
		if( input.isKeyPressed(Input.KEY_SPACE)){
			if(k == 0){
				if(count%4 == 0 && !leftCheck(rtnL) && !rightCheck(rtnR)){
					if(hitL){
						Turn.Rotate14(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					if(hitR){
						Turn.Rotate14(blocks[k], s, (int)blocks[k][0].getX()-s, (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate14(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
				else{
					if(count%4 == 1){
						Turn.Rotate13(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
						count++;
						hitL = false;
					}
					else{
						if(count%4 == 2 && !leftCheck(rtnL) && !rightCheck(rtnR)){
							if(hitL){
								Turn.Rotate12(blocks[k], s, (int)blocks[k][0].getX()+s, (int)blocks[k][0].getY());
							}
							if(hitR){
								Turn.Rotate12(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							}
							if(!hitL && !hitR){
								Turn.Rotate12(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							}
							count++;
						}
						else{
							Turn.Rotate11(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							count++;
							hitR = false;
						}
					}
				}
			}
			if(k == 1){
				if(count%4 == 0 && !leftCheck(rtnL) && !rightCheck(rtnR)){
					if(hitL){
						Turn.Rotate24(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					if(hitR){
						Turn.Rotate24(blocks[k], s, (int)blocks[k][0].getX()-s, (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate24(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
				else{
					if(count%4 == 1){
						Turn.Rotate21(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
						count++;
						hitL = false;
					}
					else{
						if(count%4 == 2 && !leftCheck(rtnL) && !rightCheck(rtnR)){
							if(hitL){
								Turn.Rotate22(blocks[k], s, (int)blocks[k][0].getX()+s, (int)blocks[k][0].getY());
							}
							if(hitR){
								Turn.Rotate22(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							}
							if(!hitL && !hitR){
								Turn.Rotate22(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							}
							count++;
						}
						else{
							Turn.Rotate23(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							count++;
							hitR = false;
						}
					}
				}
			}
			if(k == 2){
				if(count%2 == 0 && !leftCheck(rtnL) && !rightCheck(rtnR)){
					if(hitL){
						Turn.Rotate32(blocks[k], s, (int)blocks[k][0].getX()+s, (int)blocks[k][0].getY());
					}
					if(hitR){
						Turn.Rotate32(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate32(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
				else{
					if(hitR){
						Turn.Rotate31(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					if(hitL){
						Turn.Rotate31(blocks[k], s, (int)blocks[k][0].getX()-s, (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate31(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
			}
			if(k == 3){
				if(count%2 == 0 && !leftCheck(rtnL) && !rightCheck(rtnR)){
					if(hitL){
						Turn.Rotate42(blocks[k], s, (int)blocks[k][0].getX()+s, (int)blocks[k][0].getY());
					}
					if(hitR){
						Turn.Rotate42(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
						hitR=false;
					}
					if(!hitL && !hitR){
						Turn.Rotate42(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;				
				}
				else{
					if(hitR){
						Turn.Rotate41(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					if(hitL){
						Turn.Rotate41(blocks[k], s, (int)blocks[k][0].getX()-s, (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate41(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
			}
			if(k == 4){
				if(count%2 == 0 && !leftCheck(rtnL) && !rightCheck(rtnR)){
					if(hitL){
						Turn.Rotate52(blocks[k], s, (int)blocks[k][0].getX()+s, (int)blocks[k][0].getY());
					}
					if(hitR){
						Turn.Rotate52(blocks[k], s, (int)blocks[k][0].getX()-2*s, (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate52(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
				else{
					Turn.Rotate51(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					count++;
					hitL = false;
					hitR = false;
				}
			}
			if(k == 6){
				if(count%4 == 0 && !leftCheck(rtnL) && !rightCheck(rtnR)){
					if(hitL){
						Turn.Rotate74(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					if(hitR){
						Turn.Rotate74(blocks[k], s, (int)blocks[k][0].getX()-s, (int)blocks[k][0].getY());
					}
					if(!hitL && !hitR){
						Turn.Rotate74(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
					}
					count++;
				}
				else{
					if(count%4 == 1){
						Turn.Rotate71(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
						count++;
						hitL = false;
					}
					else{
						if(count%4 == 2 && !leftCheck(rtnL) && !rightCheck(rtnR)){
							if(hitL){
								Turn.Rotate72(blocks[k], s, (int)blocks[k][0].getX()+s, (int)blocks[k][0].getY());
							}
							if(hitR){
								Turn.Rotate72(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							}
							if(!hitL && !hitR){
								Turn.Rotate72(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							}
							count++;
						}
						else{
							Turn.Rotate73(blocks[k], s, (int)blocks[k][0].getX(), (int)blocks[k][0].getY());
							count++;
							hitR = false;
						}
					}
				}
			}
		}
		
		//increase speed when Down key is pressed
		if(input.isKeyDown(Input.KEY_DOWN)){
			addSpeed = 800 * (delta/1000.0);	
		}
		 //reset speed as before when Down key is released
		if(!input.isKeyDown(Input.KEY_DOWN)){
			addSpeed = 0;
		}
		
		//increase speed drastically when up key is pressed, to create that immediate down key feeling.
		if(input.isKeyPressed(Input.KEY_UP)){
			addSpeed2 = 30 ;	
			for(int i = 0; i < blocks[k].length; i++){
				for(int j = 0; j < frozens.size(); j++){
					if(blocks[k][1].getY()>=gc.getHeight()-s){
						reset = true;
					}
					if(blocks[k][i].intersects(frozens.get(j).getSkinnyRect(frozens.get(j))) && blocks[k][i].getY() >= frozens.get(j).getY()-s){
						reset = true;
					}
				}
			}
		}
		
		//Send block to hold when H was pressed
		if(input.isKeyPressed(Input.KEY_H) && !holdcheck){
			//send block to hold and spawn the next block if the hold block is empty
			//change "holdcheck" to false so the blocks cannont switch until a new block is spawned
			if(hold == null){
				hold = c.getHold();
				h = k;
				reset = true;
				holdcheck = true;
			}
			//send block to hold and spawn the block that was in hold if the hold block is not empty
			//change "holdcheck" to false so the blocks cannont switch until a new block is spawned
			if(hold != null){
				l = h;
				h = k;
				k = l;
				blocks = a.getTetrisBlocks();
				hold = c.getHold();
				hitR = false;
				hitL = false;
				holdcheck = true;
				count = 1;
			}
		}
		
		//Move Left when left key is pressed, no blocks are right next to the current block and it is not on the left edge of the screen 
		if( input.isKeyPressed(Input.KEY_LEFT) && !hitL && !leftCheck(rtnL)){
			//allow movement to the right after it moved left when the block is on the right edge of the game
			if(hitR){
				hitR = false;
			}
			//move left
			for(int i = 0; i < 4; i++){
				blocks[k][i].setX(blocks[k][i].getX()-s);
			}
		}
		
		//Move Left when right key is pressed, no blocks are right next to the current block and it is not on the right edge of the screen
		if( input.isKeyPressed(Input.KEY_RIGHT) && !hitR && !rightCheck(rtnR)){
			//allow movement to the left after it moved right when the block is on the left edge of the game
			if(hitL){
				hitL = false;
			}
			//move right
			for(int i = 0; i < 4; i++){
				blocks[k][i].setX(blocks[k][i].getX()+s);
			}
		}	
		
		
		//if User presses enter, gameOver is set to false, the board and score is cleared, and tetris blocks start falling again.
		if( input.isKeyPressed(Input.KEY_ENTER) && gameOver){
			gameOver = false;
			frozens.clear();
			score = 0;			
		}
		
	}
	

	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//set background to transparent
		g.setBackground(Color.transparent);
		
		//draw line to seperate game panel with score panel
		g.drawLine(300, 0, 300, gc.getHeight());
		
		//draw the falling block
		for(int i = 0; i < 4; i++){
			g.drawImage(blocks[k][i].getImage(), blocks[k][i].getX(), (int)(blocks[k][i].getY()/30)*30, blocks[k][i].getX()+blocks[k][i].getWidth(), (int)((blocks[k][i].getY()+blocks[k][i].getHeight())/30)*30, 0, 0, blocks[k][i].getImage().getWidth(), blocks[k][i].getImage().getHeight());
		}
		
		//draw upcoming block after the current block
		for(int i = 0; i < 4; i++){
			g.drawImage(next[k2][i].getImage(), next[k2][i].getX(), next[k2][i].getY(), next[k2][i].getX()+next[k2][i].getWidth(), next[k2][i].getY()+next[k2][i].getHeight(), 0, 0, next[k2][i].getImage().getWidth(), next[k2][i].getImage().getHeight());
		}
		
		//draw the block that are in hold
		if(hold!=null){
			for(int i = 0; i < 4; i++){
				g.drawImage(hold[h][i].getImage(), hold[h][i].getX(), hold[h][i].getY(), hold[h][i].getX()+hold[h][i].getWidth(), hold[h][i].getY()+hold[h][i].getHeight(), 0, 0, hold[h][i].getImage().getWidth(), hold[h][i].getImage().getHeight());
			}
		}
		
		//draw the fallen blocks
		for(int i = 0; i < frozens.size(); i++){
			g.drawImage(frozens.get(i).getImage(), frozens.get(i).getX(), (int)(frozens.get(i).getY()/30)*30, frozens.get(i).getX()+frozens.get(i).getWidth(), (int)((frozens.get(i).getY()+frozens.get(i).getHeight())/30)*30, 0, 0, frozens.get(i).getImage().getWidth(), frozens.get(i).getImage().getHeight());
		}
		
		//draw vertical lines on the game panel
		for(int i = 30; i < 300; i = i+30){
			g.drawGradientLine(i, 0, Color.gray, i, gc.getHeight(), Color.gray);
		}
		
		//draw horizontal lines on the game panel
		for(int i = 30; i < 540; i = i+30){
			g.drawGradientLine(0, i, Color.gray, 300, i, Color.gray);
		}
		
		//Draws the game over message on the screen when gameOver is true.
		if(gameOver){	
			g.drawImage(skull.getImage(), 0, 150);
			g.drawString("PRESS ENTER TO CONTINUE", 40, 280);
		}
		
		//draw the tetris logo on the score panel
		g.drawImage(logo.getImage(), 350, 130);
		
		//draw rectangle for the block that are in hold
		g.drawRect(415, 15, 120, 90);
		
		//draw rectangle for the next block after the current block
		g.drawRect(415, 435, 120, 90);
		
		//Show string that identifies which is the rectangle that show the block on hold
		g.drawString("HOLD", 350, 55);

		//Show string that identifies which is the rectangle that show the upcoming block after the current block
		g.drawString("NEXT", 350, 475);
		
		//show the lines that have been cleared in the game
		g.drawString("Lines : "+ (score/10), 405, 325);
		
		//show singular blocks that have been cleared in the game
		g.drawString("SCORE : "+ score, 405, 345);
		
	}
	
	/*method to set rtnL true whenever the falling block has blocks to its left. 
	 * This check ensures users cannot move the falling blocks through blocks that are already frozen to their left.
	 */
	public boolean leftCheck(boolean rtnL){	
		for(int i = 0; i < blocks[k].length; i++){
			for(int j = 0; j < frozens.size(); j++){
				if(blocks[k][i].getX() - frozens.get(j).getX() > 0 && Math.abs(blocks[k][i].getX() - frozens.get(j).getX()) <= s &&  Math.abs(blocks[k][i].getY() - frozens.get(j).getY()) < s){
					rtnL = true;
				}
			}
		}	
		return rtnL;
	}
	
	/*method to set rtnR true whenever the falling block has blocks to its right. 
	 * This check ensures users cannot move the falling blocks through blocks that are already frozen to their right.
	 */
	public boolean rightCheck(boolean rtnR){	
		for(int i = 0; i < blocks[k].length; i++){
			for(int j = 0; j < frozens.size(); j++){	
				if(blocks[k][i].getX() - frozens.get(j).getX() < 0 && Math.abs(blocks[k][i].getX() - frozens.get(j).getX()) <= s && Math.abs(blocks[k][i].getY() - frozens.get(j).getY()) < s){
					rtnR = true;
				}
			}
		}
		return rtnR;
	}
	
	
	//This method checks for complete lines and erases them. Then the blocks above the emptied line are shifted down.
	public static void lineChecker(){
		ArrayList<Block> lines = new ArrayList<Block>(); //ArrayList of blocks that are part of the complete lines.
		ArrayList<Integer> ypos = new ArrayList<Integer>(); //the heights/30 of the complete line.
		
		for(int y = 17; y >= 0; y--){	
			for(int x = 0; x < 10; x++ ){
				for(int i = 0; i < frozens.size(); i++){
					if(frozens.get(i).getX() == x*30 && (int)(frozens.get(i).getY()/30)*30 == y*30){ //adds blocks within the same line on the board to lines Arraylist.
						lines.add(frozens.get(i));
					}
				}		
			} 
			if(lines.size() < 10){ //if there are less than 10 blocks within a certain line, the blocks within the lines are cleared from the line Arraylist
				lines.clear();
			}
			
			if(lines.size() == 10){ //if the line is full, then record the height of the line in the ypos Arraylist. 
				ypos.add(y);
				for(int j = 0; j < lines.size(); j++){
					frozens.remove(lines.get(j)); //remove the blocks within the filled line.
					score++; //add 10 to the score when lines is filled.
				}	
			}
		}
		
		Integer[] heights = new Integer[ypos.size()]; // all the heights of the filled lines will be put into this array.
		
		for(int k = 0; k < ypos.size(); k++){ //fill the heights array.
			heights[k] = ypos.get(k);
		
		}
		ypos.clear(); //clear the ypos Arraylist are filling the heights array with the ypositions of the filled lines.
		
		Arrays.sort(heights, new Comparator<Integer>(){ //method derived from online to order the numbers in the heights array from smallest to largest.
			        @Override
			        public int compare(Integer x, Integer y)
			        {
			            return x-y;
			        }
		});
		
		for(int k = 0; k< heights.length; k++){ //moves the blocks above the emptied lines down a step of the board.
			for(int j = 0; j < frozens.size(); j++){
				if(frozens.get(j).getY() < heights[k]*30){	
					frozens.get(j).setY((int)((frozens.get(j).getY()+30)/30)*30);
				}
			}
		}
	}
}

