
/*This class makes all of the initial forms of the  tetris blocks that will fall
 * one at a time in these configurations. These tetris blocks are put into array 
 * tetrisBlock, which will be called in the GameApp class. This tetrisBlock array 
 * is a 2D array. An array of 7 different tetris blocks, which are themselves
 * arrays of 4 different single Blocks. */

public class TetrisBlocks { 
	int s = 30; //sideLength
	private Block[][] tetrisBlocks= new Block[7][4];
	
	
	//getters for all 7 different types of tetris blocks, which are arrays of 4 square blocks.
	public Block[] get0 (){ 
		Block[] block1 = new Block[4];
		block1[0]= new Block(4*s, -3*s, s, s, "res/oppositeL.png");
		block1[1]= new Block(4*s+s, -3*s, s, s, "res/oppositeL.png");
		block1[2]= new Block(4*s-s, -3*s, s, s, "res/oppositeL.png");
		block1[3]= new Block(4*s-s, -4*s, s, s, "res/oppositeL.png");
		return block1;
	}
	public Block[] get1(){ 
		Block[] block2 = new Block[4];
		block2[0]= new Block(4*s, -3*s, s, s, "res/L.png");
		block2[1]= new Block(4*s-s, -3*s, s, s, "res/L.png");
		block2[2]= new Block(4*s+s, -3*s, s, s, "res/L.png");
		block2[3]= new Block(4*s+s, -4*s, s, s, "res/L.png");
		
		return block2;
	}
	public Block[] get2(){ 
		Block[] block3 = new Block[4];
		block3[0] = new Block(4*s, -3*s, s, s, "res/S.png");
		block3[1] = new Block(4*s-s, -3*s, s, s, "res/S.png");
		block3[2] = new Block(4*s, -4*s, s, s, "res/S.png");
		block3[3] = new Block(4*s+s, -4*s, s, s, "res/S.png");
		return block3;
	}
	
	public Block[] get3(){ 
		Block[] block4 = new Block[4];
		block4[0] = new Block(4*s, -3*s, s, s, "res/oppositeS.png");
		block4[1] = new Block(4*s+s, -3*s, s, s, "res/oppositeS.png");
		block4[2] = new Block(4*s, -4*s, s, s, "res/oppositeS.png");
		block4[3] = new Block(4*s-s, -4*s, s, s, "res/oppositeS.png");
		return block4;
	}
	public Block[] get4(){
		Block[] block5 = new Block[4];
		block5[0] = new Block(4*s, -3*s, s, s, "res/I.png");
		block5[1] = new Block(4*s-s, -3*s, s, s, "res/I.png");
		block5[2] = new Block(4*s+s, -3*s, s, s, "res/I.png");
		block5[3] = new Block(4*s+2*s, -3*s, s, s, "res/I.png");
		return block5;
	}
	public Block[] get5(){
		Block[] block6 = new Block[4];
		block6[0]= new Block(4*s, -3*s, s, s, "res/O.png");
		block6[1]= new Block(4*s+s, -3*s, s, s, "res/O.png");
		block6[2]= new Block(4*s, -4*s, s, s, "res/O.png");
		block6[3]= new Block(4*s+s, -4*s, s, s, "res/O.png");
		return block6;
	}
	public Block[] get6(){
		Block[] block7 = new Block[4];
		block7[0] = new Block(4*s, -3*s, s, s, "res/T.png");
		block7[1] = new Block(4*s-s, -3*s, s, s, "res/T.png");
		block7[2] = new Block(4*s+s, -3*s, s, s, "res/T.png");
		block7[3] = new Block(4*s, -4*s, s, s, "res/T.png");
		return block7;
	}
	
	
	public Block[][] getTetrisBlocks(){ //Filling the tetris block. Making and getting the array of tetris blocks.
		for(int i = 0; i<4; i++){
			tetrisBlocks[0][i] = get0()[i];
		}
		for(int i = 0; i<4; i++){
			tetrisBlocks[1][i] = get1()[i];
		}
		for(int i = 0; i<4; i++){
			tetrisBlocks[2][i] = get2()[i];
		}
		for(int i = 0; i<4; i++){
			tetrisBlocks[3][i] = get3()[i];
		}
		for(int i = 0; i<4; i++){
			tetrisBlocks[4][i] = get4()[i];
		}
		for(int i = 0; i<4; i++){
			tetrisBlocks[5][i] = get5()[i];
		}
		for(int i = 0; i<4; i++){
			tetrisBlocks[6][i] = get6()[i];
		}
		return tetrisBlocks;
	}
}
