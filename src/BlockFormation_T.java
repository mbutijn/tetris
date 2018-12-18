import java.awt.*;

class BlockFormation_T extends BlockFormation{
    BlockFormation_T(BlockFormation bf) {
        super(bf);
        bf.widthIndex = new int[]{0, 1, 1, 2};
        bf.heightIndex = new int[]{0, 0, -1, 0};
        bf.J_under = 2;
        bf.color = new Color(200, 0, 200);
    }

    static boolean checkBelow(Grid grid) {
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

    static boolean checkRight(Grid grid) {
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

    static boolean checkLeft(Grid grid) {
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

    static void rotate(Grid grid) {
        if (orientation == Orientation.FIRST) {
            blockFormation.widthIndex = new int[]{1, 1, 0, 1};
            blockFormation.heightIndex = new int[]{-2, -1, -1, 0};
            orientation = Orientation.SECOND;
        } else if (orientation == Orientation.SECOND) {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 1, 1, 2};
                blockFormation.heightIndex = new int[]{-1, -1, 0, -1};
                orientation = Orientation.THIRD;
            }
        } else if (orientation == Orientation.THIRD) {
            blockFormation.widthIndex = new int[]{0, 0, 1, 0};
            blockFormation.heightIndex = new int[]{-2, -1, -1, 0};
            orientation = Orientation.FOURTH;
        } else if (orientation == Orientation.FOURTH) {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 1, 1, 2};
                blockFormation.heightIndex = new int[]{0, 0, -1, 0};
                orientation = Orientation.FIRST;
            }
        }
    }
}
