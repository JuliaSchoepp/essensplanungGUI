package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Dateiname: Gericht.java
 * Beschreibung: Klasse Gericht, speichert Informationen zu Gericht
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */

public class Gericht {

	/** Hauptfunktionalität ist das Speichern einer Liste von Zutaten,
     *  darüber hinaus werde Tags für Saison, Typ und Komplexität vergeben.
     */

    private String name;
    private String[] zutaten; // Wichtig für Einkaufsliste
    // Die Tags sind für eine später zu implementierende zufällige/automatische Planerstellung wichtig.
    private int saison; // 0 Ohne, 1 Frühling/Sommer, 2 Herbst/Winter
    public static Map<Integer, String> saisonMap = new HashMap<>();
    private int komplex; // 0 super quick, 1 normal, 2 fancy
    public static Map<Integer, String> komplexMap = new HashMap<>();
    private int typ; // 0 Pasta, 1 Suppe, 2 Kalt, 3 Reis, 4 Kartoffeln, 5 Andere
    public static Map<Integer, String> typMap = new HashMap<>();

    // Constructor liest Werte von Nutzer*in ein
    public Gericht(String name) {
    	buildMaps();
    	this.name = name;
    	String input = "";
    	ArrayList<String> zutaten = new ArrayList<String>();

    	System.out.println("\nJetzt alle Zutaten angeben oder e für Ende");

    	// Zutatenliste mit variabler Länge eingeben
    	while (!input.equals("e")) {
    		input = WriteToFile.eingabeText("Nächste Zutat oder e\n");
    		if (!input.equals("e")) {
    			zutaten.add(input);
    		}
    	}

    	this.saison = WriteToFile.eingabeZahl("Saison angeben: 0 - Ohne, 1 - Frühling/Sommer, 2 - Herbst/Winter\n");
    	this.komplex = WriteToFile.eingabeZahl("Komplexität angeben: 0 - super quick, 1 - normal, 2 - fancy\n");
    	this.typ = WriteToFile.eingabeZahl("Typ angeben: 0 - Pasta, 1 - Suppe, 2 - Kalt, 3 - Reis, 4 - Kartoffeln, 5 - Andere\n");

    	String[] zut_arr = new String[zutaten.size()];
    	zut_arr = zutaten.toArray(zut_arr);
    	this.zutaten = zut_arr;
    }

    public Gericht(String name, String[] zutaten, int saison, int komplex, int typ){
    	buildMaps();
    	this.name = name;
    	this.zutaten = zutaten;
    	this.saison = saison;
    	this.komplex = komplex;
    	this.typ = typ;
    	} // Ende Constructor

    public Gericht(String name, String[] zutaten){
      this.name = name;
      this.zutaten = zutaten;
      this.saison = 0;
      this.komplex = 1;
      this.typ = 5;
    } // Constructor mit default values

    public String getName(){
      return this.name;
    }

    public String[] getZutaten(){
      return this.zutaten;
    }

    public int getSaison(){
      return this.saison;
    }

    public int getKomplex(){
      return this.komplex;
    }

    public int getTyp(){
      return this.typ;
    }
    
    private static void buildMaps() {
    	saisonMap.put(0, "Ohne");
    	saisonMap.put(1, "Frühling/Sommer");
    	saisonMap.put(2, "Herbst/Winter");
    	komplexMap.put(0, "super quick");
    	komplexMap.put(1, "normal");
    	komplexMap.put(2, "fancy");
    	typMap.put(0, "Pasta");
    	typMap.put(1, "Suppe");
    	typMap.put(2, "Kalt");
    	typMap.put(3, "Reis");
    	typMap.put(4, "Kartoffeln");
    	typMap.put(5, "Andere");
    }

    @Override
    public String toString() {
    	
    	StringBuilder ausgabe = new StringBuilder(this.name + "\n" + "\n");
    	ausgabe.append("Zutaten:\n");
	    for (String z : this.zutaten) {
	        ausgabe.append(z + ", ");
	      }
	    ausgabe.append("\n" + "\nSaison: " + saisonMap.get(this.saison) + "\n" + "Komplexität: " + 
	      komplexMap.get(this.komplex) + "\n" + "Typ: " + typMap.get(this.typ));
	    return ausgabe.toString();
    }
    
    public String toCSVString() {
    	StringBuilder ausgabe = new StringBuilder(this.name + ",");
        for (String z : this.zutaten) {
          ausgabe.append(z + ",");
        }
        ausgabe.append(this.saison + "," + this.komplex + "," + this.typ);
        return ausgabe.toString();
    }
	
}
