import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BlokFormatie extends ArrayList<Blok> {

	private static final long serialVersionUID = 1L;
	private Color kleur;
	private char type;
	private static List<Blok> BlokLijst;
	private int[] breedteindex, hoogteindex;
	private int Ilinks, Jonder;
	
	BlokFormatie(int Ilinks, char type, Grid grid){
		BlokLijst = new ArrayList<Blok>();
		this.Ilinks = Ilinks;
		this.type = type;
		if (this.type == '-'){
			this.breedteindex = new int[] {0, 1, 2, 3};
			this.hoogteindex = new int[] {0, 0, 0, 0};
			this.Jonder = 1;
			kleur = new Color(0, 235, 235);
		} else if (this.type == '.'){
			this.breedteindex = new int[] {0, 1, 0, 1};
			this.hoogteindex = new int[] {-1, -1, 0, 0};
			this.Jonder = 2;
			kleur = new Color(240, 240, 10);
		} else if (this.type == 'L'){
			this.breedteindex = new int[] {0, 0, 0, 1};
			this.hoogteindex = new int[] {-2, -1, 0, 0};
			this.Jonder = 3;
			kleur = new Color(230, 140, 20);
		} else if (this.type == 't'){
			this.breedteindex = new int[] {0, 1, 1, 2};
			this.hoogteindex = new int[] {0, 0, -1, 0};
			this.Jonder = 2;
			kleur = new Color(200, 0, 200);
		} else if (this.type == 's'){
			this.breedteindex = new int[] {0, 1, 1, 2};
			this.hoogteindex = new int[] {0, 0, -1, -1};
			this.Jonder = 2;
			kleur = new Color(230, 20, 20);
		} else if (this.type == 'z'){
			this.breedteindex = new int[] {0, 1, 1, 2};
			this.hoogteindex = new int [] {-1, -1, 0, 0};
			this.Jonder = 2;
			kleur = new Color(30, 240, 40);
		} else if (this.type == '0'){
			this.breedteindex = new int[] {1, 1, 1, 0};
			this.hoogteindex = new int[] {-2, -1, 0, 0};
			this.Jonder = 3;
			kleur = new Color(30, 30, 255);
		}
		for(int k = 0; k < 4; k++){
			Blok blok = new Blok(this.breedteindex[k], this.hoogteindex[k], this.kleur);
			BlokLijst.add(blok);
			grid.setBezet(this.Ilinks+blok.i, this.Jonder+blok.j, true);
		}
	}

    boolean KijkOnder(Grid grid) {
    	boolean neergekomen = false;
    	if(this.type == '-'){
			if (grid.getBezet(this.Ilinks, this.Jonder+1) || grid.getBezet(this.Ilinks+1, this.Jonder+1) ||
				grid.getBezet(this.Ilinks+2, this.Jonder+1) || grid.getBezet(this.Ilinks+3, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;	
			}
		} else if(this.type == '|'){
			if(grid.getBezet(this.Ilinks+1, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if(this.type == '.' || this.type == 'L' || this.type == '0'){ // twee breed
			if (grid.getBezet(this.Ilinks, this.Jonder+1) || grid.getBezet(this.Ilinks+1, this.Jonder+1)){
				neergekomen = true;
    		} else {
    			Verplaats(grid, 'v');
    			neergekomen = false;
    		}
		} else if(this.type == '�' || this.type == 't' || this.type == '3'){ // drie breed
			if(grid.getBezet(this.Ilinks, this.Jonder+1)|| grid.getBezet(this.Ilinks+1, this.Jonder+1)||
				grid.getBezet(this.Ilinks+2, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if(this.type == '7'){
			if(grid.getBezet(this.Ilinks, this.Jonder-1)|| grid.getBezet(this.Ilinks+1, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if(this.type == '^'){
			if(grid.getBezet(this.Ilinks, this.Jonder+1)|| grid.getBezet(this.Ilinks+1, this.Jonder)||
				grid.getBezet(this.Ilinks+2, this.Jonder)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if (this.type == 'T'){
			if (grid.getBezet(this.Ilinks, this.Jonder) || grid.getBezet(this.Ilinks+1, this.Jonder+1) ||
				grid.getBezet(this.Ilinks+2, this.Jonder)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if(this.type == 's'){
			if (grid.getBezet(this.Ilinks, this.Jonder+1) || grid.getBezet(this.Ilinks+1, this.Jonder+1) ||
				grid.getBezet(this.Ilinks+2, this.Jonder)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if (this.type == 'S'|| this.type == '4'){
			if (grid.getBezet(this.Ilinks, this.Jonder) || grid.getBezet(this.Ilinks+1, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if (this.type == 'z'){
			if (grid.getBezet(this.Ilinks, this.Jonder) || grid.getBezet(this.Ilinks+1, this.Jonder+1) ||
				grid.getBezet(this.Ilinks+2, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if (this.type == 'Z' || this.type == '5'){
			if(grid.getBezet(this.Ilinks, this.Jonder+1) || grid.getBezet(this.Ilinks+1, this.Jonder)){
				neergekomen = true;
			} else {
				Verplaats(grid,'v');
				neergekomen = false;
			}
		} else if(this.type == '1'){
			if(grid.getBezet(this.Ilinks, this.Jonder)|| grid.getBezet(this.Ilinks+1, this.Jonder)||
				grid.getBezet(this.Ilinks+2, this.Jonder+1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		} else if (this.type == '2'){
			if(grid.getBezet(this.Ilinks, this.Jonder+1)|| grid.getBezet(this.Ilinks+1, this.Jonder-1)){
				neergekomen = true;
			} else {
				Verplaats(grid, 'v');
				neergekomen = false;
			}
		}
		return neergekomen;	
	}
    
	void KijkRechts(Grid grid) {
		if (this.type == '-'){
			if (!grid.getBezet(Ilinks+4, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '|'){
			if (!grid.getBezet(Ilinks+2, Jonder-3) && !grid.getBezet(Ilinks+2, Jonder-2) &&
			!grid.getBezet(Ilinks+2, Jonder-1) && !grid.getBezet(Ilinks+2, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '.'){
			if (!grid.getBezet(Ilinks+2, Jonder) && !grid.getBezet(Ilinks+2, Jonder-1)){
				Verplaats(grid, '>');
			}
		} else if (this.type == 'L'){
			if (!grid.getBezet(Ilinks+1, Jonder-2) && !grid.getBezet(Ilinks+1, Jonder-1) && !grid.getBezet(Ilinks+2, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '�'||this.type == '1'){
			if (!grid.getBezet(Ilinks+3, Jonder) && !grid.getBezet(Ilinks+3, Jonder-1)){
				Verplaats(grid, '>');
			} 
		} else if (this.type == '7' || this.type == '0' || this.type == '4'){
			if (!grid.getBezet(Ilinks+2, Jonder-2) && !grid.getBezet(Ilinks+2, Jonder-1) && !grid.getBezet(Ilinks+2, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '^'){
			if (!grid.getBezet(Ilinks+1, Jonder) && !grid.getBezet(Ilinks+3, Jonder-1)){
				Verplaats(grid, '>');
			}
		} else if (this.type == 't' || this.type == 'z'){
			if (!grid.getBezet(Ilinks+2, Jonder-1) && !grid.getBezet(Ilinks+3, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == 'T'){
			if (!grid.getBezet(Ilinks+2, Jonder+1) && !grid.getBezet(Ilinks+3, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == 's'){
			if (!grid.getBezet(Ilinks+2, Jonder) && !grid.getBezet(Ilinks+3, Jonder-1)){
				Verplaats(grid, '>');
			}
		} else if (this.type == 'S'){
			if(!grid.getBezet(Ilinks+1, Jonder-2) && !grid.getBezet(Ilinks+2, Jonder-1) && !grid.getBezet(Ilinks+2, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == 'Z'){
			if (!grid.getBezet(Ilinks+2, Jonder-2) && !grid.getBezet(Ilinks+2, Jonder-1) && !grid.getBezet(Ilinks+1, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '2'){
			if (!grid.getBezet(Ilinks+2, Jonder-2) && !grid.getBezet(Ilinks+1, Jonder-1) && !grid.getBezet(Ilinks+1, Jonder)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '3'){
			if (!grid.getBezet(Ilinks+3, Jonder) && !grid.getBezet(Ilinks+1, Jonder-1)){
				Verplaats(grid, '>');
			}
		} else if (this.type == '5'){
			if (!grid.getBezet(Ilinks+1, Jonder-2) && !grid.getBezet(Ilinks+2, Jonder-1) && !grid.getBezet(Ilinks+1, Jonder)){
				Verplaats(grid, '>');
			}
		}
	}

	void KijkLinks(Grid grid) {
			if (this.type == '-'){
				if(!grid.getBezet(Ilinks-1, Jonder)){
					Verplaats(grid, '<');
				}
			} else if (this.type == '|'){
				if (!grid.getBezet(Ilinks, Jonder-3) && !grid.getBezet(Ilinks, Jonder-2)&&
					!grid.getBezet(Ilinks, Jonder-1) && !grid.getBezet(Ilinks, Jonder)){
					Verplaats(grid, '<');
				}
			} else if (this.type == '.'||this.type == '^'||this.type == '3'){
				if (!grid.getBezet(Ilinks-1, Jonder) && !grid.getBezet(Ilinks-1, Jonder-1)){
					Verplaats(grid, '<');
				}
			} else if (this.type == 'L' || this.type == '2' || this.type == '5'){
				if (!grid.getBezet(Ilinks-1, Jonder) && !grid.getBezet(Ilinks-1, Jonder-1) &&
					!grid.getBezet(Ilinks-1, Jonder-2)){
					Verplaats(grid, '<');
				}
			} else if (this.type == '�'){
				if (!grid.getBezet(Ilinks-1, Jonder) && !grid.getBezet(Ilinks+1, Jonder-1)){
					Verplaats(grid, '<');
				}				
			} else if (this.type == '7'){
				if (!grid.getBezet(Ilinks, Jonder) && !grid.getBezet(Ilinks, Jonder-1) &&
					!grid.getBezet(Ilinks-1, Jonder-2)){
						Verplaats(grid, '<');
				}
			} else if (this.type == 't' || this.type == 's'){
				if (!grid.getBezet(Ilinks-1, Jonder) && !grid.getBezet(Ilinks, Jonder-1)){
					Verplaats(grid, '<');
				}
			} else if (type == 'T'){
				if (!grid.getBezet(Ilinks-1, Jonder) && !grid.getBezet(Ilinks, Jonder+1)){
					Verplaats(grid, '<');
				}
			} else if (this.type == 'S'){
				if (!grid.getBezet(Ilinks-1, Jonder-2) && !grid.getBezet(Ilinks-1, Jonder-1) && !grid.getBezet(Ilinks, Jonder)){
					Verplaats(grid, '<');
				}
			} else if (this.type == 'z'){
				if (!grid.getBezet(Ilinks-1, Jonder-1) && !grid.getBezet(Ilinks, Jonder)){
					Verplaats(grid, '<');
				}
			} else if (this.type == 'Z'){
				if (!grid.getBezet(Ilinks, Jonder-2) && !grid.getBezet(Ilinks-1, Jonder-1) && !grid.getBezet(Ilinks-1, Jonder)){
					Verplaats(grid, '<');
				}
			} else if (this.type == '0'){
				if (!grid.getBezet(Ilinks-1, Jonder) && !grid.getBezet(Ilinks, Jonder-1) && !grid.getBezet(Ilinks, Jonder-2)){
					Verplaats(grid, '<');
				}
			} else if (this.type == '1'){
				if (!grid.getBezet(Ilinks-1, Jonder-1) && !grid.getBezet(Ilinks+1, Jonder)){
					Verplaats(grid, '<');
				}
			} else if (this.type == '4'){
				if (!grid.getBezet(Ilinks, Jonder-2) && !grid.getBezet(Ilinks-1, Jonder-1) && !grid.getBezet(Ilinks, Jonder)){
					Verplaats(grid, '<');
				}
			}
	}
	
	private void Verplaats(Grid grid, char richting){
		for (Blok blok : BlokLijst){
			grid.setBezet(this.Ilinks+breedteindex[BlokLijst.indexOf(blok)], this.Jonder+hoogteindex[BlokLijst.indexOf(blok)], false);
		}
		if(richting == '>'){
			this.Ilinks++;
		} else if (richting == '<') {
			this.Ilinks--;
		} else if (richting == 'v'){
			this.Jonder++;
		}
		for (Blok blok : BlokLijst){
			grid.setBezet(this.Ilinks+breedteindex[BlokLijst.indexOf(blok)], this.Jonder+hoogteindex[BlokLijst.indexOf(blok)], true);
		}
	}
	
	void zetBlokkenOver(Veld veld){
		for(Blok blok : BlokLijst){
			Veld.LigBlokken.add(blok);
			if(blok.j == 1){
				veld.StopSpel();
				break;
			}
		}		
	}
	
	void roteer(Grid grid){
		for (Blok blok : BlokLijst){
			grid.setBezet(this.Ilinks+breedteindex[BlokLijst.indexOf(blok)], this.Jonder+hoogteindex[BlokLijst.indexOf(blok)], false);
		}
		if(type == '-'){
			if(this.Jonder > 3 && this.Jonder < Grid.HoogteAantal){
				this.breedteindex = new int[] {1, 1, 1, 1};
				this.hoogteindex = new int[] {0, -1, -2, -3};
				this.type ='|';
			}
		} else if(type == '|'){
			if(!grid.getBezet(Ilinks, Jonder) && !grid.getBezet(Ilinks+2, Jonder)
				&& !grid.getBezet(Ilinks+3, Jonder)) {
				this.breedteindex = new int[] {0, 1, 2, 3};
				this.hoogteindex = new int[] {0, 0, 0, 0};
				this.type = '-';
			}
		} else if(type == 'L'){
			if(!grid.getBezet(Ilinks+2, Jonder) && !grid.getBezet(Ilinks+2, Jonder-1)){
				this.breedteindex = new int[] {0, 1, 2, 2};
				this.hoogteindex = new int[] {0, 0, 0, -1};
				this.type = '�';
			}
		} else if(type == '�'){
			this.breedteindex = new int[] {0, 1, 1, 1};
			this.hoogteindex = new int[] {-2, -2, -1, 0};
			this.type = '7';
		} else if(type == '7'){
			if(!grid.getBezet(this.Ilinks+2, this.Jonder-1)){
				this.breedteindex = new int[] {0, 0, 1, 2};
				this.hoogteindex = new int[] {0, -1, -1, -1};
				this.type = '^';
			}
		} else if(type == '^'){
			this.breedteindex = new int[] {0, 0, 0, 1};
			this.hoogteindex = new int[] {-2, -1, 0, 0};
			this.type = 'L';
		} else if (type == 't'){
			this.breedteindex = new int[] {1, 1, 0, 1};
			this.hoogteindex = new int[] {-2, -1, -1, 0};
			this.type = '4';
		}
		else if (type == '4'){
			if(!grid.getBezet(this.Ilinks+2,this.Jonder-1)){
				this.breedteindex = new int[] {0, 1, 1, 2};
				this.hoogteindex = new int[] {-1, -1, 0, -1};
				this.type = 'T';
			}
		} else if (type == 'T'){
			this.breedteindex = new int[] {0, 0, 1, 0};
			this.hoogteindex = new int[] {-2, -1, -1, 0};
			this.type = '5';
		} else if (type == '5'){
			if(!grid.getBezet(this.Ilinks+2,this.Jonder-1)){
				this.breedteindex = new int[] {0, 1, 1, 2};
				this.hoogteindex = new int[] {0, 0, -1, 0};
				this.type = 't';
			}
		} else if (type == 's'){
			this.breedteindex = new int[] {0, 0, 1, 1};
			this.hoogteindex = new int[] {-2, -1, -1, 0};
			this.type = 'S';
		} else if (type == 'S'){
			if(!grid.getBezet(this.Ilinks+2, this.Jonder-1)){
				this.breedteindex = new int[] {0, 1, 1, 2};
				this.hoogteindex = new int[] {0, 0, -1, -1};
				this.type = 's';
			}
		} else if (type == 'z'){
			this.breedteindex = new int[] {0, 0, 1, 1};
			this.hoogteindex = new int[] {0, -1, -1, -2};
			this.type = 'Z';
		} else if (type == 'Z'){
			if(!grid.getBezet(this.Ilinks+2, this.Jonder)){
				this.breedteindex = new int[] {0, 1, 1, 2};
				this.hoogteindex = new int [] {-1, -1, 0, 0};
				this.type = 'z';
			}
		} else if(type == '0'){ // gespiegelde L
			if(!grid.getBezet(Ilinks+2, Jonder) && !grid.getBezet(Ilinks+2, Jonder-1)){
				this.breedteindex = new int[] {0, 1, 2, 2};
				this.hoogteindex = new int[] {-1, -1, -1, 0};
				this.type = '1';
			}
		} else if (type == '1'){
			this.breedteindex = new int[] {1, 0, 0, 0};
			this.hoogteindex = new int[] {-2, -2, -1, 0};
			this.type = '2';
		} else if (type == '2'){
			if(!grid.getBezet(this.Ilinks+1, this.Jonder) && !grid.getBezet(this.Ilinks+2, this.Jonder)){
				this.breedteindex = new int[] {0, 0, 1, 2};
				this.hoogteindex = new int[] {-1, 0, 0, 0};
				this.type = '3';
			}
		} else if (type == '3'){
			this.breedteindex = new int[] {1, 1, 1, 0};
			this.hoogteindex = new int[] {-2, -1, 0, 0};
			this.type = '0';
		}

		for (Blok blok : BlokLijst){
			grid.setBezet(this.Ilinks+breedteindex[BlokLijst.indexOf(blok)], this.Jonder+hoogteindex[BlokLijst.indexOf(blok)], true);
		}
	}

	void teken(Graphics graphics){
		for(Blok blok : BlokLijst){
			blok.i = this.Ilinks+breedteindex[BlokLijst.indexOf(blok)];
			blok.j = this.Jonder+hoogteindex[BlokLijst.indexOf(blok)];
			blok.setPositie(blok.i, blok.j);
			blok.teken(graphics);
		}
	}
}