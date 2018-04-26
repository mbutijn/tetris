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

//	private Grid grid = new Grid(30, 2, 13, 21);
	private Grid grid = new Grid(30, 2, 5, 12);
	private static GreyBoxes grijzehokjes;
	private static ArrayList<BlockFormation> BlokFormaties = new ArrayList<BlockFormation>();
	static ArrayList<Block> LigBlokken = new ArrayList<Block>();
	static ArrayList<Integer> VerwijderIndex = new ArrayList<Integer>();
	private Timer timer;
	static int punten;
	private static JTextField puntenveld = new JTextField("0", 4);
	static JTextArea highscore = new JTextArea("0", 0, 0);
	private int count = 0;

	Field(){
		grijzehokjes = new GreyBoxes();
		setContentPane(grijzehokjes);
		setKeyBoardListeners();
		setResizable(false);
		setLayout(new BorderLayout());
		JLabel SpelerPunten = new JLabel("Punten: ");
		JLabel HighScoreLabel = new JLabel("Datum:                   Scores:");

		JPanel puntenpaneel = new JPanel();
		JPanel highscorepaneel = new JPanel();
		puntenveld.setEditable(false);
		puntenveld.setVisible(true);
		highscore.setEditable(false);
		highscore.setVisible(true);

		puntenpaneel.add(SpelerPunten);
		puntenpaneel.add(puntenveld);

		ObjectInputStream inputstream;

		try {
			inputstream = new ObjectInputStream(new FileInputStream("Game.ser"));
			String highscores = (String) inputstream.readObject();
			highscore.setText(highscores);
		} catch(Exception ex) {
			System.out.println("laden highscores niet gelukt");
		}

		highscorepaneel.setLayout(new BorderLayout(1,1));
		highscorepaneel.add(HighScoreLabel,BorderLayout.NORTH);
		highscorepaneel.add(highscore,BorderLayout.CENTER);
		highscorepaneel.add(new Panel(),BorderLayout.EAST);

		add(puntenpaneel, BorderLayout.SOUTH);
		add(highscorepaneel, BorderLayout.EAST);
	}

	void startGame() {
		timer = new Timer(50, run);
		timer.setRepeats(true);
		timer.start();
	}

	private void setBFL(ArrayList<BlockFormation> BlokFormaties){
		Field.BlokFormaties = BlokFormaties;
	}

	private ArrayList<BlockFormation> getBlokFormatieLijst(){
		return BlokFormaties;
	}

	private ActionListener run = new ActionListener() {
		boolean neergekomen;
		public void actionPerformed(ActionEvent evt) {
			count++;
			grijzehokjes.paintImmediately(new Rectangle(0, 0, Spel.xBound, Spel.yBound));
			if(BlokFormaties.isEmpty()){
				BlokFormaties.add(new BlockFormation((int) Math.round(1+Math.random()*(Grid.BreedteAantal-5)), SelectType(), grid));
				setBFL(BlokFormaties);
			}
			if (count == 10){
				neergekomen = getBlokFormatieLijst().get(0).checkBelow(grid);

				if (neergekomen){
					MakeNewOne(getBlokFormatieLijst().get(0));
				}
				count = 0;
			}
		}
	};

	private void MakeNewOne(BlockFormation blokformatie) {
		blokformatie.layDownBlocks(this);
		grid.makeFullRows();
		if(!VerwijderIndex.isEmpty()){
			RemoveEveryBlock();
		}
		getBlokFormatieLijst().remove(0);
		getBlokFormatieLijst().add(new BlockFormation((int) Math.round(1+Math.random()*(Grid.BreedteAantal-5)), SelectType(), grid));
	}

	private void RemoveEveryBlock(){
		int aantal = LigBlokken.size();
		int gatgrootte = 0;
		Collections.sort(VerwijderIndex);

		for (int k = 0; k<aantal; k++){
			if(!VerwijderIndex.isEmpty()){
				if(k == VerwijderIndex.get(0)){
					LigBlokken.remove(gatgrootte);
					VerwijderIndex.remove(0);
				} else{
					gatgrootte++;
				}
			}
		}
		Collections.sort(LigBlokken, new CustomComparator());
		DropGroundBlocks();
	}

	private void DropGroundBlocks() {
		for (int i = 0; i<Grid.VolleRijen.size(); i++){
			for (Block ligblok: LigBlokken){
				if (ligblok.j<Grid.VolleRijen.get(i)){
					ligblok.drop(grid);
				}
			}
		}
	}

	private char SelectType() {
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
		type = '-';
		return type;
	}

	private void setKeyBoardListeners() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
	}

	static void GivePoint() {
		punten++;
		puntenveld.setText(""+punten);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();

		if (code == KeyEvent.VK_DOWN) {
			getBlokFormatieLijst().get(0).checkBelow(grid);
		} else if (code == KeyEvent.VK_RIGHT) {
			getBlokFormatieLijst().get(0).checkRight(grid);
		} else if (code == KeyEvent.VK_LEFT){
			getBlokFormatieLijst().get(0).checkLeft(grid);
		} else if (code == KeyEvent.VK_ESCAPE){
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		} else if (code == KeyEvent.VK_SPACE){
			getBlokFormatieLijst().get(0).rotate(grid);
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

	void StopGame() {
		timer.stop();
		JOptionPane.showMessageDialog(null, "Game over! \n" + "U heeft " + punten + " punten gescoord!");
		Spel.saveScore();
	}

	class GreyBoxes extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			for(int i = 1; i< Grid.BreedteAantal+1; i++){
				grid.setBezet(i, Grid.HoogteAantal+1, true);
				for(int j = 1; j< Grid.HoogteAantal+1; j++){
					grid.setBezet(Grid.BreedteAantal+1, j, true);
					grid.setBezet(Grid.BreedteAantal+2, j, true);
					grid.setBezet(0, j, true);

					if(grid.getBezet(i, j)){
						g2d.setColor(grid.bezetkleur);
					}else {
						g2d.setColor(grid.vrijkleur);
					}

					//g2d.setColor(grid.kleur);
					g2d.fillRect(i*Grid.getDimension(), j*Grid.getDimension(), Grid.getDimension()-2*Grid.getDistance(), Grid.getDimension()-2*Grid.getDistance());
				}
			}

			for (BlockFormation blokformatie: getBlokFormatieLijst()){
				blokformatie.draw(g2d);
			}

			for (Block ligblok : LigBlokken){
				ligblok.draw(g2d);
			}
		}
	}

	public class CustomComparator implements Comparator<Block> {
		@Override
		public int compare(Block blok1, Block blok2) {
			return blok2.getj().compareTo(blok1.getj());
		}
	}
}