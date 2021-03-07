package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// Bug: Neu hinzugefügte Gerichte nicht in Kochbuch Dropdown
// Name bei neuem Gericht darf nicht leer sein

public class Main extends Application {
	
	public static String rezeptePfad = "./rezepte.csv";
	public static String planPfad = "../wochenplan.md";
	public static String listePfad = "../einkaufsliste.txt";
	public static String statPfad = "./statistik.csv";
	
	
	// Main function
	public static void main(String[] args) {
		
		Kochbuch.ladeRezepte(rezeptePfad);
		Wochenplan.randInit();
		launch(args);
	}
	
	
	//
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		Scene mainScene = MainScene.buildScene();
		
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Essensplanung");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
		
}










