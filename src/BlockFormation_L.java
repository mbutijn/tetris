import java.awt.*;

class BlockFormation_L extends BlockFormation{

    BlockFormation_L(Grid grid) {
        super(grid);
        setWidthIndices(new int[]{0, 0, 0, 1});
        setHeightIndices(new int[]{-2, -1, 0, 0});
        J_under = 3;
        color = new Color(230, 140, 20);
        type = Sort.L;
    }

    boolean checkBelow(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST){
            return grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under + 1);
        } else if (orientation == Orientation.SECOND) {
            return (grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under + 1) || grid.getHoldsBlock(I_left + 2, J_under + 1));
        } else if (orientation == Orientation.THIRD) {
            return (grid.getHoldsBlock(I_left, J_under - 1) || grid.getHoldsBlock(I_left + 1, J_under + 1));
        } else {
            return (grid.getHoldsBlock(I_left, J_under + 1) || grid.getHoldsBlock(I_left + 1, J_under) || grid.getHoldsBlock(I_left + 2, J_under));
        }
    }

    boolean checkRight(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(I_left + 1, J_under - 2) && !grid.getHoldsBlock(I_left + 1, J_under - 1) && !grid.getHoldsBlock(I_left + 2, J_under));
        } else if (orientation == Orientation.SECOND) {
            return (!grid.getHoldsBlock(I_left + 3, J_under) && !grid.getHoldsBlock(I_left + 3, J_under - 1));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(I_left + 2, J_under - 2) && !grid.getHoldsBlock(I_left + 2, J_under - 1) && !grid.getHoldsBlock(I_left + 2, J_under));
        } else {
            return (!grid.getHoldsBlock(I_left + 1, J_under) && !grid.getHoldsBlock(I_left + 3, J_under - 1));
        }
    }

    boolean checkLeft(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left - 1, J_under - 1) && !grid.getHoldsBlock(I_left - 1, J_under - 2));
        } else if (orientation == Orientation.SECOND) {
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left + 1, J_under - 1));
        } else if (orientation == Orientation.THIRD){
            return (!grid.getHoldsBlock(I_left, J_under) && !grid.getHoldsBlock(I_left, J_under - 1) && !grid.getHoldsBlock(I_left - 1, J_under - 2));
        } else {
            return (!grid.getHoldsBlock(I_left - 1, J_under) && !grid.getHoldsBlock(I_left - 1, J_under - 1));
        }
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            if (!grid.getHoldsBlock(I_left + 2, J_under) && !grid.getHoldsBlock(I_left + 2, J_under - 1)) {
                widthIndices = new int[]{0, 1, 2, 2};
                heightIndices = new int[]{0, 0, 0, -1};
                return Orientation.SECOND;
            } else {
                return orientation;
            }
        } else if (orientation == Orientation.SECOND) {
            widthIndices = new int[]{0, 1, 1, 1};
            heightIndices = new int[]{-2, -2, -1, 0};
            return Orientation.THIRD;
        } else if (orientation == Orientation.THIRD) {
            if (!grid.getHoldsBlock(I_left + 2, J_under - 1)) {
                widthIndices = new int[]{0, 0, 1, 2};
                heightIndices = new int[]{0, -1, -1, -1};
                return Orientation.FOURTH;
            } else {
                return orientation;
            }
        } else {
            widthIndices = new int[]{0, 0, 0, 1};
            heightIndices = new int[]{-2, -1, 0, 0};
            return Orientation.FIRST;
        }
    }
}