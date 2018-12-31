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

	void drop(Grid grid){
		grid.setHoldsBlock(i, j, false);
		j++;
		grid.setHoldsBlock(i, j, true);
	}

	void render(Graphics graphics){
		float [] contour = {0.4f, 1.0f};
		Color[] colors = {color, Color.BLACK};
		Graphics2D g2d = (Graphics2D) graphics;

		Point2D center = new Point2D.Float(position.getX(), position.getY());
		g2d.setPaint(new RadialGradientPaint(center, (float) (1.2*Grid.getDimension()), contour, colors));
		g2d.fillRect(position.getX(), position.getY(), Grid.getDimension()-4*Grid.getDistance(), Grid.getDimension()-4*Grid.getDistance());
	}

	void setPosition() {
		position.setX(i*Grid.getDimension()+Grid.getDistance());
		position.setY(j*Grid.getDimension()+Grid.getDistance());
	}

	Integer getJ() {
		return this.j;
	}

	public Integer getI() {
		return this.i;
	}

	void setI(int i){
		this.i = i;
	}

	void setJ(int j){
		this.j = j;
	}
}