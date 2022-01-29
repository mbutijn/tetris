class BlockFormation_S extends BlockFormation{
    BlockFormation_S(Grid grid) {
        super(grid,5);
        setWidthIndices(new int[]{0, 1, 1, 2});
        setHeightIndices(new int[]{0, 0, -1, -1});
        J_under = 2;
        type = Sort.S;
    }

    Orientation rotate(Grid grid, Orientation orientation) {
        if (orientation == Orientation.FIRST) {
            widthIndices = new int[]{0, 0, 1, 1};
            heightIndices = new int[]{-2, -1, -1, 0};
            return Orientation.SECOND;
        } else {
            if (grid.getHoldsBlock(I_left + 2, J_under - 1) == 0) {
                widthIndices = new int[]{0, 1, 1, 2};
                heightIndices = new int[]{0, 0, -1, -1};
                return Orientation.FIRST;
            } else {
                return orientation;
            }
        }
    }
}
