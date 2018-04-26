import java.awt.*;
import java.awt.geom.Point2D;

public class Block {
	int i,j;
	private Position position = new Position();
	private Color color;

	Block(int i, int j, Color color){
		this.i = i;
		this.j = j;
		this.color = color;
	}

	void setPosition(int i, int j) {
		this.position.setX(i*Grid.getDimension()+Grid.getDistance());
		this.position.setY(j*Grid.getDimension()+Grid.getDistance());
	}

	void drop(Grid grid){
		grid.setHoldsBlock(this.i, this.j, false);
		this.j++;
		grid.setHoldsBlock(this.i, this.j, true);
	}

	Integer getj() {
		return this.j;
	}

	public Integer geti() {
		return this.i;
	}

	void draw(Graphics graphics){
		float [] shine = {0.4f, 1.0f};
		Color[] colors = {color, Color.BLACK};
		Graphics2D g2d = (Graphics2D) graphics;
		setPosition(this.i, this.j);

		Point2D center = new Point2D.Float(this.position.getX(), this.position.getY());
		RadialGradientPaint rgp = new RadialGradientPaint(center, (float) (1.2*Grid.getDimension()), shine, colors);
		g2d.setPaint(rgp);
		g2d.fillRect(this.position.getX(), this.position.getY(), Grid.getDimension()-4*Grid.getDistance(), Grid.getDimension()-4*Grid.getDistance());
	}
}