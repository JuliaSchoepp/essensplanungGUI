package application;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class GridBuilder {
	
	// Buttons Random, Reste
	private static List<Button> mzButtons = new ArrayList<>();
		
	// Labels Auswahl
	private static List<Label> mzLabels = new ArrayList<>();
		
	// Textfelder Auswahl
	private static List<TextField> mzTextFields = new ArrayList<>();
		
	// Main GridPane
	private static GridPane mainGrid;
	
	
	public static List<Label> getMzLabels() {
		return mzLabels;
	}
	
	// Listen mit Buttons, Textfeldern und Labels werden gefüllt
	private static void buildLists() {
		
		String[] mahlzeiten = Wochenplan.mahlzeiten;
		
		// Für jede Mahlzeit
		for (int i=0; i<mahlzeiten.length; i++) {
			
			// Labels für alle Mahlzeiten erzeugen
			Label mzLabel = new Label(mahlzeiten[i] + ": " + Wochenplan.getPlan()[i]);
			mzLabels.add(mzLabel);
			
			// Textfelder für alle Labels erzeugen
			TextField mzField = new TextField();
			
			// Breite & Promt Text
			mzField.setMaxWidth(140);
			//mzField.setPromptText("Was willst du Essen?");
			
			// Bei Enter Aktionen aus processTFInput durchführen
			mzField.setOnAction(e -> { 
				processTFInput(mzField); 
				e.consume();
			});
			mzTextFields.add(mzField);
			
			// Buttons erstellen
			// Random-Button
			Button mzButton1 = new Button("Random");
			mzButton1.setOnAction(e -> random(mzButton1));
			mzButtons.add(mzButton1);
			//Reste Button
			Button mzButton2 = new Button("Reste");
			mzButton2.setOnAction(e -> reste(mzButton2));
			mzButtons.add(mzButton2);
			
		}
	}
	
	
	// Funktionalität Reste-Button:
	private static void reste(Button bt) {
		// Index in Button Liste holen
		int ind = mzButtons.indexOf(bt);
		// Zugehörigen MZ-Index bestimmen
		int mzInd = indForButton(ind);
		// Mahlzeit im Wochenplan auf "Reste" setzen
		Wochenplan.setMahlzeit(mzInd, "Reste");		
		// Label aktualisieren
		mzLabels.get(mzInd).setText(Wochenplan.mahlzeiten[mzInd] + ": " + "Reste"); 
	}

	// Funktionalität Random Button
	private static void random(Button bt) {
		// Index in Button Liste holen
		int ind = mzButtons.indexOf(bt);
		// Zugehörigen MZ-Index bestimmen
		int mzInd = indForButton(ind);
		// Random Gericht auswählen
		String rdChoice = Kochbuch.pickRandom();	
		// In Wochenplan ersetzen
		Wochenplan.setMahlzeit(mzInd, rdChoice); 
		// Label aktualisieren
		mzLabels.get(mzInd).setText(Wochenplan.mahlzeiten[mzInd] + ": " + rdChoice); 
	}

	
	// ActionHandler Enter auf Textfeld
	private static void processTFInput(TextField tf) {
		// Text holen
		String input = tf.getText();
		// wenn nicht in Kochbuch: Alert Box
		ArrayList<String> rezepte = Kochbuch.getNamen();
		if (!rezepte.contains(input)) {
            TFAlert.display(input);
			}
		// Index der Mahlzeit bestimmen
		int ind = mzTextFields.indexOf(tf);
		// In Wochenplan aktualisieren
		Wochenplan.setMahlzeit(ind, input);
		// Label aktualisieren
		mzLabels.get(ind).setText(Wochenplan.mahlzeiten[ind] + ": " + input); 
		// Feld leeren
		tf.setText(""); 
	}
	
	// Da zwei Buttons pro Mahlzeit, index der Mahlzeit zu Button-Ind bestimmen
	private static int indForButton(int ind) {
		int ix = ind/2;
		return ix;
	}
	
	// Buttons, Labels & Textfelder im Main Grid anordnen
	public static GridPane buildGrid() {
		mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(10,10,10,10));
		mainGrid.setVgap(8);
		mainGrid.setHgap(10);
		buildLists();
		
		// Add Labels
		for (int i=0; i<mzLabels.size(); i++) {
			// Wenn Labelindex gerade, dann erste Spalte, sonst 2.
			// Erste Reihe Labels, dann immer eine frei
			int col = i%2==0 ? 0 : 4;
			int row = i%2==0 ? i : i-1;
			Label label = mzLabels.get(i);
			GridPane.setConstraints(label, col, row, 4, 1);
			mainGrid.getChildren().add(label);
		}
		
		// Add TextFields
		for (int i=0; i<mzTextFields.size(); i++) {
			// Wenn Feldindex gerade, dann erste Spalte, sonst 2.
			// Zweite Reihe Labels, dann immer eine frei
			int col = i%2==0 ? 0 : 4;
			int row = i%2==0 ? i+1 : i;
			TextField tf = mzTextFields.get(i);
			GridPane.setConstraints(tf, col, row);
			mainGrid.getChildren().add(tf);
		}
		
		// Add Buttons
		int row = 1;
		for (int i=0; i<mzButtons.size(); i++) {
			// Ungerade Reihen; Zeilen 2,3,6,7
			int col = i%4 == 0 ? 2: i%4 == 1 ? 3: i%4 == 2 ? 6: 7;
			Button bt = mzButtons.get(i);
			bt.setPrefWidth(90);
			GridPane.setConstraints(bt, col, row);
			mainGrid.getChildren().add(bt);
			if (i%4 == 3) row+=2; // bei jedem 4. Button in die übernächste Reihe springen
		}
		
		return mainGrid;
	}

}











