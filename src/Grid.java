import java.awt.Color;

public class Grid {

	Color color = new Color(180, 180, 180);
	Color color_occupied = new Color(200, 50, 50);
	Color color_free = new Color(50, 200, 50);
	private static int edgeLength, spacing;
	static int widthNumber, heightNumber;
	private final int [][] blockMatrix;

	Grid(int edgeLength, int spacing, int widthNumber, int heightNumber){
		Grid.edgeLength = edgeLength;
		Grid.spacing = spacing;
		Grid.widthNumber = widthNumber;
		Grid.heightNumber = heightNumber;
		blockMatrix = new int[heightNumber + 2][widthNumber + 3];

		for(int row = 1; row < Grid.heightNumber + 1; row++) {
			setHoldsBlock(Grid.widthNumber + 1, row, 1);
			setHoldsBlock(Grid.widthNumber + 2, row, 1);
			setHoldsBlock(0, row, 1);
		}

		for(int collumn = 1; collumn < Grid.widthNumber + 1; collumn++) {
			setHoldsBlock(collumn, Grid.heightNumber + 1, 1);
		}
	}

	int clearFullRows() {
		int rowsCleared = 0;
		for (int rowIndex = 0; rowIndex < heightNumber + 1; rowIndex++) {
			if (checkLineFull(rowIndex)) {
				rowsCleared++;

				for (int rowAbove = rowIndex-1; rowAbove > 0; rowAbove--) {
					moveRowDown(rowAbove);
				}
				setRow(1, new int[widthNumber + 3]);
			}
		}
		return rowsCleared;
	}

	private void moveRowDown(int rowAbove) {
		int [] blocks = getRow(rowAbove);
		setRow(rowAbove+1, blocks);
	}

	private boolean checkLineFull(int row){
		for (int column: blockMatrix[row])
			if (column == 0) return false;
		return true;
	}

	public boolean checkLost(){
		for (int column = 2; column < widthNumber -1; column++)
			if(blockMatrix[1][column] != 0) return true;
		return false;
	}

	void setHoldsBlock(int i, int j, int index){
		blockMatrix[j][i] = index;
	}

	int getHoldsBlock(int i, int j){
		return blockMatrix[j][i];
	}

	int [] getRow(int j){
		return blockMatrix[j];
	}

	void setRow(int j, int [] row){
		blockMatrix[j] = row;
	}

	static int getEdgeLength() {
		return edgeLength;
	}

	static int getSpacing() {
		return spacing;
	}

}