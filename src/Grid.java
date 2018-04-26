import java.awt.Color;
import java.util.ArrayList;

public class Grid {

	Color kleur = new Color(180, 180, 180);
	Color bezetkleur = new Color(200, 50, 50);
	Color vrijkleur = new Color(50, 200, 50);
	private static int dimensie, afstand;
	static int BreedteAantal, HoogteAantal;
	static ArrayList<Integer> VolleRijen = new ArrayList<Integer>();
	private boolean [][] bezetmatrix;

	Grid(int dimensie, int afstand, int breedteaantal, int hoogteaantal){
		Grid.HoogteAantal = hoogteaantal;
		Grid.dimensie = dimensie;
		Grid.afstand = afstand;
		Grid.BreedteAantal = breedteaantal;
		bezetmatrix = new boolean[hoogteaantal+2][breedteaantal+3];
	}

	static int getDistance() {
		return afstand;
	}

	static int getDimension() {
		return dimensie;
	}

	void setBezet(int i, int j, boolean bezet){
		bezetmatrix[j][i] = bezet;
	}

	public void veranderBezet(int i, int j){
		bezetmatrix[j][i] ^= true;
	}

	boolean getBezet(int i, int j){
		return bezetmatrix[j][i];
	}

	void makeFullRows() {
		VolleRijen.clear();
		for(int j=0; j < HoogteAantal+1; j++){
			if(checkLineFull(j)){
				VolleRijen.add(j);
			}
		}
		removeLines(VolleRijen);
	}

	private void removeLines(ArrayList<Integer> VolleRijen) {
		Field.VerwijderIndex.clear();
		for(int rijnummer: VolleRijen){
			for(Block ligblok: Field.LigBlokken){
				if(ligblok.j == rijnummer){
					this.setBezet(ligblok.i, ligblok.j, false);
					Field.VerwijderIndex.add(Field.LigBlokken.indexOf(ligblok));
					Field.GivePoint();
				}
			}
		}
	}

	private boolean checkLineFull(int j){
		for (boolean b: bezetmatrix[j])
			if (!b) return false;
		return true;
	}
}