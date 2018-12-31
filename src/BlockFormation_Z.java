import java.awt.*;

class BlockFormation_Z extends BlockFormation{
    BlockFormation_Z(BlockFormation bf) {
        super(bf);
        bf.widthIndices = new int[]{0, 1, 1, 2};
        bf.heightIndices = new int[]{-1, -1, 0, 0};
        bf.J_under = 2;
        bf.color = new Color(30, 240, 40);
    }

    static boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        }
    }

    static boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        }
    }

    static boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under));
        }
    }

    static Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            blockFormation.widthIndices = new int[]{0, 0, 1, 1};
            blockFormation.heightIndices = new int[]{0, -1, -1, -2};
            return Orientation.SECOND;
        } else {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under)) {
                blockFormation.widthIndices = new int[]{0, 1, 1, 2};
                blockFormation.heightIndices = new int[]{-1, -1, 0, 0};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }
}
