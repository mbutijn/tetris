import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BlockFormation extends ArrayList<Block> {

	private static final long serialVersionUID = 1L;
	static BlockFormation blockFormation;
	Color color;
	private static Sort type;
	private static List<Block> blockList;
	int[] widthIndex, heightIndex;
	int I_left, J_under;
	static Orientation orientation;

	BlockFormation(BlockFormation bf){
		blockFormation = bf;
	}

	BlockFormation(int I_left, Sort type, Grid grid) {
		blockList = new ArrayList<>();
		this.I_left = I_left;
		this.type = type;
		if (type == Sort.ONE_BY_FOUR) {
			new BlockFormation_1b4(this);
		} else if (type == Sort.TWO_BY_TWO) {
			new BlockFormation_2b2(this);
		} else if (type == Sort.L) {
			new BlockFormation_L(this);
		} else if (type == Sort.T) {
			new BlockFormation_T(this);
		} else if (type == Sort.S) {
			new BlockFormation_S(this);
		} else if (type == Sort.Z) {
			new BlockFormation_Z(this);
		} else if (type == Sort.J) {
			new BlockFormation_J(this);
		}

		orientation = Orientation.FIRST;

		for (int k = 0; k < 4; k++) {
			Block block = new Block(widthIndex[k], heightIndex[k], color);
			blockList.add(block);
			grid.setHoldsBlock(I_left + block.i, J_under + block.j, true);
		}
	}

	static boolean IsBelowOccupied(Grid grid) {
		if (type == Sort.ONE_BY_FOUR){
			return BlockFormation_1b4.checkBelow(grid);
		} else if (type == Sort.TWO_BY_TWO) {
			return BlockFormation_2b2.checkBelow(grid);
		} else if (type == Sort.L){
			return BlockFormation_L.checkBelow(grid);
		} else if (type == Sort.T) {
			return BlockFormation_T.checkBelow(grid);
		}  else if (type == Sort.S) {
			return BlockFormation_S.checkBelow(grid);
		} else if (type == Sort.Z) {
			return BlockFormation_Z.checkBelow(grid);
		} else {
			return BlockFormation_J.checkBelow(grid);
		}
	}

	static boolean IsRightFree(Grid grid) {
		if (type == Sort.ONE_BY_FOUR) {
			return BlockFormation_1b4.checkRight(grid);
		} else if (type == Sort.TWO_BY_TWO) {
			return BlockFormation_2b2.checkRight(grid);
		} else if (type == Sort.L) {
			return BlockFormation_L.checkRight(grid);
		} else if (type == Sort.T){
			return BlockFormation_T.checkRight(grid);
		} else if (type == Sort.S) {
			return BlockFormation_S.checkRight(grid);
		} else if (type == Sort.Z) {
			return BlockFormation_Z.checkRight(grid);
		} else {
			return BlockFormation_J.checkRight(grid);
		}
	}

	static boolean IsLeftFree(Grid grid) {
		if (type == Sort.ONE_BY_FOUR) {
			return BlockFormation_1b4.checkLeft(grid);
		} else if (type == Sort.TWO_BY_TWO) {
			return BlockFormation_2b2.checkLeft(grid);
		} else if (type == Sort.L) {
			return BlockFormation_L.checkLeft(grid);
		} else if (type == Sort.T){
			return BlockFormation_T.checkLeft(grid);
		} else if (type == Sort.S) {
			return BlockFormation_S.checkLeft(grid);
		} else if (type == Sort.Z) {
			return BlockFormation_Z.checkLeft(grid);
		} else {
			return BlockFormation_J.checkLeft(grid);
		}
	}

	void moveOneTile(Grid grid, Direction direction) {
		changeBlockMatrix(grid, false);

		if (direction == Direction.RIGHT) {
			I_left++;
		} else if (direction == Direction.LEFT) {
			I_left--;
		} else if (direction == Direction.DOWN) {
			J_under++;
		}
		changeBlockMatrix(grid, true);
	}

	private static void changeBlockMatrix(Grid grid, boolean occupied) {
		for (Block block : blockList) {
			grid.setHoldsBlock(blockFormation.I_left + blockFormation.widthIndex[blockList.indexOf(block)], blockFormation.J_under + blockFormation.heightIndex[blockList.indexOf(block)], occupied);
		}
	}

	void layDownBlocks(Field field) {
		for (Block block : blockList) {
			Field.groundBlocks.add(block);
			if (block.j == 1) {
				field.stopGame();
				break;
			}
		}
	}

	static void rotate(Grid grid) {
		changeBlockMatrix(grid, false);

		if (type == Sort.ONE_BY_FOUR) {
			BlockFormation_1b4.rotate(grid);
		} else if (type == Sort.L) {
			BlockFormation_L.rotate(grid);
		} else if (type == Sort.T) {
			BlockFormation_T.rotate(grid);
		} else if (type == Sort.S) {
			BlockFormation_S.rotate(grid);
		} else if (type == Sort.Z) {
			BlockFormation_Z.rotate(grid);
		} else if (type == Sort.J) {
			BlockFormation_J.rotate(grid);
		}

		changeBlockMatrix(grid, true);
	}

	void draw(Graphics graphics) {
		for (Block block : blockList) {
			block.i = I_left + widthIndex[blockList.indexOf(block)];
			block.j = J_under + heightIndex[blockList.indexOf(block)];
			block.setPosition(block.i, block.j);
			block.draw(graphics);
		}
	}

	void setWidthIndex(int[] widthIndex) {
		this.widthIndex = widthIndex;
	}

	void setHeightIndex(int[] heightIndex) {
		this.heightIndex = heightIndex;
	}

}