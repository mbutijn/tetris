import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class Spel {
	static int xBound = 600;
	static int yBound = 750;

	public static void main(String[] args){
		Field field = new Field();
		field.setVisible(true);
		field.setSize(xBound, yBound);
		field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		field.setTitle("Tetris");

		field.startGame();
	}

	private static String getDate(){
		Calendar calendar = new GregorianCalendar();
		String jaar = String.format("%04d",calendar.get(Calendar.YEAR));
		String maand = String.format("%02d",calendar.get(Calendar.MONTH)+1);
		String dag = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
		String uur = String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY));
		String minuut = String.format("%02d",calendar.get(Calendar.MINUTE));
		return uur + ":" + minuut + "  " + dag + "-" + maand + "-" + jaar;
	}

	static void saveScore() {
		String laatstescore = getDate() + ":  " + String.format("%04d", Field.punten);
		ObjectInputStream inputstream;

		try {
			inputstream = new ObjectInputStream(new FileInputStream("Game.ser"));
			String oudehighscore = (String) inputstream.readObject();
			String highscore = laatstescore + "\n" + oudehighscore;
			Field.highscore.setText(highscore);
		} catch(Exception ex) {
			ex.printStackTrace();
			Field.highscore.setText(laatstescore);
		}
		try {
			ObjectOutputStream outputstream = new ObjectOutputStream(new FileOutputStream("Game.ser"));
			outputstream.writeObject(Field.highscore.getText());
			outputstream.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}