package application;


/**
 * Dateiname: Wochenplan.java
 * Beschreibung: Wochenplan wird erstellt
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */


public class Wochenplan {

	/** User-Input für die Essensplanung für jeden Wochentag (Mittag & Abendessen)
     *  wird eingeholt, eine Liste von Gerichten wird erstellt.
     */
     private static String[] wochenplan = new String[14];

     final static public String[] mahlzeiten = {"Montag Mittag", "Montag Abend", "Dienstag Mittag", // die Mahlzeiten einer Woche
          "Dienstag Abend", "Mittwoch Mittag", "Mittwoch Abend", "Donnerstag Mittag",
          "Donnerstag Abend", "Freitag Mittag", "Freitag Abend", "Samstag Mittag",
          "Samstag Abend", "Sonntag Mittag", "Sonntag Abend"};
     
     
     /** Getter Plan
      *  @return Wochenplan, String[]
      */
     public static String[] getPlan(){
       return wochenplan;
     }
     
     
     /** Setter Mz in Plan
      *  @param index, String Mahlzeit
      */
     public static void setMahlzeit(int ind, String mahlzeit){
       wochenplan[ind] = mahlzeit;
     }
     
     /** Initialisiert Random Wochenplan
      *  
      */
     public static void randInit(){
       for (int i = 0; i<wochenplan.length; i++) {
    	   wochenplan[i] = Kochbuch.pickRandom();
       }
     }

     public static String getString() {
       String ausgabe = " Wochenplan \n\n";
       String format = " | %s | %s | %s | \n";
       ausgabe += String.format(format, "  ", "Mittag", "Abend"); // Spaltenbezeichner
       ausgabe += " | --- | --- | --- |\n";
       String[] wochentage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
       int counter = 0;

       // Pro Wochentag Mittag & Abendessen in einer Zeile ausgeben
       for (String tag : wochentage) {
    	   String nextLine = String.format(format, tag, wochenplan[counter], wochenplan[counter + 1]);
    	   ausgabe += nextLine;
    	   counter += 2;
       	} // Ende for Loop
       return ausgabe;
     } 
	
}
