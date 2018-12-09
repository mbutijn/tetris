import java.awt.*;

class BlockFormation_2b2 extends BlockFormation {

    BlockFormation_2b2(BlockFormation bf) {
        super(bf);
        bf.widthIndex = new int[]{0, 1, 0, 1};
        bf.heightIndex = new int[]{-1, -1, 0, 0};
        bf.J_under = 2;
        bf.color = new Color(240, 240, 10);
    }

    static boolean checkBelow(Grid grid) {
        return (grid.getHoldsBlock(blockFormation.I_left, blockFormation.J_under + 1) || grid.getHoldsBlock(blockFormation.I_left + 1, blockFormation.J_under + 1));
    }

    static boolean checkRight(Grid grid) {
        return (!grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left + 2, blockFormation.J_under - 1));
    }

    static boolean checkLeft(Grid grid) {
        return (!grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under) && !grid.getHoldsBlock(blockFormation.I_left - 1, blockFormation.J_under - 1));
    }
}
