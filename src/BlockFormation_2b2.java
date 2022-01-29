class BlockFormation_2b2 extends BlockFormation {

    BlockFormation_2b2(Grid grid) {
        super(grid,2);
        setWidthIndices(new int[]{0, 1, 0, 1});
        setHeightIndices(new int[]{-1, -1, 0, 0});
        J_under = 2;

        type = Sort.TWO_BY_TWO;
    }
}
