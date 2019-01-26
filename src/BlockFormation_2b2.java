import java.awt.*;

class BlockFormation_2b2 extends BlockFormation {

    BlockFormation_2b2(Grid grid) {
        super(grid);
        setWidthIndices(new int[]{0, 1, 0, 1});
        setHeightIndices(new int[]{-1, -1, 0, 0});
        J_under = 2;
        color = new Color(240, 240, 10);
        type = Sort.TWO_BY_TWO;
    }

    boolean checkBelow(Grid grid) {
        return (grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under + 1));
    }

    boolean checkRight(Grid grid) {
        return (!grid.getHoldsBlock(I_left + 2, J_under) && !grid.getHoldsBlock(I_left + 2, J_under - 1));
    }

    boolean checkLeft(Grid grid) {
        return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left - 1, J_under - 1));
    }
}
