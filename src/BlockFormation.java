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

	boolean canMove(int right, int down) {
		outerloop:
		for (Block block_outer : blockList) {
			// Check if any blocks from this formation is in check-direction from block_outer
			for (Block block_inner : blockList){
				if (block_inner.geti() == block_outer.geti() + right && block_inner.getj() == block_outer.getj() + down) {
					continue outerloop;
				}
			}
			// return false when occupied
			if (grid.getHoldsBlock(block_outer.geti() + right, block_outer.getj() + down)){
				return false;
			}
		}
		return true;
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

	void rotate() {
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

	void updateCoordinatesPerBlock() {
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