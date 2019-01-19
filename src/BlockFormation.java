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
	private Orientation orientation;
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
			grid.setHoldsBlock(I_left + block.geti(), J_under + block.getj(), true);
		}
	}

	boolean IsBelowOccupied() {
		if (type == Sort.ONE_BY_FOUR){
			return BlockFormation_1b4.checkBelow(grid, orientation);
		} else if (type == Sort.TWO_BY_TWO) {
			return BlockFormation_2b2.checkBelow(grid);
		} else if (type == Sort.L){
			return BlockFormation_L.checkBelow(grid, orientation);
		} else if (type == Sort.T) {
			return BlockFormation_T.checkBelow(grid, orientation);
		}  else if (type == Sort.S) {
			return BlockFormation_S.checkBelow(grid, orientation);
		} else if (type == Sort.Z) {
			return BlockFormation_Z.checkBelow(grid, orientation);
		} else {
			return BlockFormation_J.checkBelow(grid, orientation);
		}
	}

	boolean IsRightFree() {
		if (type == Sort.ONE_BY_FOUR) {
			return BlockFormation_1b4.checkRight(grid, orientation);
		} else if (type == Sort.TWO_BY_TWO) {
			return BlockFormation_2b2.checkRight(grid);
		} else if (type == Sort.L) {
			return BlockFormation_L.checkRight(grid, orientation);
		} else if (type == Sort.T){
			return BlockFormation_T.checkRight(grid, orientation);
		} else if (type == Sort.S) {
			return BlockFormation_S.checkRight(grid, orientation);
		} else if (type == Sort.Z) {
			return BlockFormation_Z.checkRight(grid, orientation);
		} else {
			return BlockFormation_J.checkRight(grid, orientation);
		}
	}

	boolean IsLeftFree() {
		if (type == Sort.ONE_BY_FOUR) {
			return BlockFormation_1b4.checkLeft(grid, orientation);
		} else if (type == Sort.TWO_BY_TWO) {
			return BlockFormation_2b2.checkLeft(grid);
		} else if (type == Sort.L) {
			return BlockFormation_L.checkLeft(grid, orientation);
		} else if (type == Sort.T){
			return BlockFormation_T.checkLeft(grid, orientation);
		} else if (type == Sort.S) {
			return BlockFormation_S.checkLeft(grid, orientation);
		} else if (type == Sort.Z) {
			return BlockFormation_Z.checkLeft(grid, orientation);
		} else {
			return BlockFormation_J.checkLeft(grid, orientation);
		}
	}

	void moveOneTile(Direction direction) {
		updateBlockMatrix(grid, false);

		if (direction == Direction.RIGHT) {
			I_left++;
		} else if (direction == Direction.LEFT) {
			I_left--;
		} else if (direction == Direction.DOWN) {
			J_under++;
		}
		updateBlockMatrix(grid, true);
	}

	private void updateBlockMatrix(Grid grid, boolean occupied) {
		for (Block block : blockList) {
			grid.setHoldsBlock(I_left + widthIndices[blockList.indexOf(block)], J_under + heightIndices[blockList.indexOf(block)], occupied);
		}
	}

	void layDownBlocks(Field field) {
		for (Block block : blockList) {
			field.groundBlocks.add(block);
			if (block.getj() == 1) {
				field.stopGame();
				break;
			}
		}
	}

	void rotate_bl() {
		updateBlockMatrix(grid, false);

		if (type == Sort.ONE_BY_FOUR) {
			orientation = BlockFormation_1b4.rotate(grid, orientation);
		} else if (type == Sort.L) {
			orientation = BlockFormation_L.rotate(grid, orientation);
		} else if (type == Sort.T) {
			orientation = BlockFormation_T.rotate(grid, orientation);
		} else if (type == Sort.S) {
			orientation = BlockFormation_S.rotate(grid, orientation);
		} else if (type == Sort.Z) {
			orientation = BlockFormation_Z.rotate(grid, orientation);
		} else if (type == Sort.J) {
			orientation = BlockFormation_J.rotate(grid, orientation);
		}

		updateBlockMatrix(grid, true);
	}

	void setCoordinates() {
		for (Block block : blockList) {
			block.seti(I_left + widthIndices[blockList.indexOf(block)]);
			block.setj(J_under + heightIndices[blockList.indexOf(block)]);
		}
	}

	void render(Graphics graphics) {
		for (Block block : blockList) {
			block.updatePosition();
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