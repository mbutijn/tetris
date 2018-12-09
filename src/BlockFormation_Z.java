import java.awt.*;

class BlockFormation_Z extends BlockFormation{
    BlockFormation_Z(BlockFormation bf) {
        super(bf);
        bf.widthIndex = new int[]{0, 1, 1, 2};
        bf.heightIndex = new int[]{-1, -1, 0, 0};
        bf.J_under = 2;
        bf.color = new Color(30, 240, 40);
    }

    static boolean checkBelow(Grid grid) {
        if (blockFormation.type == 'z') {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under + 1));
        } else {
            return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        }
    }

    static boolean checkRight(Grid grid) {
        if (blockFormation.type == 'z') {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 3, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under));
        }
    }

    static boolean checkLeft(Grid grid) {
        if (blockFormation.type == 'z') {
            return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under));
        } else {
            return (!grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under - 2) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under));
        }
    }

    static void rotate(Grid grid) {
        if (blockFormation.type == 'z') {
            blockFormation.widthIndex = new int[]{0, 0, 1, 1};
            blockFormation.heightIndex = new int[]{0, -1, -1, -2};
            blockFormation.type = 'Z';
        } else if (blockFormation.type == 'Z') {
            if (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under)) {
                blockFormation.widthIndex = new int[]{0, 1, 1, 2};
                blockFormation.heightIndex = new int[]{-1, -1, 0, 0};
                blockFormation.type = 'z';
            }
        }
    }
}
