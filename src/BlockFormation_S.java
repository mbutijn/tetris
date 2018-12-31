import java.awt.*;

class BlockFormation_S extends BlockFormation{
    BlockFormation_S(BlockFormation bf) {
        super(bf);
        bf.widthIndices = new int[]{0, 1, 1, 2};
        bf.heightIndices = new int[]{0, 0, -1, -1};
        bf.J_under = 2;
        bf.color = new Color(230, 20, 20);
    }

    static boolean checkBelow(Grid grid) {
        if (orientation == Orientation.FIRST) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1));
        }
    }

    static boolean checkRight(Grid grid) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under - 1));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        }
    }

    static boolean checkLeft(Grid grid) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under));
        }
    }
    static void rotate(Grid grid) {
        if (orientation == Orientation.FIRST) {
            blockFormation.widthIndices = new int[]{0, 0, 1, 1};
            blockFormation.heightIndices = new int[]{-2, -1, -1, 0};
            orientation = Orientation.SECOND;
        } else if (orientation == Orientation.SECOND) {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndices = new int[]{0, 1, 1, 2};
                blockFormation.heightIndices = new int[]{0, 0, -1, -1};
                orientation = Orientation.FIRST;
            }
        }
    }
}
