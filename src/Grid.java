import java.awt.Color;
import java.util.ArrayList;

public class Grid {

	Color color = new Color(180, 180, 180);
	Color color_occupied = new Color(200, 50, 50);
	Color color_free = new Color(50, 200, 50);
	private static int edgeLength, spacing;
	static int widthNumber, heightNumber;
	ArrayList<Integer> fullRowIndices = new ArrayList<>();
	private boolean [][] blockMatrix;

	Grid(int edgeLength, int spacing, int widthNumber, int heightNumber){
		Grid.edgeLength = edgeLength;
		Grid.spacing = spacing;
		Grid.widthNumber = widthNumber;
		Grid.heightNumber = heightNumber;
		blockMatrix = new boolean[heightNumber + 2][widthNumber + 3];

		for(int row = 1; row < Grid.heightNumber + 1; row++) {
			setHoldsBlock(Grid.widthNumber + 1, row, true);
			setHoldsBlock(Grid.widthNumber + 2, row, true);
			setHoldsBlock(0, row, true);
		}

		for(int collumn = 1; collumn < Grid.widthNumber + 1; collumn++) {
			setHoldsBlock(collumn, Grid.heightNumber + 1, true);
		}
	}

	void updateFullRowIndices() {
		fullRowIndices.clear();
		for(int rowIndex = 0; rowIndex < heightNumber + 1; rowIndex++){
			if(checkLineFull(rowIndex)){
				fullRowIndices.add(rowIndex);
			}
		}
	}

	private boolean checkLineFull(int row){
		for (boolean b: blockMatrix[row])
			if (!b) return false;
		return true;
	}

	void removeLines(Field field) {
		field.removeIndices.clear();
		for(int rowNumber : fullRowIndices){
			for(Block groundBlock : field.groundBlocks){
				if(groundBlock.getj() == rowNumber){
					setHoldsBlock(groundBlock.geti(), groundBlock.getj(), false);
					field.removeIndices.add(field.groundBlocks.indexOf(groundBlock));
				}
			}
		}
	}

	void setHoldsBlock(int i, int j, boolean occupied){
		blockMatrix[j][i] = occupied;
	}

	boolean getHoldsBlock(int i, int j){
		return blockMatrix[j][i];
	}

	public void changeHoldsBlock(int i, int j){
		blockMatrix[j][i] ^= true;
	}

	static int getEdgeLength() {
		return edgeLength;
	}

	static int getSpacing() {
		return spacing;
	}

}