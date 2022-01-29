class BlockFormation_J extends BlockFormation{
    BlockFormation_J(Grid grid) {
        super(grid,3);
        setWidthIndices(new int[]{1, 1, 1, 0});
        setHeightIndices(new int[]{-2, -1, 0, 0});
        J_under = 3;
        type = Sort.J;
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            if (grid.getHoldsBlock(I_left + 2, J_under) == 0 && grid.getHoldsBlock(I_left + 2, J_under - 1) == 0) {
                widthIndices = new int[]{0, 1, 2, 2};
                heightIndices = new int[]{-1, -1, -1, 0};
                return Orientation.SECOND;
            } else {
                return orientation;
            }
        } else if (orientation == Orientation.SECOND) {
            widthIndices = new int[]{1, 0, 0, 0};
            heightIndices = new int[]{-2, -2, -1, 0};
            return Orientation.THIRD;
        } else if (orientation == Orientation.THIRD) {
            if (grid.getHoldsBlock(I_left + 1, J_under) == 0 && grid.getHoldsBlock(I_left + 2, J_under) == 0) {
                widthIndices = new int[]{0, 0, 1, 2};
                heightIndices = new int[]{-1, 0, 0, 0};
                return Orientation.FOURTH;
            } else {
                return orientation;
            }
        } else {
            widthIndices = new int[]{1, 1, 1, 0};
            heightIndices = new int[]{-2, -1, 0, 0};
            return Orientation.FIRST;
        }
    }
}
