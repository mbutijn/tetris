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
//	private Grid grid = new Grid(30, 2, 5, 12);
	private static GreyBoxes greyBoxes;
	private static ArrayList<BlockFormation> blockFormations = new ArrayList<>();
	static ArrayList<Block> groundBlocks = new ArrayList<>();
	static ArrayList<Integer> removeIndex = new ArrayList<>();
	private Timer timer;
	static int points;
	private static JTextField scoreField = new JTextField("0", 4);
	static JTextArea highscore = new JTextArea("0", 0, 0);
	private int count = 0;

	Field(){
		greyBoxes = new GreyBoxes();
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

		ObjectInputStream inputstream;

		try {
			inputstream = new ObjectInputStream(new FileInputStream("Game.ser"));
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
		timer = new Timer(50, run);
		timer.setRepeats(true);
		timer.start();
	}

	private void setBFL(ArrayList<BlockFormation> BlokFormations){
		Field.blockFormations = BlokFormations;
	}

	private ArrayList<BlockFormation> getBlockFormationList(){
		return blockFormations;
	}

	private ActionListener run = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			count++;
			greyBoxes.paintImmediately(new Rectangle(0, 0, Spel.xBound, Spel.yBound));
			if(blockFormations.isEmpty()){
				blockFormations.add(new BlockFormation((int) Math.round(1+Math.random()*(Grid.widthNumber -5)), selectType(), grid));
				setBFL(blockFormations);
			}
			if (count == 10){

				if (getBlockFormationList().get(0).checkBelow(grid)){
					makeNewOne(getBlockFormationList().get(0));
				}
				count = 0;
			}
		}
	};

	private void makeNewOne(BlockFormation blockFormation) {
		blockFormation.layDownBlocks(this);
		grid.makeFullRows();
		if(!removeIndex.isEmpty()){
			removeEveryBlock();
		}
		getBlockFormationList().remove(0);
		getBlockFormationList().add(new BlockFormation((int) Math.round(1+Math.random()*(Grid.widthNumber -5)), selectType(), grid));
	}

	private void removeEveryBlock(){
		int number = groundBlocks.size();
		int gapSize = 0;
		Collections.sort(removeIndex);

		for (int k = 0; k < number; k++){
			if(!removeIndex.isEmpty()){
				if(k == removeIndex.get(0)){
					groundBlocks.remove(gapSize);
					removeIndex.remove(0);
				} else{
					gapSize++;
				}
			}
		}
		Collections.sort(groundBlocks, new CustomComparator());
		dropGroundBlocks();
	}

	private void dropGroundBlocks() {
		for (int i = 0; i<Grid.fullRows.size(); i++){
			for (Block groundBlock: groundBlocks){
				if (groundBlock.j<Grid.fullRows.get(i)){
					groundBlock.drop(grid);
				}
			}
		}
	}

	private char selectType() {
		char type;
		double select =  Math.random();
		if(select < 1.0/7.0){
			type = '-';
		} else if (select < 2.0/7.0){
			type = '.';
		} else if (select < 3.0/7.0){
			type = 'L';
		} else if (select < 4.0/7.0){
			type = 't';
		} else if (select < 5.0/7.0){
			type = 's';
		} else if (select < 6.0/7.0){
			type = 'z';
		} else {
			type = '0';
		}
		//type = '-';
		return type;
	}

	private void setKeyBoardListeners() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
	}

	static void givePoint() {
		points++;
		scoreField.setText("" + points);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();

		if (code == KeyEvent.VK_DOWN) {
			getBlockFormationList().get(0).checkBelow(grid);
		} else if (code == KeyEvent.VK_RIGHT) {
			getBlockFormationList().get(0).checkRight(grid);
		} else if (code == KeyEvent.VK_LEFT){
			getBlockFormationList().get(0).checkLeft(grid);
		} else if (code == KeyEvent.VK_ESCAPE){
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		} else if (code == KeyEvent.VK_SPACE){
			getBlockFormationList().get(0).rotate(grid);
		} else if (code == KeyEvent.VK_F){
			try {
				timer.setDelay(timer.getDelay() - 10);
			} catch(Exception exception) {
				System.out.println("Maximum speed");
			}
		} else if (code == KeyEvent.VK_S){
			timer.setDelay(timer.getDelay() + 10);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	void stopGame() {
		timer.stop();
		JOptionPane.showMessageDialog(null, "Game over! \n" + "You scored " + points + " points!");
		Spel.saveScore();
	}

	class GreyBoxes extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			for(int i = 1; i< Grid.widthNumber +1; i++){
				grid.setHoldsBlock(i, Grid.heightNumber +1, true);
				for(int j = 1; j< Grid.heightNumber +1; j++){
					grid.setHoldsBlock(Grid.widthNumber +1, j, true);
					grid.setHoldsBlock(Grid.widthNumber +2, j, true);
					grid.setHoldsBlock(0, j, true);
					/*
					if(grid.getHoldsBlock(i, j)){
						g2d.setColor(grid.color_occupied);
					}else {
						g2d.setColor(grid.color_free);
					}
					*/
					g2d.setColor(grid.color);
					g2d.fillRect(i*Grid.getDimension(), j*Grid.getDimension(), Grid.getDimension()-2*Grid.getDistance(), Grid.getDimension()-2*Grid.getDistance());
				}
			}

			for (BlockFormation blockFormation: getBlockFormationList()){
				blockFormation.draw(g2d);
			}

			for (Block groundBlock : groundBlocks){
				groundBlock.draw(g2d);
			}
		}
	}

	public class CustomComparator implements Comparator<Block> {
		@Override
		public int compare(Block block1, Block block2) {
			return block2.getj().compareTo(block1.getj());
		}
	}
}