package application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Dateiname: WriteToFile.java
 * Beschreibung: Statische Methode um Ergebnisse in Dateien abzuspeichern
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */

public class WriteToFile {

	public static void write(String text, String filename) throws IOException{
		FileWriter writer = new FileWriter(filename);
		writer.write(text);
		writer.close();
		//System.out.println("Datei wurde abgespeichert.");
	}


	public static void addLine(String text, String filename) throws IOException{
		FileWriter writer = new FileWriter(filename, true);
		writer.write(text + "\n");
		writer.close();
	}

	/**
	 * liest einen Text von den Nutzenden ein
	 *
	 * @param aufforderung Text der als Eingabeaufforderung auf der Konsole
	 *                     erscheinen soll
	 * @return von den Nutzenden eingegebener Text
	 */
	public static String eingabeText(String aufforderung) {
			Scanner eingabe;
			eingabe = new Scanner(System.in);
			eingabe.useDelimiter(System.lineSeparator());

			System.out.print(aufforderung);
			String text = eingabe.next();
			eingabe.close();
			return text;
	}

	/**
	 * liest einen Integer von den Nutzenden ein
	 *
	 * @param aufforderung Text der als Eingabeaufforderung auf der Konsole
	 *                     erscheinen soll
	 * @return von den Nutzenden eingegebener Text
	 */
	public static int eingabeZahl(String aufforderung) {
			Scanner eingabe;
			eingabe = new Scanner(System.in);
			eingabe.useDelimiter(System.lineSeparator());

			System.out.print(aufforderung);
			int zahl = eingabe.nextInt();
			eingabe.close();
			return zahl;
	}	
	
}
