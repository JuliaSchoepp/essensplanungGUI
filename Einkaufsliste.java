package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dateiname: Einkaufsliste.java
 * Beschreibung: Einkaufsliste für den Wochenplan wird erstellt.
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */

public class Einkaufsliste {
	/** Anhand eines Wochenplans wird eine Einkaufsliste (Zutat - Anzahl) erstellt.
    *
    */
    private HashMap<String, Integer> einkaufsliste = new HashMap<String, Integer>();

    public Einkaufsliste(String[] wochenGerichte, Map<String, Gericht> rezepte) { //Constructor
           for (String gerichtName : wochenGerichte) { // loop über gerichte in plan
             Gericht gerichtObj  = rezepte.get(gerichtName);
             if (gerichtObj != null) { // Wenn Gericht in Kochbuch ist
               String[] zutaten = gerichtObj.getZutaten();
               for (String zutat : zutaten) {
                 if (!einkaufsliste.containsKey(zutat)) { // wenn noch nicht in Einkaufsliste
                   einkaufsliste.put(zutat,1); // setze Menge = 1
                 } else {
                   int count = einkaufsliste.get(zutat); // get Menge aus Liste
                   einkaufsliste.put(zutat, count+1); // Erhöhe Menge zutat um 1
                 } // Ende if/else
                 } // Ende Loop über zutaten in Gericht
               } // Ende if (Gericht in Kochbuch)
             } // Ende Loop über Gerichte
           } // Ende Constructor

    public HashMap<String, Integer> getEinkaufsliste(){
      return this.einkaufsliste;
    }


    @Override
    public String toString() {
    	List<String> sortedKeys = new ArrayList<>(this.einkaufsliste.keySet());
    	Collections.sort(sortedKeys);
    	String ausgabe = "";
    	for (String zutat: sortedKeys) {
    		ausgabe += zutat + ": " + this.einkaufsliste.get(zutat) + "x \n";
    	}
      return ausgabe;
    }
}
