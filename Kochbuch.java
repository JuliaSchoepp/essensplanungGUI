package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Dateiname: Kochbuch.java
 * Beschreibung: Klasse Kochbuch - Sammlung von Gerichten
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */

public class Kochbuch {

	/** Hier werden Objekte des Typs Gericht erstellt und gespeichert.
    *
    */
	
    private static ArrayList<Gericht> rezepte = new ArrayList<>();
    
    //private static ArrayList<Gericht> choiceForRand = new ArrayList<>();

    /**
     * Einlesen der Rezepte aus CSV-Datei, erstellen von Objekten "Gericht" und
     * abspeichern unter Rezepte.
     * @param path der Name/Pfad der CSV-Datei
	  */
    public static void ladeRezepte(String path) {
   	 String zeile; // line initiieren

	   	 // Try-cath für Lesen der CSV Datei mit Hilfe eines Buffered Reader
	   	 try {
	   		 BufferedReader br = new BufferedReader(new FileReader(path));
	
	   		 // solange noch Zeilen da sind:
	   		 while ((zeile = br.readLine()) != null) {
	
	   			 // mit Komma getrennte Werte als Array speichern
	   			 String[] gerichtDaten = zeile.split(",");
	   			 int len = gerichtDaten.length;
	
	   			 //Daten in entsprechenden Variablen speichern
	   			 String name = gerichtDaten[0];
	   			 int saison = Integer.parseInt(gerichtDaten[len-3]);
	   			 int komplex = Integer.parseInt(gerichtDaten[len-2]);
	   			 int typ = Integer.parseInt(gerichtDaten[len-1]);
	   			 String[] zutaten = Arrays.copyOfRange(gerichtDaten, 1,len-3);
	
	   			 // Gericht erstellen und abspeichern
	   			 Gericht neuesGericht = new Gericht(name, zutaten, saison, komplex, typ);
	   			 rezepte.add(neuesGericht);
	   		 		}
	
	   		 br.close(); // buffered reader schließen
	
	   	 } catch (IOException e) {
	   		 System.out.println("Fehler beim Lesen der CSV-Datei.");
	   	 }
    } // Ende Methode generateFromCSV
    

    /**
     * Füge Gericht zu Kochbuch hinzu
     *
     * @return rezepteMap
	  */
    public static void add(Gericht neuesGericht) {
   	 rezepte.add(neuesGericht);
    }
    
    /**
     * Getter Rezepte Liste
     *
     * @return rezepteMap
	 */
    public static ArrayList<Gericht> getRezepte() {
      return rezepte;
    }

    /**
     * Ersetllen einer Liste, die die Namen aller Gerichte enthält
     *
     * @return Namen
	 */
    public static ArrayList<String> getNamen() {
      ArrayList<String> namen = new ArrayList<String>();
      for (Gericht g: rezepte) {
        namen.add(g.getName());
      }
      return namen;
    }

    /**
     * Wählt den Namen eines zufälligen Gerichts aus
     *
     * @return String zufälliges Gericht
	  */
    public static String pickRandom() {
      ArrayList<String> namen = getNamen();
      int len = namen.size();
      Random randGen = new Random();
      int ix = randGen.nextInt(len);
      return namen.get(ix);
    }

    /**
     * Erstellen einer Map, die jedem Namen (String) das zugeh. Objekt (Gericht() zuweist
     *
     * @return rezepteMap
	 */
    public static Map<String, Gericht> getRezepteMap() {
      Map<String, Gericht> rezepteMap = rezepte.stream()
                                               .collect(Collectors.toMap(Gericht::getName, g -> g));
      return rezepteMap;
    }

    /**
     * Hilfsfunktion für listGerichte : Bündelt je 3
     * Namen in einen formatierten String, nur für zahlen % 3 = 0 geeignet
     *
     * @return formatierte Ausgabe
     */
    private static String dreierreihe(int x, String format, ArrayList<String> namen) {
      String ausgabe = "";
      for (int i = 0; i < x; i += 3) {
         String nextLine = String.format(format, namen.get(i),
           namen.get(i+1), namen.get(i+2));
         ausgabe += nextLine;
         }
     return ausgabe;
    }

    /**
     * Erstellen eines Strings mit allen Gerichtsnamen im Kochbuch
     * Formatiert auf 3 Gerichte pro Zeile
     *
     * @return Gerichte Liste
     */
     public static String listGerichte() {
       String ausgabe = " --------------- \n Alle Gerichte: \n";
       String format = "%25s%25s%25s\n"; // rechts bündig 25 Zeichen Padding
       String nextLine;
       ArrayList<String> namen = getNamen();

       int n = namen.size();

       // index problem bei nicht durch 3 teilbaren Anzahlen von Gerichten umgehen
       switch (n%3) {
         case 0:
           ausgabe += dreierreihe(n,format,namen);
           break;
         case 1:
           ausgabe += dreierreihe(n-1,format,namen);
           nextLine = String.format(format, namen.get(n-1), "", "");
           ausgabe += nextLine;
           break;
         case 2:
           ausgabe += dreierreihe(n-2,format,namen);
           nextLine = String.format(format, namen.get(n-2),
             namen.get(n-1)  , "");
           ausgabe += nextLine;
           break;
           }

       ausgabe +=  " ---------------";
       return ausgabe;
     }	
	
}
