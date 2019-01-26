import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BlockFormation extends ArrayList<Block> {

	private static final long serialVersionUID = 1L;
	private List<Block> blockList = new ArrayList<>();
	private Orientation orientation;
	private Grid grid;
	int[] widthIndices, heightIndices;
	int I_left, J_under;
	Sort type;
	Color color;

	BlockFormation(Grid grid){
		this.I_left = (int) Math.round(1 + Math.random()*(Grid.widthNumber - 5));
		this.orientation = Orientation.FIRST;
		this.grid = grid;
	}

	void makeBlockList(Grid grid){
		for (int k = 0; k < 4; k++) {
			Block block = new Block(widthIndices[k], heightIndices[k], color);
			blockList.add(block);
			grid.setHoldsBlock(I_left + block.geti(), J_under + block.getj(), true);
		}
	}

	boolean IsBelowOccupied() {
		if (type == Sort.ONE_BY_FOUR){
			BlockFormation_1b4 bf = (BlockFormation_1b4) this;
			return bf.checkBelow(grid, orientation);
		} else if (type == Sort.TWO_BY_TWO) {
			BlockFormation_2b2 bf = (BlockFormation_2b2) this;
			return bf.checkBelow(grid);
		} else if (type == Sort.L){
			BlockFormation_L bf = (BlockFormation_L) this;
			return bf.checkBelow(grid, orientation);
		} else if (type == Sort.T) {
			BlockFormation_T bf = (BlockFormation_T) this;
			return bf.checkBelow(grid, orientation);
		}  else if (type == Sort.S) {
			BlockFormation_S bf = (BlockFormation_S) this;
			return bf.checkBelow(grid, orientation);
		} else if (type == Sort.Z) {
			BlockFormation_Z bf = (BlockFormation_Z) this;
			return bf.checkBelow(grid, orientation);
		} else {
			BlockFormation_J bf = (BlockFormation_J) this;
			return bf.checkBelow(grid, orientation);
		}
	}

	boolean IsRightFree() {
		if (type == Sort.ONE_BY_FOUR) {
			BlockFormation_1b4 bf = (BlockFormation_1b4) this;
			return bf.checkRight(grid, orientation);
		} else if (type == Sort.TWO_BY_TWO) {
			BlockFormation_2b2 bf = (BlockFormation_2b2) this;
			return bf.checkRight(grid);
		} else if (type == Sort.L) {
			BlockFormation_L bf = (BlockFormation_L) this;
			return bf.checkRight(grid, orientation);
		} else if (type == Sort.T){
			BlockFormation_T bf = (BlockFormation_T) this;
			return bf.checkRight(grid, orientation);
		} else if (type == Sort.S) {
			BlockFormation_S bf = (BlockFormation_S) this;
			return bf.checkRight(grid, orientation);
		} else if (type == Sort.Z) {
			BlockFormation_Z bf = (BlockFormation_Z) this;
			return bf.checkRight(grid, orientation);
		} else {
			BlockFormation_J bf = (BlockFormation_J) this;
			return bf.checkRight(grid, orientation);
		}
	}

	boolean IsLeftFree() {
		if (type == Sort.ONE_BY_FOUR) {
			BlockFormation_1b4 bf = (BlockFormation_1b4) this;
			return bf.checkLeft(grid, orientation);
		} else if (type == Sort.TWO_BY_TWO) {
			BlockFormation_2b2 bf = (BlockFormation_2b2) this;
			return bf.checkLeft(grid);
		} else if (type == Sort.L) {
			BlockFormation_L bf = (BlockFormation_L) this;
			return bf.checkLeft(grid, orientation);
		} else if (type == Sort.T){
			BlockFormation_T bf = (BlockFormation_T) this;
			return bf.checkLeft(grid, orientation);
		} else if (type == Sort.S) {
			BlockFormation_S bf = (BlockFormation_S) this;
			return bf.checkLeft(grid, orientation);
		} else if (type == Sort.Z) {
			BlockFormation_Z bf = (BlockFormation_Z) this;
			return bf.checkLeft(grid, orientation);
		} else {
			BlockFormation_J bf = (BlockFormation_J) this;
			return bf.checkLeft(grid, orientation);
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
			BlockFormation_1b4 bf = (BlockFormation_1b4) this;
			orientation = bf.rotate(grid, orientation);
		} else if (type == Sort.L) {
			BlockFormation_L bf = (BlockFormation_L) this;
			orientation = bf.rotate(grid, orientation);
		} else if (type == Sort.T) {
			BlockFormation_T bf = (BlockFormation_T) this;
			orientation = bf.rotate(grid, orientation);
		} else if (type == Sort.S) {
			BlockFormation_S bf = (BlockFormation_S) this;
			orientation = bf.rotate(grid, orientation);
		} else if (type == Sort.Z) {
			BlockFormation_Z bf = (BlockFormation_Z) this;
			orientation = bf.rotate(grid, orientation);
		} else if (type == Sort.J) {
			BlockFormation_J bf = (BlockFormation_J) this;
			orientation = bf.rotate(grid, orientation);
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