

/*This class creates the blocks that will be spawned after the current falling blocks. 
 * This block will be drawn in the "Next Block" rectangle on the tetris board.*/

public class Next {

	int s = 30;
	float x = 460;
	float x2 = 445;
	int y = 480;
	int y2 = 465;
	private Block[][] hold= new Block[7][4];
	
	
	//getters for all 7 different types of tetris blocks, which are arrays of 4 square blocks.
	public Block[] get0 (){ 
		Block[] block1 = new Block[4];
		block1[0]= new Block(x, y, s, s, "res/oppositeL.png");
		block1[1]= new Block(x+s, y, s, s, "res/oppositeL.png");
		block1[2]= new Block(x-s, y, s, s, "res/oppositeL.png");
		block1[3]= new Block(x-s, y-s, s, s, "res/oppositeL.png");
		return block1;
	}
	public Block[] get1(){ 
		Block[] block2 = new Block[4];
		block2[0]= new Block(x, y, s, s, "res/L.png");
		block2[1]= new Block(x-s, y, s, s, "res/L.png");
		block2[2]= new Block(x+s, y, s, s, "res/L.png");
		block2[3]= new Block(x+s, y-s, s, s, "res/L.png");
		
		return block2;
	}
	public Block[] get2(){ 
		Block[] block3 = new Block[4];
		block3[0] = new Block(x, y, s, s, "res/S.png");
		block3[1] = new Block(x-s, y, s, s, "res/S.png");
		block3[2] = new Block(x, y-s, s, s, "res/S.png");
		block3[3] = new Block(x+s, y-s, s, s, "res/S.png");
		return block3;
	}
	
	public Block[] get3(){ 
		Block[] block4 = new Block[4];
		block4[0] = new Block(x, y, s, s, "res/oppositeS.png");
		block4[1] = new Block(x+s, y, s, s, "res/oppositeS.png");
		block4[2] = new Block(x, y-s, s, s, "res/oppositeS.png");
		block4[3] = new Block(x-s, y-s, s, s, "res/oppositeS.png");
		return block4;
	}
	public Block[] get4(){
		Block[] block5 = new Block[4];
		block5[0] = new Block(x2, y2, s, s, "res/I.png");
		block5[1] = new Block(x2-s, y2, s, s, "res/I.png");
		block5[2] = new Block(x2+s, y2, s, s, "res/I.png");
		block5[3] = new Block(x2+2*s, y2, s, s, "res/I.png");
		return block5;
	}
	public Block[] get5(){
		Block[] block6 = new Block[4];
		block6[0]= new Block(x2, y, s, s, "res/O.png");
		block6[1]= new Block(x2+s, y, s, s, "res/O.png");
		block6[2]= new Block(x2, y-s, s, s, "res/O.png");
		block6[3]= new Block(x2+s, y-s, s, s, "res/O.png");
		return block6;
	}
	public Block[] get6(){
		Block[] block7 = new Block[4];
		block7[0] = new Block(x, y, s, s, "res/T.png");
		block7[1] = new Block(x-s, y, s, s, "res/T.png");
		block7[2] = new Block(x+s, y, s, s, "res/T.png");
		block7[3] = new Block(x, y-s, s, s, "res/T.png");
		return block7;
	}
	
	
	//Making and getting the array of tetris blocks.
	public Block[][] getNext(){
		for(int i = 0; i<4; i++){
			hold[0][i] = get0()[i];
		}
		for(int i = 0; i<4; i++){
			hold[1][i] = get1()[i];
		}
		for(int i = 0; i<4; i++){
			hold[2][i] = get2()[i];
		}
		for(int i = 0; i<4; i++){
			hold[3][i] = get3()[i];
		}
		for(int i = 0; i<4; i++){
			hold[4][i] = get4()[i];
		}
		for(int i = 0; i<4; i++){
			hold[5][i] = get5()[i];
		}
		for(int i = 0; i<4; i++){
			hold[6][i] = get6()[i];
		}
		return hold;
	}
}

