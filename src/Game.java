import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class Game {
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
		String year = String.format("%04d",calendar.get(Calendar.YEAR));
		String month = String.format("%02d",calendar.get(Calendar.MONTH)+1);
		String day = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
		String hour = String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY));
		String minute = String.format("%02d",calendar.get(Calendar.MINUTE));
		return hour + ":" + minute + "  " + day + "-" + month + "-" + year;
	}

	static void saveScore(Field field) {
		String lastScore = getDate() + ":  " + String.format("%04d", field.points);

		try {
			ObjectInputStream inputstream = new ObjectInputStream(new FileInputStream("Game.ser"));
			String oudehighscore = (String) inputstream.readObject();
			String highscore = lastScore + "\n" + oudehighscore;
			field.highscore.setText(highscore);
		} catch(Exception ex) {
			ex.printStackTrace();
			field.highscore.setText(lastScore);
		}
		try {
			ObjectOutputStream outputstream = new ObjectOutputStream(new FileOutputStream("Game.ser"));
			outputstream.writeObject(field.highscore.getText());
			outputstream.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
//
}