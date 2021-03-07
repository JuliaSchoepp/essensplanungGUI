package application;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GerichtWindow {
	
	private String name;
	private ArrayList<String> zutaten; // für Konstruktor zu array
	private int saison;
	private int komplex;
	private int typ;
	
	public GerichtWindow() {
		this.name = "";
		this.zutaten = new ArrayList<>();
		this.saison = 0;
		this.komplex = 1;
		this.typ = 5;
	}
	
	public GerichtWindow(String inputName) {
		this.name = inputName;
		this.zutaten = new ArrayList<>();
		this.saison = 0;
		this.komplex = 1;
		this.typ = 5;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void show() {
		
		// Basics
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Neues Gericht");
		
		// Label Name
		Label nameLabel = new Label("Name:");
		
		// Textfield Name
		TextField nameTF = new TextField(this.getName());
		nameTF.setOnAction(e -> { 
				this.name = nameTF.getText(); 
				e.consume();
			});
		
		// Titel-Label Zutaten
		Label zutatenTitel = new Label("Zutaten:");
		
		// Liste-Label Zutaten
		Label zutatenLabel = new Label();
		zutatenLabel.setWrapText(true);
		
		// Textfield Zutaten
		TextField zutatenTF = new TextField();
		zutatenTF.setPromptText("Zutat eingeben & Enter");
		zutatenTF.setOnAction(e -> { 
				this.zutaten.add(zutatenTF.getText());
				zutatenTF.setText("");
				updateLabel(zutatenLabel);
				e.consume();
			});
		
		// Dropdown Saison, Komplex, Typ
		ComboBox<String> saisonBox = new ComboBox<>();
		saisonBox.setPrefWidth(100);
		saisonBox.getItems().addAll("Ohne", "Frühling/Sommer", "Herbst/Winter");
		saisonBox.getSelectionModel().select(0);
		saisonBox.setOnAction(e -> {
			this.saison = saisonBox.getSelectionModel().getSelectedIndex();
		});
		
		ComboBox<String> komplexBox = new ComboBox<>();
		komplexBox.setPrefWidth(100);
		komplexBox.getItems().addAll("Easy", "Normal", "Fancy");
		komplexBox.getSelectionModel().select(1);
		komplexBox.setOnAction(e -> {
			this.komplex = komplexBox.getSelectionModel().getSelectedIndex(); 
		});
		
		ComboBox<String> typBox = new ComboBox<>();
		typBox.setPrefWidth(100);
		typBox.getItems().addAll("Pasta", "Suppe", "Kalt", "Reis", "Kartoffeln", "Andere");
		typBox.getSelectionModel().select(5);
		typBox.setOnAction(e -> {
			this.typ = typBox.getSelectionModel().getSelectedIndex(); 
		});
		
		
		Button okButton = new Button("Erfassen");
		okButton.setPrefWidth(120);
		okButton.setOnAction(e -> {
			this.name = nameTF.getText(); 
			String[] arr = new String[this.zutaten.size()]; 
	        arr =this.zutaten.toArray(arr); 
			Gericht gericht = new Gericht(this.name, arr, this.saison, this.komplex, this.typ);
			Kochbuch.add(gericht);
			try {
				WriteToFile.addLine(gericht.toCSVString(),Main.rezeptePfad);
				} catch (IOException ex) {
					zutatenLabel.setText("Gericht konnte nicht abgespeichert werden");
				}
			window.close();
		});
		
		Button escButton = new Button("Zurück");
		escButton.setPrefWidth(120);
		escButton.setOnAction(e -> {
			window.close();
		});
		
		
		
		// Grid setup
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		// Grid Positions
		GridPane.setConstraints(nameLabel, 0, 0);
		GridPane.setConstraints(nameTF, 1, 0,2,1);
		GridPane.setConstraints(zutatenTitel, 0, 1);
		GridPane.setConstraints(zutatenTF, 1, 1, 2, 1);
		GridPane.setConstraints(zutatenLabel, 1, 2, 2,4);
		
		grid.getChildren().addAll(nameLabel, nameTF, zutatenTitel, zutatenTF, zutatenLabel);
		
		
		// Layout overall
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(15, 12, 15, 12));
		buttonBox.setSpacing(10);
		buttonBox.getChildren().addAll(saisonBox, komplexBox, typBox);
		
		HBox bottomBox = new HBox();
		bottomBox.setAlignment(Pos.CENTER_RIGHT);
		bottomBox.setPadding(new Insets(15, 12, 15, 12));
		bottomBox.setSpacing(10);
		bottomBox.getChildren().addAll(escButton, okButton);
		
		VBox lowerPart = new VBox();
		lowerPart.getChildren().addAll(buttonBox, bottomBox);
		
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(grid);
		mainPane.setBottom(lowerPart);
		
		// Scene
		Scene scene = new Scene(mainPane, 400,400);
		scene.getStylesheets().add(ConfWindow.class.getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
	}

	private void updateLabel(Label zutatenLabel) {
		String zutaten = "";
		for (String zutat: this.zutaten) {
			zutaten += zutat + "\n";
		}
		zutatenLabel.setText(zutaten);
	}

	
}
