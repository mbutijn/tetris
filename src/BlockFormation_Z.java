class BlockFormation_Z extends BlockFormation{
    BlockFormation_Z(Grid grid) {
        super(grid,7);
        setWidthIndices(new int[]{0, 1, 1, 2});
        setHeightIndices(new int[]{-1, -1, 0, 0});
        J_under = 2;
        type = Sort.Z;
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            widthIndices = new int[]{0, 0, 1, 1};
            heightIndices = new int[]{0, -1, -1, -2};
            return Orientation.SECOND;
        } else {
            if (grid.getHoldsBlock(I_left + 2, J_under) == 0) {
                widthIndices = new int[]{0, 1, 1, 2};
                heightIndices = new int[]{-1, -1, 0, 0};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }
}
