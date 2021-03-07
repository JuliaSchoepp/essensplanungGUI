package application;

import java.io.IOException;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.concurrent.TimeUnit;

public class ConfWindow {
	
	private static String[] mahlzeiten;
	//private static List<Label> labels = new ArrayList<>();
	private static String[] tage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	private static Label statusLabel;
	private static Stage window;
	
	public static void show() {
		// Basics
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Preview Wochenplan");
		
		// GridPane setup
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		// Grid füllen
		// Column Labels
		Label mittag = new Label("Mittag");
		mittag.setPrefWidth(150);
		mittag.setStyle("-fx-font-size: 15;");
		GridPane.setConstraints(mittag, 1, 0);
		grid.getChildren().add(mittag);
		Label abend = new Label("Abend");
		abend.setPrefWidth(150);
		abend.setStyle("-fx-font-size: 15;");
		GridPane.setConstraints(abend, 2, 0);
		grid.getChildren().add(abend);
		
		// Zeilen Labels
		for (int i = 0; i<tage.length; i++) {
			Label l = new Label(tage[i]);
			l.setPrefWidth(150);
			l.setStyle("-fx-font-size: 15;");
			GridPane.setConstraints(l, 0, i+1);
			grid.getChildren().add(l);
		}
		
		//Mahlzeiten Labels
		mahlzeiten = Wochenplan.getPlan();
		
		for (int i =0; i<mahlzeiten.length; i++) {
			Label l = new Label(mahlzeiten[i]);
			l.setPrefWidth(150);
			int col = i%2==0 ? 1 : 2;
			int row = i/2 + 1;
			GridPane.setConstraints(l, col, row);
			grid.getChildren().add(l);
		}
		
		// Buttons
		Button ok = new Button("Ok");
		ok.setPrefWidth(120);
		ok.setOnAction(e -> handleOk());
		Button zurück = new Button("Zurück");
		zurück.setPrefWidth(120);
		zurück.setOnAction(e -> window.close());
		
		statusLabel = new Label("Sieht gut aus?");
		HBox lowMid = new HBox();
		lowMid.setAlignment(Pos.CENTER);
		lowMid.setPadding(new Insets(15, 12, 15, 12));
		lowMid.setSpacing(10);
		lowMid.getChildren().add(statusLabel);
		
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(15, 12, 15, 12));
		bottom.setSpacing(10);
		bottom.getChildren().addAll(ok, zurück);
		
		VBox lowerPart = new VBox();
		lowerPart.getChildren().addAll(lowMid, bottom);
		
		BorderPane mainLayout = new BorderPane();
		mainLayout.setCenter(grid);
		mainLayout.setBottom(lowerPart);
		
		Scene sc = new Scene(mainLayout,650,400);
		sc.getStylesheets().add(ConfWindow.class.getResource("confirm.css").toExternalForm());
		window.setScene(sc);
		window.showAndWait();
	}
	

	private static void handleOk() {
		boolean status;
		// Einkaufsliste & Wochenplan erstellen und abspeichern; Statistik updaten
		Map<String, Gericht> rezepte = Kochbuch.getRezepteMap();
		Einkaufsliste liste = new Einkaufsliste(Wochenplan.getPlan(), rezepte);
		String einkaufsliste = liste.toString();
		String plan = Wochenplan.getString();
		Map<String, Integer> statistik = Statistik.updateStatistik(Main.statPfad);
		try {
			WriteToFile.write(einkaufsliste, Main.listePfad);
			WriteToFile.write(plan, Main.planPfad);
			Statistik.schreibeStatistik(statistik, Main.statPfad);
			status = true;
			} catch (IOException ex) {
			statusLabel.setText("Fehler beim Speichern");
			status = false;
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (status) {
			window.close();
			Platform.exit();
		}
	}
}
