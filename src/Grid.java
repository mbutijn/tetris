import java.awt.Color;
import java.util.ArrayList;

public class Grid {

	Color color = new Color(180, 180, 180);
	Color color_occupied = new Color(200, 50, 50);
	Color color_free = new Color(50, 200, 50);
	private static int dimension, distance;
	static int widthNumber, heightNumber;
	static ArrayList<Integer> fullRows = new ArrayList<Integer>();
	private boolean [][] blockMatrix;

	Grid(int dimension, int distance, int widthNumber, int heightNumber){
		Grid.heightNumber = heightNumber;
		Grid.dimension = dimension;
		Grid.distance = distance;
		Grid.widthNumber = widthNumber;
		blockMatrix = new boolean[heightNumber+2][widthNumber+3];
	}

	static int getDistance() {
		return distance;
	}

	static int getDimension() {
		return dimension;
	}

	void setHoldsBlock(int i, int j, boolean occupied){
		blockMatrix[j][i] = occupied;
	}

	public void changeHoldsBlock(int i, int j){
		blockMatrix[j][i] ^= true;
	}

	boolean getHoldsBlock(int i, int j){
		return blockMatrix[j][i];
	}

	void makeFullRows() {
		fullRows.clear();
		for(int j = 0; j < heightNumber +1; j++){
			if(checkLineFull(j)){
				fullRows.add(j);
			}
		}
		removeLines(fullRows);
	}

	private void removeLines(ArrayList<Integer> fullRows) {
		Field.removeIndex.clear();
		for(int rowNumber : fullRows){
			for(Block groundBlock : Field.groundBlocks){
				if(groundBlock.j == rowNumber){
					this.setHoldsBlock(groundBlock.i, groundBlock.j, false);
					Field.removeIndex.add(Field.groundBlocks.indexOf(groundBlock));
					Field.givePoint();
				}
			}
		}
	}

	private boolean checkLineFull(int j){
		for (boolean b: blockMatrix[j])
			if (!b) return false;
		return true;
	}
}