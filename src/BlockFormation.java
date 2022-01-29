import java.util.ArrayList;
import java.util.List;

class BlockFormation extends ArrayList<Block> {

	private static final long serialVersionUID = 1L;
	private final List<Block> blockList = new ArrayList<>();
	private Orientation orientation;
	private final Grid grid;
	int[] widthIndices, heightIndices;
	int I_left, J_under;
	Sort type;
	public int index;

	BlockFormation(Grid grid, int index){
		this.I_left = (int) Math.round(1 + Math.random()*(Grid.widthNumber - 5));
		this.orientation = Orientation.FIRST;
		this.grid = grid;
		this.index = index;
	}

	void makeBlockList(Grid grid){
		for (int k = 0; k < 4; k++) {
			Block block = new Block(widthIndices[k], heightIndices[k]);
			blockList.add(block);
			grid.setHoldsBlock(I_left + block.geti(), J_under + block.getj(), index);
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
			if (grid.getHoldsBlock(block_outer.geti() + right, block_outer.getj() + down) != 0){
				return false;
			}
		}
		return true;
	}

	void moveOneTile(Direction direction) {
		updateBlockMatrix(grid, 0);

		if (direction == Direction.RIGHT) {
			I_left++;
		} else if (direction == Direction.LEFT) {
			I_left--;
		} else if (direction == Direction.DOWN) {
			J_under++;
		}
		updateBlockMatrix(grid, index);
	}

	private void updateBlockMatrix(Grid grid, int index) {
		for (Block block : blockList) {
			grid.setHoldsBlock(I_left + widthIndices[blockList.indexOf(block)], J_under + heightIndices[blockList.indexOf(block)], index);
		}
	}

	void rotate() {
		updateBlockMatrix(grid, 0);

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

		updateBlockMatrix(grid, index);
	}

	void updateCoordinatesPerBlock() {
		for (Block block : blockList) {
			block.seti(I_left + widthIndices[blockList.indexOf(block)]);
			block.setj(J_under + heightIndices[blockList.indexOf(block)]);
		}
	}

	void setWidthIndices(int[] widthIndices) {
		this.widthIndices = widthIndices;
	}

	void setHeightIndices(int[] heightIndices) {
		this.heightIndices = heightIndices;
	}

}