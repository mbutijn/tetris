package tetris;

import java.awt.*;
import java.awt.geom.Point2D;

public class Blok{
	int i,j;
	
	private Positie positie = new Positie();
	private Color kleur;
	
	Blok(int i, int j, Color kleur){
		this.i = i;
		this.j = j;
		this.kleur = kleur;
	}

	void setPositie(int i, int j) {
		this.positie.setX(this.i*Grid.getDimensie()+Grid.getAfstand());
		this.positie.setY(this.j*Grid.getDimensie()+Grid.getAfstand());
	}
	
	void val(Grid grid){
		grid.setBezet(this.i, this.j, false);
		this.j++;
		grid.setBezet(this.i, this.j, true);
	}

	Integer getj() {
		return this.j;
	}
	
	public Integer geti() {
		return this.i;
	}
	
	void teken(Graphics graphics){
		float [] shine = {0.4f, 1.0f};
		Color[] colors = {kleur, Color.BLACK};
		Graphics2D g2d = (Graphics2D) graphics;
		setPositie(this.i, this.j);

		Point2D center = new Point2D.Float(this.positie.getX(), this.positie.getY());
		RadialGradientPaint rgp = new RadialGradientPaint(center, (float) (1.2*Grid.getDimensie()), shine, colors);
		g2d.setPaint(rgp);
		g2d.fillRect(this.positie.getX(), this.positie.getY(), Grid.getDimensie()-4*Grid.getAfstand(), Grid.getDimensie()-4*Grid.getAfstand());
	}
}
