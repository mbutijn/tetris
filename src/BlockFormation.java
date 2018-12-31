import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BlockFormation extends ArrayList<Block> {

	private static final long serialVersionUID = 1L;
	static BlockFormation blockFormation;
	Color color;
	private Sort type;
	private List<Block> blockList = new ArrayList<>();
	int[] widthIndices, heightIndices;
	int I_left, J_under;
	static Orientation orientation;
	private Grid grid;

	BlockFormation(BlockFormation bf){
		blockFormation = bf;
	}

	BlockFormation(int I_left, Sort type, Grid grid) {
		this.I_left = I_left;
		this.type = type;
		orientation = Orientation.FIRST;
		this.grid = grid;

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
		} else {
			new BlockFormation_J(this);
		}

		for (int k = 0; k < 4; k++) {
			Block block = new Block(widthIndices[k], heightIndices[k], color);
			blockList.add(block);
			grid.setHoldsBlock(I_left + block.i, J_under + block.j, true);
		}
	}

	boolean IsBelowOccupied() {
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

	boolean IsRightFree() {
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

	boolean IsLeftFree() {
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

	void moveOneTile(Direction direction) {
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

	private void changeBlockMatrix(Grid grid, boolean occupied) {
		for (Block block : blockList) {
			grid.setHoldsBlock(I_left + widthIndices[blockList.indexOf(block)], J_under + heightIndices[blockList.indexOf(block)], occupied);
		}
	}

	void layDownBlocks(Field field) {
		for (Block block : blockList) {
			field.groundBlocks.add(block);
			if (block.j == 1) {
				field.stopGame();
				break;
			}
		}
	}

	void rotate_bl() {
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

	void setCoordinates() {
		for (Block block : blockList) {
			block.i = I_left + widthIndices[blockList.indexOf(block)];
			block.j = J_under + heightIndices[blockList.indexOf(block)];
		}
	}

	void render(Graphics graphics) {
		for (Block block : blockList) {
			block.setPosition();
			block.render(graphics);
		}
	}

	void setWidthIndices(int[] widthIndices) {
		this.widthIndices = widthIndices;
	}

	void setHeightIndices(int[] heightIndices) {
		this.heightIndices = heightIndices;
	}

}