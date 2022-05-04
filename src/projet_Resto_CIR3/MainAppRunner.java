package projet_Resto_CIR3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppRunner extends Application {

	public static void main(String[] args) {
				
		//Back_________________________________________________________________________________________
		
		TxtManagement inventaireTxt = new TxtManagement("inventaire");
		inventaireTxt.updateLine("Tomate", 0);
		inventaireTxt.updateLine("150", 1);
		inventaireTxt.updateLine("150", 2);
		inventaireTxt.updateLine("Salade", 3);
		inventaireTxt.updateLine("90", 4);
		inventaireTxt.updateLine("75", 5);
		inventaireTxt.ecrireTexte();

		Inventaire currInv = new Inventaire(inventaireTxt);
		currInv.subInvItem("Tomate", 12);
		currInv.subInvItem("Salade", 63);
		
		TxtManagement listeDeCourse = new TxtManagement("liste_de_course");
		currInv.listeCourse(listeDeCourse);
		currInv.updateInv(inventaireTxt);
		
		//currInv.affInv();
		
		//TxtManagement listePlats = new TxtManagement("plats");
        //Menu cartePlats = new Menu(listePlats);
        //cartePlats.affUnPlat(13);
        
        //Table test = new Table(5);
        //test.commandePlatMenu100ans(13, cartePlats, currInv);

		//Front________________________________________________________________________________________
		
		launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ecran.fxml"));
			Parent root = loader.load();
			
			Controller control = loader.getController();
			
			
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