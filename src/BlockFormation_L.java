import java.awt.*;

class BlockFormation_L extends BlockFormation{

    BlockFormation_L(BlockFormation bf) {
        super(bf);
        bf.widthIndex = new int[]{0, 0, 0, 1};
        bf.heightIndex = new int[]{-2, -1, 0, 0};
        bf.J_under = 3;
        bf.color = new Color(230, 140, 20);
    }

    static boolean checkBelow(Grid grid) {
        if (blockFormation.type == 'L'){
            return grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1);
        } else if (blockFormation.type == '1') {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        } else if (blockFormation.type == '7') {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        }
    }

    static boolean checkRight(Grid grid) {
        if (blockFormation.type == 'L') {
            return (!grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        } else if (blockFormation.type == '1') {
            return (!grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under - 1));
        } else if (blockFormation.type == '7'){
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under - 1));
        }
    }

    static boolean checkLeft(Grid grid) {
        if (blockFormation.type == 'L') {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 2));
        } else if (blockFormation.type == '1') {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 1));
        } else if (blockFormation.type == '7'){
            return (!grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 2));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1));
        }
    }

    static void rotate(Grid grid) {
        if (blockFormation.type == 'L') {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 1, 2, 2};
                blockFormation.heightIndex = new int[]{0, 0, 0, -1};
                blockFormation.type = '1';
            }
        } else if (blockFormation.type == '1') {
            blockFormation.widthIndex = new int[]{0, 1, 1, 1};
            blockFormation.heightIndex = new int[]{-2, -2, -1, 0};
            blockFormation.type = '7';
        } else if (blockFormation.type == '7') {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 0, 1, 2};
                blockFormation.heightIndex = new int[]{0, -1, -1, -1};
                blockFormation.type = '^';
            }
        } else if (blockFormation.type == '^') {
            blockFormation.widthIndex = new int[]{0, 0, 0, 1};
            blockFormation.heightIndex = new int[]{-2, -1, 0, 0};
            blockFormation.type = 'L';
        }
    }

}
