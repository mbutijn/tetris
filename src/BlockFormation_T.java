import java.awt.*;

class BlockFormation_T extends BlockFormation{
    BlockFormation_T(Grid grid) {
        super(grid);
        setWidthIndices(new int[]{0, 1, 1, 2});
        setHeightIndices(new int[]{0, 0, -1, 0});
        J_under = 2;
        color = new Color(230, 80, 230);
        type = Sort.T;
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
