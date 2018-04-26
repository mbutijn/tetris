import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class Spel {
	static int xBound = 600;
	static int yBound = 750;
	
	public static void main(String[] args){
		Veld veld = new Veld();
		veld.setVisible(true);
		veld.setSize(xBound, yBound);
		veld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		veld.setTitle("Tetris");

		veld.startSpel();
	}
	
	private static String getDatum(){
		Calendar calendar = new GregorianCalendar();
		String jaar = String.format("%04d",calendar.get(Calendar.YEAR));
		String maand = String.format("%02d",calendar.get(Calendar.MONTH)+1);
		String dag = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
		String uur = String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY));
		String minuut = String.format("%02d",calendar.get(Calendar.MINUTE));
		return uur + ":" + minuut + "  " + dag + "-" + maand + "-" + jaar;
	}

	static void bewaarScore() {
		String laatstescore = getDatum() + ":  " + String.format("%04d",Veld.punten);
        ObjectInputStream inputstream;

		try {
			inputstream = new ObjectInputStream(new FileInputStream("Game.ser"));
			String oudehighscore = (String) inputstream.readObject();
			String highscore = laatstescore + "\n" + oudehighscore;
			Veld.highscore.setText(highscore);
		} catch(Exception ex) {
			ex.printStackTrace();
			Veld.highscore.setText(laatstescore);
		}
		try {
			ObjectOutputStream outputstream = new ObjectOutputStream(new FileOutputStream("Game.ser"));
			outputstream.writeObject(Veld.highscore.getText());
			outputstream.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
