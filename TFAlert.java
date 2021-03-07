package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Dateiname: TFAlert.java
 * Beschreibung: Fenster das angezeigt wird, falls ein Gericht nicht im Kochbuch ist
 *
 * @author Julia Schöpp (s54931@beuth-hochschule.de)
 * @version 11.0.9, 10/2020
 */


public class TFAlert {

	public static void display(String input) {
		// Basics
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Obacht");
		window.setMinWidth(250);
		
		// Label & Buttons
		Label label = new Label(input + " ist nicht im Kochbuch - Neues Gericht erstellen?");
		Button jaButton = new Button("Ja");
		jaButton.setOnAction(e -> {
			GerichtWindow gBox = new GerichtWindow(input);
			gBox.show();
			window.close();
		});
		jaButton.setPrefWidth(60);
		Button neinButton = new Button("Nein");
		neinButton.setOnAction(e -> window.close());
		neinButton.setPrefWidth(60);
		
		// Layout
		HBox buttonLayout = new HBox();
		buttonLayout.getChildren().addAll(jaButton, neinButton);
		buttonLayout.setAlignment(Pos.CENTER);
		buttonLayout.setSpacing(10);
		VBox mainLayout = new VBox();
		mainLayout.setPadding(new Insets(10, 7, 10, 7));
		mainLayout.setSpacing(10);
		mainLayout.getChildren().addAll(label, buttonLayout);
		mainLayout.setAlignment(Pos.CENTER);
		
		// Scene
		Scene scene = new Scene(mainLayout);
		scene.getStylesheets().add(ConfWindow.class.getResource("confirm.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
		
	}


}
