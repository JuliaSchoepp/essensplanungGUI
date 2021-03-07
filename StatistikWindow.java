package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Dateiname: TFAlert.java
 * Beschreibung: Fenster das angezeigt wird, falls ein Gericht nicht im Kochbuch ist
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */

public class StatistikWindow {
	
	public static void display() {
		// Basics
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Statistik - Top 5 Gerichte");
		window.setMinWidth(300);
		
		// Label & Buttons
		Label label = new Label(Statistik.getTopStats(Main.statPfad));
		label.setWrapText(true);
		label.setPrefWidth(270);
		
		// Layout
		VBox mainLayout = new VBox();
		mainLayout.setPadding(new Insets(10, 7, 10, 7));
		mainLayout.setSpacing(10);
		mainLayout.getChildren().addAll(label);
		mainLayout.setAlignment(Pos.CENTER);
		
		// Scene
		Scene scene = new Scene(mainLayout);
		scene.getStylesheets().add(ConfWindow.class.getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
		
	}
}
