import java.awt.*;

class BlockFormation_1b4 extends BlockFormation {

    BlockFormation_1b4(BlockFormation bf){
        super(bf);
        bf.setWidthIndices(new int[] {0, 1, 2, 3});
        bf.setHeightIndices(new int[] {0, 0, 0, 0});
        bf.J_under = 1;
        bf.color = new Color(0, 235, 235);
    }

    static boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) ||
                    grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under + 1);
        } else {
            return grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1);
        }
    }

    static boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return !grid.getHoldsBlock(blockFormation.I_left + 4, blockFormation.J_under);
        } else {
            return !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 3) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) &&
					!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under);
        }
    }

    static boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under);
        } else {
            return !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 3) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under);
        }
    }

    static Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST){
            if (blockFormation.J_under > 3 && blockFormation.J_under < Grid.heightNumber) {
                blockFormation.widthIndices = new int[]{1, 1, 1, 1};
                blockFormation.heightIndices = new int[]{0, -1, -2, -3};
                return Orientation.SECOND;
            } else {
                return orientation;
            }
		} else {
            if (!grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under)
                    && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under)) {
                blockFormation.widthIndices = new int[]{0, 1, 2, 3};
                blockFormation.heightIndices = new int[]{0, 0, 0, 0};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }

}
