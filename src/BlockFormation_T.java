import java.awt.*;

class BlockFormation_T extends BlockFormation{
    BlockFormation_T(Grid grid) {
        super(grid);
        setWidthIndices(new int[]{0, 1, 1, 2});
        setHeightIndices(new int[]{0, 0, -1, 0});
        J_under = 2;
        color = new Color(200, 0, 200);
        type = Sort.T;
    }

    boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under + 1) || grid.getHoldsBlock(I_left + 2, J_under + 1));
        } else if (orientation == Orientation.SECOND) {
            return (grid.getHoldsBlock(I_left, J_under) || grid.getHoldsBlock(I_left +1, J_under +1));
        } else if (orientation == Orientation.THIRD) {
            return (grid.getHoldsBlock(I_left, J_under) || grid.getHoldsBlock(I_left +1, J_under +1) || grid.getHoldsBlock(I_left +2, J_under));
        } else {
            return (grid.getHoldsBlock(I_left, J_under +1) || grid.getHoldsBlock(I_left +1, J_under));
        }
    }

    boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(I_left + 2, J_under - 1) && !grid.getHoldsBlock(I_left + 3, J_under));
        } else if (orientation == Orientation.SECOND) {
            return (!grid.getHoldsBlock(I_left + 2, J_under - 2) && !grid.getHoldsBlock(I_left + 2, J_under - 1) && !grid.getHoldsBlock(I_left + 2, J_under));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(I_left + 2, J_under + 1) && !grid.getHoldsBlock(I_left + 3, J_under));
        } else {
            return (!grid.getHoldsBlock(I_left + 1, J_under - 2) && !grid.getHoldsBlock(I_left + 2, J_under - 1) && !grid.getHoldsBlock(I_left + 1, J_under));
        }
    }

    boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left, J_under - 1));
        } else if (orientation == Orientation.SECOND) {
            return (!grid.getHoldsBlock(I_left, J_under - 2) && !grid.getHoldsBlock(I_left - 1, J_under - 1) && !grid.getHoldsBlock(I_left, J_under));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left, J_under + 1));
        } else {
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left - 1, J_under - 1) && !grid.getHoldsBlock(I_left - 1, J_under - 2));
        }
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            widthIndices = new int[]{1, 1, 0, 1};
            heightIndices = new int[]{-2, -1, -1, 0};
            return Orientation.SECOND;
        } else if (orientation == Orientation.SECOND) {
            if (!grid.getHoldsBlock(I_left + 2, J_under - 1)) {
                widthIndices = new int[]{0, 1, 1, 2};
                heightIndices = new int[]{-1, -1, 0, -1};
                return Orientation.THIRD;
            } else {
                return orientation;
            }
        } else if (orientation == Orientation.THIRD) {
            widthIndices = new int[]{0, 0, 1, 0};
            heightIndices = new int[]{-2, -1, -1, 0};
            return Orientation.FOURTH;
        } else {
            if (!grid.getHoldsBlock(I_left + 2, J_under - 1)) {
                widthIndices = new int[]{0, 1, 1, 2};
                heightIndices = new int[]{0, 0, -1, 0};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }
}
