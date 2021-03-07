package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainScene {
	
	public static Scene buildScene() {
		
		// Grid in der Mitte mit Mahlzeiten, Buttons, Textfeldern pro Mahlzeit
		GridPane mainGrid = GridBuilder.buildGrid();
		
		// Unterer Teil - fertig/reset - Box
		HBox bottomBox = new HBox();
		bottomBox.setAlignment(Pos.CENTER_RIGHT);
		bottomBox.setPadding(new Insets(15, 12, 15, 12));
		bottomBox.setSpacing(10);
		Button fertigButton = new Button("Fertig");
		fertigButton.setOnAction(e -> ConfWindow.show());
		fertigButton.setPrefWidth(120);
		Button resetButton = new Button("Random Reset");
		resetButton.setOnAction(e -> randomReset());
		resetButton.setPrefWidth(120);
		bottomBox.getChildren().addAll(resetButton, fertigButton);
		
		// Linker Teil: Kochbuch anzeigen, Neues Gericht, Help, Statistik
		VBox leftMenu = new VBox();
		leftMenu.setFillWidth(true);
		leftMenu.setAlignment(Pos.TOP_LEFT);
		leftMenu.setPadding(new Insets(15, 12, 15, 12));
		leftMenu.setSpacing(10);
		String basicText = "Infos zu Gericht...";
		Label gerichtInfo = new Label(basicText);
		gerichtInfo.setWrapText(true);
		gerichtInfo.setMaxWidth(180);
		ComboBox<String> kochbuchBox = new ComboBox<>();
		List<String> gerichteNamen = Kochbuch.getNamen();
		Collections.sort(gerichteNamen);
		kochbuchBox.getItems().addAll(gerichteNamen);
		kochbuchBox.setPromptText("Kochbuch anzeigen");
		Map<String, Gericht> rezepteMap = Kochbuch.getRezepteMap();
		kochbuchBox.setOnAction(e -> {
			String input = kochbuchBox.getValue();
			gerichtInfo.setText(basicText + "\n" + rezepteMap.get(input).toString());
		});
		kochbuchBox.setMaxWidth(99999D);
		
		Button newGerichtButton = new Button("Neues Gericht");
		newGerichtButton.setAlignment(Pos.CENTER_LEFT);
		newGerichtButton.setOnAction(e -> {
			GerichtWindow gerichtBox = new GerichtWindow();
			gerichtBox.show();
		});
		newGerichtButton.setMaxWidth(99999D);
		Button statistikButton = new Button("Statistik");
		statistikButton.setAlignment(Pos.CENTER_LEFT);
		statistikButton.setOnAction(e -> StatistikWindow.display());
		statistikButton.setMaxWidth(99999D);
		leftMenu.getChildren().addAll(newGerichtButton, statistikButton, kochbuchBox, gerichtInfo);
		
		// Foto Top
		FileInputStream inputstream;
		ImageView imageView = null;
		try {
			inputstream = new FileInputStream("./IMG_6259_cut.jpeg");
			Image image = new Image(inputstream);
			imageView = new ImageView(image);
		    imageView.setFitWidth(900); 
		    imageView.setPreserveRatio(true);  
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		 
		
		// Gesamtlayout Border Pane
		BorderPane mainLayout = new BorderPane();
		mainLayout.setTop(imageView);
		mainLayout.setCenter(mainGrid);
		mainLayout.setBottom(bottomBox);
		mainLayout.setLeft(leftMenu);
		
		Scene scene = new Scene(mainLayout,900,550);
		scene.getStylesheets().add(ConfWindow.class.getResource("confirm.css").toExternalForm());

		return scene;
	}


	private static void randomReset() {
		Wochenplan.randInit();
		List<Label> labels = GridBuilder.getMzLabels();
		for (int i=0; i<Wochenplan.mahlzeiten.length; i++) {
			labels.get(i).setText(Wochenplan.mahlzeiten[i] + ": " + Wochenplan.getPlan()[i]);
		}
	}

}


