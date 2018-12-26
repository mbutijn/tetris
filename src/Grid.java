import java.awt.Color;
import java.util.ArrayList;

public class Grid {

	Color color = new Color(180, 180, 180);
	Color color_occupied = new Color(200, 50, 50);
	Color color_free = new Color(50, 200, 50);
	private static int dimension, distance;
	static int widthNumber, heightNumber;
	ArrayList<Integer> fullRows = new ArrayList<>();
	private boolean [][] blockMatrix;

	Grid(int dimension, int distance, int widthNumber, int heightNumber){
		Grid.dimension = dimension;
		Grid.distance = distance;
		Grid.widthNumber = widthNumber;
		Grid.heightNumber = heightNumber;
		blockMatrix = new boolean[heightNumber+2][widthNumber+3];
	}

	void makeFullRows(Field field) {
		fullRows.clear();
		for(int j = 0; j < heightNumber +1; j++){
			if(checkLineFull(j)){
				fullRows.add(j);
			}
		}
		removeLines(fullRows, field);
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
					field.givePoint();
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

	static int getDimension() {
		return dimension;
	}

	static int getDistance() {
		return distance;
	}

}