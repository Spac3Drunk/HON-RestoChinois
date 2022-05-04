package projet_Resto_CIR3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppRunner extends Application {

	public static void main(String[] args) {
		
		//Front________________________________________________________________________________________
		
		launch(args);
		
		
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
		
		TxtManagement StaffTxt = new TxtManagement("StaffList");
		Staff currStaff = new Staff(StaffTxt);
		currStaff.startShift("XiaoLong");
		
		currStaff.startShift("XueWun");
		
		currStaff.startShift("LiDao");
		currStaff.startShift("LiBing");
		
		currStaff.startShift("MaoZheDong");
		currStaff.startShift("DenXiaoMing");
		currStaff.startShift("XiJingPing");
		currStaff.startShift("LiuShaoqi");
		currStaff.endService(StaffTxt);
		
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ecran.fxml"));
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