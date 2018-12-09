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
        if (blockFormation.type == 't') {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        } else if (blockFormation.type == '4') {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left +1, blockFormation.J_under +1));
        } else if (blockFormation.type == 'T') {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left +1, blockFormation.J_under +1) || grid.getHoldsBlock(blockFormation.I_left +2, blockFormation.J_under));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under +1) || grid.getHoldsBlock(blockFormation.I_left +1, blockFormation.J_under));
        }
    }

    static boolean checkRight(Grid grid) {
        if (blockFormation.type == 't') {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under));
        } else if (blockFormation.type == '4') {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under));
        } else if (blockFormation.type == 'T'){
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        }
    }

    static boolean checkLeft(Grid grid) {
        if (blockFormation.type == 't') {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 1));
        } else if (blockFormation.type == '4') {
            return (!grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under));
        } else if (blockFormation.type == 'T'){
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 2));
        }
    }

    static void rotate(Grid grid) {
        if (blockFormation.type == 't') {
            blockFormation.widthIndex = new int[]{1, 1, 0, 1};
            blockFormation.heightIndex = new int[]{-2, -1, -1, 0};
            blockFormation.type = '4';
        } else if (blockFormation.type == '4') {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 1, 1, 2};
                blockFormation.heightIndex = new int[]{-1, -1, 0, -1};
                blockFormation.type = 'T';
            }
        } else if (blockFormation.type == 'T') {
            blockFormation.widthIndex = new int[]{0, 0, 1, 0};
            blockFormation.heightIndex = new int[]{-2, -1, -1, 0};
            blockFormation.type = '5';
        } else if (blockFormation.type == '5') {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1)) {
                blockFormation.widthIndex = new int[]{0, 1, 1, 2};
                blockFormation.heightIndex = new int[]{0, 0, -1, 0};
                blockFormation.type = 't';
            }
        }
    }
}
