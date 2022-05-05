package projet_Resto_CIR3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppRunner extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ecran.fxml"));
		Parent root = loader.load();
		try {
			
			
			//Controller control = loader.getController();
			
			Scene scene = new Scene(root);
			
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("RestoManager8000");
			stage.show();
			
		}catch(Exception e) {
			System.out.println("Erreur dans l'init de la fenetre " + " : Error " + e);
		}
	}

}