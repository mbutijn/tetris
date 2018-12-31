import java.awt.*;

class BlockFormation_T extends BlockFormation{
    BlockFormation_T(BlockFormation bf) {
        super(bf);
        bf.widthIndices = new int[]{0, 1, 1, 2};
        bf.heightIndices = new int[]{0, 0, -1, 0};
        bf.J_under = 2;
        bf.color = new Color(200, 0, 200);
    }

    static boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        } else if (orientation == Orientation.SECOND) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left +1, blockFormation.J_under +1));
        } else if (orientation == Orientation.THIRD) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left +1, blockFormation.J_under +1) || grid.getHoldsBlock(blockFormation.I_left +2, blockFormation.J_under));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under +1) || grid.getHoldsBlock(blockFormation.I_left +1, blockFormation.J_under));
        }
    }

    static boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under));
        } else if (orientation == Orientation.SECOND) {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        }
    }

    static boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1));
        } else if (orientation == Orientation.SECOND) {
            return (!grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 2));
        }
    }

    static Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            blockFormation.widthIndices = new int[]{1, 1, 0, 1};
            blockFormation.heightIndices = new int[]{-2, -1, -1, 0};
            return Orientation.SECOND;
        } else if (orientation == Orientation.SECOND) {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndices = new int[]{0, 1, 1, 2};
                blockFormation.heightIndices = new int[]{-1, -1, 0, -1};
                return Orientation.THIRD;
            } else {
                return orientation;
            }
        } else if (orientation == Orientation.THIRD) {
            blockFormation.widthIndices = new int[]{0, 0, 1, 0};
            blockFormation.heightIndices = new int[]{-2, -1, -1, 0};
            return Orientation.FOURTH;
        } else {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndices = new int[]{0, 1, 1, 2};
                blockFormation.heightIndices = new int[]{0, 0, -1, 0};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }
}
