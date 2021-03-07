package application;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Comparator;

/**
 * Dateiname: Statistik.java
 * Beschreibung: Statische Methoden zum erstellen einer Statistik
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */

public class Statistik {
	
    /** statistik (Map) aus CSV Datei erzeugen
     *
     */
    public static Map<String, Integer> getStatistik(String path) {
      String zeile;
      Map<String, Integer> statistik = new HashMap <String, Integer>();

      // Try-cath für Lesen der CSV Datei mit Hilfe eines Buffered Reader
   	 try {
   		 BufferedReader br = new BufferedReader(new FileReader(path));

   		 // solange noch Zeilen da sind:
   		 while ((zeile = br.readLine()) != null) {

   			 // mit Komma getrennte Werte als Array speichern
   			 String[] stats = zeile.split(",");

   			 //Daten zu Map hinzufügen
          statistik.put(stats[0], Integer.valueOf(stats[1]));
        }
   		br.close();
        } catch (IOException e) {
     		 System.out.println("Fehler beim Lesen der CSV-Datei.");
     	 }
       return statistik;
    }

    /** statistik (Map) mit Wochenplan updaten
     *
     */
    public static Map<String, Integer> updateStatistik(String path) {
      String[] toAdd = Wochenplan.getPlan();
      Map<String, Integer> statistik = getStatistik(path);
      for (String essen: toAdd) {
        if (essen.equals("Reste")) continue;
        if (statistik.containsKey(essen)) {
          statistik.put(essen, statistik.get(essen)+1);
        }
        else {
          statistik.put(essen, 1);
        }
      }
      return statistik;
    }

    /** statistik (Map) in CSV schreiben
     * @throws IOException 
     *
     */
    public static void schreibeStatistik(Map<String, Integer> statistik, String path) throws IOException {
      StringBuilder text = new StringBuilder();
      statistik.forEach((k,v) -> text.append(k+","+String.valueOf(v)+"\n"));
      String textStr = text.toString();
      WriteToFile.write(textStr,path);
    }


    /** Top 5 Statistikeinträge als String
     *
     */
    public static String getTopStats(String path){
    	Map<String, Integer> statistik = getStatistik(path);
    	StringBuilder top5 = new StringBuilder();
    	statistik.entrySet()
               	.stream()
               	.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
               	.limit(5)
               	.forEach(e -> top5.append("-" + e.getKey() + ": " + e.getValue() + "x" + "\n"));
    	return top5.toString();
    }
    
    /** Top 5 Kategorien "Typ" als String
    *
    */
   public static String getTopTyp(String path){
   	// toDo
	   return null;
   }
}
