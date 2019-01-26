import java.awt.*;

class BlockFormation_1b4 extends BlockFormation {

    BlockFormation_1b4(Grid grid){
        super(grid);
        setWidthIndices(new int[] {0, 1, 2, 3});
        setHeightIndices(new int[] {0, 0, 0, 0});
        J_under = 1;
        color = new Color(0, 235, 235);
        type = Sort.ONE_BY_FOUR;
    }

    boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under + 1) ||
                    grid.getHoldsBlock(I_left + 2, J_under + 1) || grid.getHoldsBlock(I_left + 3, J_under + 1);
        } else {
            return grid.getHoldsBlock(I_left + 1, J_under + 1);
        }
    }

    boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return !grid.getHoldsBlock(I_left + 4, J_under);
        } else {
            return !grid.getHoldsBlock(I_left + 2, J_under - 3) && !grid.getHoldsBlock(I_left + 2, J_under - 2) &&
                    !grid.getHoldsBlock(I_left + 2, J_under - 1) && !grid.getHoldsBlock(I_left + 2, J_under);
        }
    }

    boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return !grid.getHoldsBlock(I_left - 1, J_under);
        } else {
            return !grid.getHoldsBlock(I_left, J_under - 3) && !grid.getHoldsBlock(I_left, J_under - 2) && !grid.getHoldsBlock(I_left, J_under - 1) && !grid.getHoldsBlock(I_left, J_under);
        }
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST){
            if (J_under > 3 && J_under < Grid.heightNumber) {
                widthIndices = new int[]{1, 1, 1, 1};
                heightIndices = new int[]{0, -1, -2, -3};
                return Orientation.SECOND;
            } else {
                return orientation;
            }
        } else {
            if (!grid.getHoldsBlock(I_left, J_under) && !grid.getHoldsBlock(I_left + 2, J_under)
                    && !grid.getHoldsBlock(I_left + 3, J_under)) {
                widthIndices = new int[]{0, 1, 2, 3};
                heightIndices = new int[]{0, 0, 0, 0};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }

}
