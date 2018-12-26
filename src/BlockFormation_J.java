import java.awt.*;

class BlockFormation_J extends BlockFormation{
    BlockFormation_J(BlockFormation bf) {
        super(bf);
        bf.widthIndex = new int[]{1, 1, 1, 0};
        bf.heightIndex = new int[]{-2, -1, 0, 0};
        bf.J_under = 3;
        bf.color = new Color(30, 30, 255);
    }

    static boolean checkBelow(Grid grid) {
        if (orientation == Orientation.FIRST) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1));
        } else if (orientation == Orientation.SECOND) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        } else if (orientation == Orientation.THIRD) {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 1));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        }
    }

    static boolean checkRight(Grid grid) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        } else if (orientation == Orientation.SECOND){
            return (!grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under - 1));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 1));
        }
    }

    static boolean checkLeft(Grid grid) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 2));
        } else if (orientation == Orientation.SECOND){
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 2));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1));
        }
    }

    static void rotate(Grid grid) {
        if (orientation == Orientation.FIRST) {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 1, 2, 2};
                blockFormation.heightIndex = new int[]{-1, -1, -1, 0};
                orientation = Orientation.SECOND;;
            }
        } else if (orientation == Orientation.SECOND) {
            blockFormation.widthIndex = new int[]{1, 0, 0, 0};
            blockFormation.heightIndex = new int[]{-2, -2, -1, 0};
            orientation = Orientation.THIRD;;
        } else if (orientation == Orientation.THIRD) {
            if (!grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under)) {
                blockFormation.widthIndex = new int[]{0, 0, 1, 2};
                blockFormation.heightIndex = new int[]{-1, 0, 0, 0};
                orientation = Orientation.FOURTH;;
            }
        } else if (orientation == Orientation.FOURTH) {
            blockFormation.widthIndex = new int[]{1, 1, 1, 0};
            blockFormation.heightIndex = new int[]{-2, -1, 0, 0};
            orientation = Orientation.FIRST;
        }
    }
}