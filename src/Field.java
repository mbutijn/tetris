import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Field extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;

	private Grid grid = new Grid(30, 2, 13, 21);
	private GreyBoxes greyBoxes = new GreyBoxes();
	private BlockFormation currentBlockFormation = new BlockFormation((int) Math.round(1 + Math.random()*(Grid.widthNumber - 5)), selectType(), grid);
	ArrayList<Block> groundBlocks = new ArrayList<>();
	ArrayList<Integer> removeIndex = new ArrayList<>();
	private Timer timer;
	int points;
	private JTextField scoreField = new JTextField("0", 4);
	JTextArea highscore = new JTextArea("0", 0, 0);
	private final int TIMER_PERIOD = 25; // ms
	private int dropPeriod = 10;

	Field(){
		setContentPane(greyBoxes);
		setKeyBoardListeners();
		setResizable(false);
		setLayout(new BorderLayout());

		JPanel panel_points = new JPanel();
		JPanel panel_highscore = new JPanel();
		scoreField.setEditable(false);
		scoreField.setVisible(true);
		highscore.setEditable(false);
		highscore.setVisible(true);

		panel_points.add(new JLabel("Points: "));
		panel_points.add(scoreField);

		try {
			ObjectInputStream inputstream = new ObjectInputStream(new FileInputStream("Game.ser"));
			String highscores = (String) inputstream.readObject();
			highscore.setText(highscores);
		} catch(Exception ex) {
			System.out.println("loading highscores failed");
		}

		panel_highscore.setLayout(new BorderLayout(1,1));
		panel_highscore.add(new JLabel("Date:                   Scores:"),BorderLayout.NORTH);
		panel_highscore.add(highscore,BorderLayout.CENTER);
		panel_highscore.add(new Panel(),BorderLayout.EAST);

		add(panel_points, BorderLayout.SOUTH);
		add(panel_highscore, BorderLayout.EAST);
	}

	void startGame() {
		timer = new Timer(TIMER_PERIOD, run);
		timer.setRepeats(true);
		timer.start();
	}

	private ActionListener run = new ActionListener() {
		private int count = 0;
		public void actionPerformed(ActionEvent evt) { // runs every 25 miliseconds
			count++;
			greyBoxes.paintImmediately(new Rectangle(0, 0, Game.xBound, Game.yBound));

			if (count >= dropPeriod){ // normally runs every 250 miliseconds
				if (currentBlockFormation.IsBelowOccupied()){ // blockformation can not move down anymore
					// move the blocks from the current blockformation to layDownBlocks
					currentBlockFormation.layDownBlocks(Field.this);
					grid.makeFullRows(Field.this);
					if(!removeIndex.isEmpty()){
						removeEveryBlock();
					}

					// overwrite "blockformation" with a new instance
					currentBlockFormation = new BlockFormation((int) Math.round(1+Math.random()*(Grid.widthNumber - 5)), selectType(), grid);
				} else {
					currentBlockFormation.moveOneTile(Direction.DOWN); // move the blockformation down one tile
				}
				count = 0;
			}
		}
	};

	private void removeEveryBlock(){
		int numberGroundBlocks = groundBlocks.size();
		int gapSize = 0;
		Collections.sort(removeIndex);

		for (int groundBlockIndex = 0; groundBlockIndex < numberGroundBlocks; groundBlockIndex++){
			if(!removeIndex.isEmpty()){
				if(groundBlockIndex == removeIndex.get(0)){
					groundBlocks.remove(gapSize);
					removeIndex.remove(0);
				} else{
					gapSize++;
				}
			}
		}
		Collections.sort(groundBlocks, new CustomComparator());

		// drop the groundblocks
		for (int fullRowIndex = 0; fullRowIndex < grid.fullRowIndices.size(); fullRowIndex++){
			for (Block groundBlock: groundBlocks){
				if (groundBlock.getJ() < grid.fullRowIndices.get(fullRowIndex)){
					groundBlock.drop(grid);
				}
			}
		}
	}

	private Sort selectType() {
		double select = Math.random();
		if(select < 1.0/7.0){
			return Sort.ONE_BY_FOUR;
		} else if (select < 2.0/7.0){
			return Sort.TWO_BY_TWO;
		} else if (select < 3.0/7.0){
			return Sort.L;
		} else if (select < 4.0/7.0){
			return Sort.T;
		} else if (select < 5.0/7.0){
			return Sort.S;
		} else if (select < 6.0/7.0){
			return Sort.Z;
		} else {
			return Sort.J;
		}
//		return Sort.Z;
	}

	private void setKeyBoardListeners() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
	}

	void updateScore(int rowsCleared) {
		if (rowsCleared == 1) {
			points += 50;
		} else if (rowsCleared == 2){
			points += 150;
		} else if (rowsCleared == 3){
			points += 400;
		} else if (rowsCleared == 4){
			points += 900;
		}

		scoreField.setText("" + points);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();
		if (code == KeyEvent.VK_DOWN) {
			if (!currentBlockFormation.IsBelowOccupied()){
				currentBlockFormation.moveOneTile(Direction.DOWN);
			}
		} else if (code == KeyEvent.VK_RIGHT) {
			if (currentBlockFormation.IsRightFree()){
				currentBlockFormation.moveOneTile(Direction.RIGHT);
			}
		} else if (code == KeyEvent.VK_LEFT){
			if (currentBlockFormation.IsLeftFree()){
				currentBlockFormation.moveOneTile(Direction.LEFT);
			}
		} else if (code == KeyEvent.VK_ESCAPE){
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		} else if (code == KeyEvent.VK_SPACE){
			currentBlockFormation.rotate_bl();
		} else if (code == KeyEvent.VK_F){
			if (dropPeriod > 1){
				dropPeriod--;
				System.out.println("BlockFormation drops every " + dropPeriod * TIMER_PERIOD + " ms");
			} else {
				System.out.println("Maximum speed (" + TIMER_PERIOD + " ms)");
			}
		} else if (code == KeyEvent.VK_S){
			dropPeriod++;
			System.out.println("BlockFormation drops every " + dropPeriod * TIMER_PERIOD + " ms");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	void stopGame() {
		timer.stop();
		JOptionPane.showMessageDialog(null, "Game over! \n" + "You scored " + points + " points!");
		Game.saveScore(this);
	}

	class GreyBoxes extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics graphics){
			super.paintComponent(graphics);
			Graphics2D g2d = (Graphics2D) graphics;

			for(int i = 1; i< Grid.widthNumber +1; i++){
				for(int j = 1; j < Grid.heightNumber +1; j++){
					if (false) { // Makes background tiles red and green
						if (grid.getHoldsBlock(i, j)) { // red for occupied
							g2d.setColor(grid.color_occupied);
						} else { // green for free
							g2d.setColor(grid.color_free);
						}
					} else { // Makes all background tiles grey
						g2d.setColor(grid.color);
					}
					g2d.fillRect(i*Grid.getDimension(), j*Grid.getDimension(), Grid.getDimension()-2*Grid.getDistance(), Grid.getDimension()-2*Grid.getDistance());
				}
			}

			currentBlockFormation.setCoordinates();
			currentBlockFormation.render(g2d);

			for (Block groundBlock : groundBlocks){
				groundBlock.setPosition();
				groundBlock.render(g2d);
			}
		}
	}

	public class CustomComparator implements Comparator<Block> {
		@Override
		public int compare(Block block1, Block block2) {
			return block2.getJ().compareTo(block1.getJ());
		}
	}

}