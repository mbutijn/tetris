import java.awt.*;

class BlockFormation_S extends BlockFormation{
    BlockFormation_S(Grid grid) {
        super(grid);
        setWidthIndices(new int[]{0, 1, 1, 2});
        setHeightIndices(new int[]{0, 0, -1, -1});
        J_under = 2;
        color = new Color(230, 20, 20);
        type = Sort.S;
    }

    boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under + 1) || grid.getHoldsBlock(I_left + 2, J_under));
        } else {
            return (grid.getHoldsBlock(I_left, J_under) || grid.getHoldsBlock(I_left + 1, J_under + 1));
        }
    }

    boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(I_left + 2, J_under) && !grid.getHoldsBlock(I_left + 3, J_under - 1));
        } else {
            return (!grid.getHoldsBlock(I_left + 1, J_under - 2) && !grid.getHoldsBlock(I_left + 2, J_under - 1) && !grid.getHoldsBlock(I_left + 2, J_under));
        }
    }

    boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left, J_under - 1));
        } else {
            return (!grid.getHoldsBlock(I_left - 1, J_under - 2) && !grid.getHoldsBlock(I_left - 1, J_under - 1) && !grid.getHoldsBlock(I_left, J_under));
        }
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            widthIndices = new int[]{0, 0, 1, 1};
            heightIndices = new int[]{-2, -1, -1, 0};
            return Orientation.SECOND;
        } else {
            if (!grid.getHoldsBlock(I_left + 2, J_under - 1)) {
                widthIndices = new int[]{0, 1, 1, 2};
                heightIndices = new int[]{0, 0, -1, -1};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }
}
