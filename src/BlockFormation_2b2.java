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
}
