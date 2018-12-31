import java.awt.Color;
import java.util.ArrayList;

public class Grid {

	Color color = new Color(180, 180, 180);
	Color color_occupied = new Color(200, 50, 50);
	Color color_free = new Color(50, 200, 50);
	private static int dimension, distance;
	static int widthNumber, heightNumber;
	ArrayList<Integer> fullRowIndices = new ArrayList<>();
	private boolean [][] blockMatrix;

	Grid(int dimension, int distance, int widthNumber, int heightNumber){
		Grid.dimension = dimension;
		Grid.distance = distance;
		Grid.widthNumber = widthNumber;
		Grid.heightNumber = heightNumber;
		blockMatrix = new boolean[heightNumber+2][widthNumber+3];

		for(int row = 1; row < Grid.heightNumber +1; row++) {
			setHoldsBlock(Grid.widthNumber + 1, row, true);
			setHoldsBlock(Grid.widthNumber + 2, row, true);
			setHoldsBlock(0, row, true);
		}

		for(int collumn = 1; collumn < Grid.widthNumber + 1; collumn++) {
			setHoldsBlock(collumn, Grid.heightNumber + 1, true);
		}
	}

	void makeFullRows(Field field) {
		fullRowIndices.clear();
		for(int j = 0; j < heightNumber +1; j++){
			if(checkLineFull(j)){
				fullRowIndices.add(j);
			}
		}

		removeLines(fullRowIndices, field);
	}

	private boolean checkLineFull(int j){
		for (boolean b: blockMatrix[j])
			if (!b) return false;
		return true;
	}

	private void removeLines(ArrayList<Integer> fullRows, Field field) {
		field.removeIndex.clear();
		for(int rowNumber : fullRows){
			for(Block groundBlock : field.groundBlocks){
				if(groundBlock.j == rowNumber){
					this.setHoldsBlock(groundBlock.i, groundBlock.j, false);
					field.removeIndex.add(field.groundBlocks.indexOf(groundBlock));
				}
			}
		}
		field.givePoints(fullRows.size());
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

	static int getDimension() {
		return dimension;
	}

	static int getDistance() {
		return distance;
	}

}