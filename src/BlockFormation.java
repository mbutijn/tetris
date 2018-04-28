import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BlockFormation extends ArrayList<Block> {

	private static final long serialVersionUID = 1L;
	private Color color;
	private char type;
	private static List<Block> blockList;
	private int[] widthIndex, heightIndex;
	private int I_left, J_under;

	BlockFormation(int I_left, char type, Grid grid){
		blockList = new ArrayList<>();
		this.I_left = I_left;
		this.type = type;
		if (type == '-'){
			widthIndex = new int[] {0, 1, 2, 3};
			heightIndex = new int[] {0, 0, 0, 0};
			J_under = 1;
			color = new Color(0, 235, 235);
		} else if (type == '.'){
			widthIndex = new int[] {0, 1, 0, 1};
			heightIndex = new int[] {-1, -1, 0, 0};
			J_under = 2;
			color = new Color(240, 240, 10);
		} else if (type == 'L'){
			widthIndex = new int[] {0, 0, 0, 1};
			heightIndex = new int[] {-2, -1, 0, 0};
			J_under = 3;
			color = new Color(230, 140, 20);
		} else if (type == 't'){
			widthIndex = new int[] {0, 1, 1, 2};
			heightIndex = new int[] {0, 0, -1, 0};
			J_under = 2;
			color = new Color(200, 0, 200);
		} else if (type == 's'){
			widthIndex = new int[] {0, 1, 1, 2};
			heightIndex = new int[] {0, 0, -1, -1};
			J_under = 2;
			color = new Color(230, 20, 20);
		} else if (type == 'z'){
			widthIndex = new int[] {0, 1, 1, 2};
			heightIndex = new int [] {-1, -1, 0, 0};
			J_under = 2;
			color = new Color(30, 240, 40);
		} else if (type == '0'){
			widthIndex = new int[] {1, 1, 1, 0};
			heightIndex = new int[] {-2, -1, 0, 0};
			J_under = 3;
			color = new Color(30, 30, 255);
		}
		for(int k = 0; k < 4; k++){
			Block block = new Block(widthIndex[k], heightIndex[k], color);
			blockList.add(block);
			grid.setHoldsBlock(I_left +block.i, J_under +block.j, true);
		}
	}

	boolean checkBelow(Grid grid) {
		boolean down = false;
		if(type == '-'){
			if (grid.getHoldsBlock(I_left, J_under +1) || grid.getHoldsBlock(I_left +1, J_under +1) ||
					grid.getHoldsBlock(I_left +2, J_under +1) || grid.getHoldsBlock(I_left +3, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if(type == '|'){
			if(grid.getHoldsBlock(I_left +1, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if(type == '.' || type == 'L' || type == '0'){ // two wide and flat bottom
			if (grid.getHoldsBlock(I_left, J_under +1) || grid.getHoldsBlock(I_left +1, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if(type == '�' || type == 't' || type == '3'){ // three wide
			if(grid.getHoldsBlock(I_left, J_under +1)|| grid.getHoldsBlock(I_left +1, J_under +1)|| grid.getHoldsBlock(I_left +2, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if(type == '7'){
			if(grid.getHoldsBlock(I_left, J_under -1)|| grid.getHoldsBlock(I_left +1, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if(type == '^'){
			if(grid.getHoldsBlock(I_left, J_under +1)|| grid.getHoldsBlock(I_left +1, J_under)|| grid.getHoldsBlock(I_left +2, J_under)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if (type == 'T'){
			if (grid.getHoldsBlock(I_left, J_under) || grid.getHoldsBlock(I_left +1, J_under +1) || grid.getHoldsBlock(I_left +2, J_under)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if(type == 's'){
			if (grid.getHoldsBlock(I_left, J_under +1) || grid.getHoldsBlock(I_left +1, J_under +1) || grid.getHoldsBlock(I_left +2, J_under)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if (type == 'S'|| type == '4'){
			if (grid.getHoldsBlock(I_left, J_under) || grid.getHoldsBlock(I_left +1, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if (type == 'z'){
			if (grid.getHoldsBlock(I_left, J_under) || grid.getHoldsBlock(I_left +1, J_under +1) || grid.getHoldsBlock(I_left +2, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if (type == 'Z' || type == '5'){
			if(grid.getHoldsBlock(I_left, J_under +1) || grid.getHoldsBlock(I_left +1, J_under)){
				down = true;
			} else {
				move(grid,'v');
				down = false;
			}
		} else if(type == '1'){
			if(grid.getHoldsBlock(I_left, J_under)|| grid.getHoldsBlock(I_left +1, J_under)|| grid.getHoldsBlock(I_left +2, J_under +1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		} else if (type == '2'){
			if(grid.getHoldsBlock(I_left, J_under +1)|| grid.getHoldsBlock(I_left +1, J_under -1)){
				down = true;
			} else {
				move(grid, 'v');
				down = false;
			}
		}
		return down;
	}

	void checkRight(Grid grid) {
		if (type == '-'){
			if (!grid.getHoldsBlock(I_left +4, J_under)){
				move(grid, '>');
			}
		} else if (type == '|'){
			if (!grid.getHoldsBlock(I_left +2, J_under -3) && !grid.getHoldsBlock(I_left +2, J_under -2) &&
					!grid.getHoldsBlock(I_left +2, J_under -1) && !grid.getHoldsBlock(I_left +2, J_under)){
				move(grid, '>');
			}
		} else if (type == '.'){
			if (!grid.getHoldsBlock(I_left +2, J_under) && !grid.getHoldsBlock(I_left +2, J_under -1)){
				move(grid, '>');
			}
		} else if (type == 'L'){
			if (!grid.getHoldsBlock(I_left +1, J_under -2) && !grid.getHoldsBlock(I_left +1, J_under -1) && !grid.getHoldsBlock(I_left +2, J_under)){
				move(grid, '>');
			}
		} else if (type == '�'|| type == '1'){
			if (!grid.getHoldsBlock(I_left +3, J_under) && !grid.getHoldsBlock(I_left +3, J_under -1)){
				move(grid, '>');
			}
		} else if (type == '7' || type == '0' || type == '4'){
			if (!grid.getHoldsBlock(I_left +2, J_under -2) && !grid.getHoldsBlock(I_left +2, J_under -1) && !grid.getHoldsBlock(I_left +2, J_under)){
				move(grid, '>');
			}
		} else if (type == '^'){
			if (!grid.getHoldsBlock(I_left +1, J_under) && !grid.getHoldsBlock(I_left +3, J_under -1)){
				move(grid, '>');
			}
		} else if (type == 't' || type == 'z'){
			if (!grid.getHoldsBlock(I_left +2, J_under -1) && !grid.getHoldsBlock(I_left +3, J_under)){
				move(grid, '>');
			}
		} else if (type == 'T'){
			if (!grid.getHoldsBlock(I_left +2, J_under +1) && !grid.getHoldsBlock(I_left +3, J_under)){
				move(grid, '>');
			}
		} else if (type == 's'){
			if (!grid.getHoldsBlock(I_left +2, J_under) && !grid.getHoldsBlock(I_left +3, J_under -1)){
				move(grid, '>');
			}
		} else if (type == 'S'){
			if (!grid.getHoldsBlock(I_left +1, J_under -2) && !grid.getHoldsBlock(I_left +2, J_under -1) && !grid.getHoldsBlock(I_left +2, J_under)){
				move(grid, '>');
			}
		} else if (type == 'Z'){
			if (!grid.getHoldsBlock(I_left +2, J_under -2) && !grid.getHoldsBlock(I_left +2, J_under -1) && !grid.getHoldsBlock(I_left +1, J_under)){
				move(grid, '>');
			}
		} else if (type == '2'){
			if (!grid.getHoldsBlock(I_left +2, J_under -2) && !grid.getHoldsBlock(I_left +1, J_under -1) && !grid.getHoldsBlock(I_left +1, J_under)){
				move(grid, '>');
			}
		} else if (type == '3'){
			if (!grid.getHoldsBlock(I_left +3, J_under) && !grid.getHoldsBlock(I_left +1, J_under -1)){
				move(grid, '>');
			}
		} else if (type == '5'){
			if (!grid.getHoldsBlock(I_left +1, J_under -2) && !grid.getHoldsBlock(I_left +2, J_under -1) && !grid.getHoldsBlock(I_left +1, J_under)){
				move(grid, '>');
			}
		}
	}

	void checkLeft(Grid grid) {
		if (type == '-'){
			if(!grid.getHoldsBlock(I_left -1, J_under)){
				move(grid, '<');
			}
		} else if (type == '|'){
			if (!grid.getHoldsBlock(I_left, J_under -3) && !grid.getHoldsBlock(I_left, J_under -2)&& !grid.getHoldsBlock(I_left, J_under -1) && !grid.getHoldsBlock(I_left, J_under)){
				move(grid, '<');
			}
		} else if (type == '.'|| type == '^'|| type == '3'){
			if (!grid.getHoldsBlock(I_left -1, J_under) && !grid.getHoldsBlock(I_left -1, J_under -1)){
				move(grid, '<');
			}
		} else if (type == 'L' || type == '2' || type == '5'){
			if (!grid.getHoldsBlock(I_left -1, J_under) && !grid.getHoldsBlock(I_left -1, J_under -1) && !grid.getHoldsBlock(I_left -1, J_under -2)){
				move(grid, '<');
			}
		} else if (type == '�'){
			if (!grid.getHoldsBlock(I_left -1, J_under) && !grid.getHoldsBlock(I_left +1, J_under -1)){
				move(grid, '<');
			}
		} else if (type == '7'){
			if (!grid.getHoldsBlock(I_left, J_under) && !grid.getHoldsBlock(I_left, J_under -1) && !grid.getHoldsBlock(I_left -1, J_under -2)){
				move(grid, '<');
			}
		} else if (type == 't' || type == 's'){
			if (!grid.getHoldsBlock(I_left -1, J_under) && !grid.getHoldsBlock(I_left, J_under -1)){
				move(grid, '<');
			}
		} else if (type == 'T'){
			if (!grid.getHoldsBlock(I_left -1, J_under) && !grid.getHoldsBlock(I_left, J_under +1)){
				move(grid, '<');
			}
		} else if (type == 'S'){
			if (!grid.getHoldsBlock(I_left -1, J_under -2) && !grid.getHoldsBlock(I_left -1, J_under -1) && !grid.getHoldsBlock(I_left, J_under)){
				move(grid, '<');
			}
		} else if (type == 'z'){
			if (!grid.getHoldsBlock(I_left -1, J_under -1) && !grid.getHoldsBlock(I_left, J_under)){
				move(grid, '<');
			}
		} else if (type == 'Z'){
			if (!grid.getHoldsBlock(I_left, J_under -2) && !grid.getHoldsBlock(I_left -1, J_under -1) && !grid.getHoldsBlock(I_left -1, J_under)){
				move(grid, '<');
			}
		} else if (type == '0'){
			if (!grid.getHoldsBlock(I_left -1, J_under) && !grid.getHoldsBlock(I_left, J_under -1) && !grid.getHoldsBlock(I_left, J_under -2)){
				move(grid, '<');
			}
		} else if (type == '1'){
			if (!grid.getHoldsBlock(I_left -1, J_under -1) && !grid.getHoldsBlock(I_left +1, J_under)){
				move(grid, '<');
			}
		} else if (type == '4'){
			if (!grid.getHoldsBlock(I_left, J_under -2) && !grid.getHoldsBlock(I_left -1, J_under -1) && !grid.getHoldsBlock(I_left, J_under)){
				move(grid, '<');
			}
		}
	}

	private void move(Grid grid, char direction){
		changeBlockMatrix(grid, false);

		if(direction == '>'){
			I_left++;
		} else if (direction == '<') {
			I_left--;
		} else if (direction == 'v'){
			J_under++;
		}
		changeBlockMatrix(grid,true);
	}

	private void changeBlockMatrix(Grid grid, boolean occupied){
		for (Block block : blockList){
			grid.setHoldsBlock(I_left + widthIndex[blockList.indexOf(block)], J_under + heightIndex[blockList.indexOf(block)], occupied);
		}
	}

	void layDownBlocks(Field field){
		for(Block block : blockList){
			Field.groundBlocks.add(block);
			if(block.j == 1){
				field.stopGame();
				break;
			}
		}
	}

	void rotate(Grid grid){
		changeBlockMatrix(grid, false);

		if(type == '-'){
			if(J_under > 3 && J_under < Grid.heightNumber){
				widthIndex = new int[] {1, 1, 1, 1};
				heightIndex = new int[] {0, -1, -2, -3};
				type ='|';
			}
		} else if(type == '|'){
			if(!grid.getHoldsBlock(I_left, J_under) && !grid.getHoldsBlock(I_left +2, J_under)
					&& !grid.getHoldsBlock(I_left +3, J_under)) {
				widthIndex = new int[] {0, 1, 2, 3};
				heightIndex = new int[] {0, 0, 0, 0};
				type = '-';
			}
		} else if(type == 'L'){
			if(!grid.getHoldsBlock(I_left +2, J_under) && !grid.getHoldsBlock(I_left +2, J_under -1)){
				widthIndex = new int[] {0, 1, 2, 2};
				heightIndex = new int[] {0, 0, 0, -1};
				type = '�';
			}
		} else if(type == '�'){
			widthIndex = new int[] {0, 1, 1, 1};
			heightIndex = new int[] {-2, -2, -1, 0};
			type = '7';
		} else if(type == '7'){
			if(!grid.getHoldsBlock(I_left +2, J_under -1)){
				widthIndex = new int[] {0, 0, 1, 2};
				heightIndex = new int[] {0, -1, -1, -1};
				type = '^';
			}
		} else if(type == '^'){
			widthIndex = new int[] {0, 0, 0, 1};
			heightIndex = new int[] {-2, -1, 0, 0};
			type = 'L';
		} else if (type == 't'){
			widthIndex = new int[] {1, 1, 0, 1};
			heightIndex = new int[] {-2, -1, -1, 0};
			type = '4';
		}
		else if (type == '4'){
			if(!grid.getHoldsBlock(I_left +2,J_under -1)){
				widthIndex = new int[] {0, 1, 1, 2};
				heightIndex = new int[] {-1, -1, 0, -1};
				type = 'T';
			}
		} else if (type == 'T'){
			widthIndex = new int[] {0, 0, 1, 0};
			heightIndex = new int[] {-2, -1, -1, 0};
			type = '5';
		} else if (type == '5'){
			if(!grid.getHoldsBlock(I_left +2,J_under -1)){
				widthIndex = new int[] {0, 1, 1, 2};
				heightIndex = new int[] {0, 0, -1, 0};
				type = 't';
			}
		} else if (type == 's'){
			widthIndex = new int[] {0, 0, 1, 1};
			heightIndex = new int[] {-2, -1, -1, 0};
			type = 'S';
		} else if (type == 'S'){
			if(!grid.getHoldsBlock(I_left +2, J_under -1)){
				widthIndex = new int[] {0, 1, 1, 2};
				heightIndex = new int[] {0, 0, -1, -1};
				type = 's';
			}
		} else if (type == 'z'){
			widthIndex = new int[] {0, 0, 1, 1};
			heightIndex = new int[] {0, -1, -1, -2};
			type = 'Z';
		} else if (type == 'Z'){
			if(!grid.getHoldsBlock(I_left +2, J_under)){
				widthIndex = new int[] {0, 1, 1, 2};
				heightIndex = new int [] {-1, -1, 0, 0};
				type = 'z';
			}
		} else if(type == '0'){ // = mirrored L
			if(!grid.getHoldsBlock(I_left +2, J_under) && !grid.getHoldsBlock(I_left +2, J_under -1)){
				widthIndex = new int[] {0, 1, 2, 2};
				heightIndex = new int[] {-1, -1, -1, 0};
				type = '1';
			}
		} else if (type == '1'){
			widthIndex = new int[] {1, 0, 0, 0};
			heightIndex = new int[] {-2, -2, -1, 0};
			type = '2';
		} else if (type == '2'){
			if(!grid.getHoldsBlock(I_left +1, J_under) && !grid.getHoldsBlock(I_left +2, J_under)){
				widthIndex = new int[] {0, 0, 1, 2};
				heightIndex = new int[] {-1, 0, 0, 0};
				type = '3';
			}
		} else if (type == '3'){
			widthIndex = new int[] {1, 1, 1, 0};
			heightIndex = new int[] {-2, -1, 0, 0};
			type = '0';
		}

		changeBlockMatrix(grid, true);
	}

	void draw(Graphics graphics){
		for(Block block : blockList){
			block.i = I_left + widthIndex[blockList.indexOf(block)];
			block.j = J_under + heightIndex[blockList.indexOf(block)];
			block.setPosition(block.i, block.j);
			block.draw(graphics);
		}
	}

}