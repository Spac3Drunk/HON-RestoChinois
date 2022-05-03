package projet_Resto_CIR3;

public class MainAppRunner {

	public static void main(String[] args) {
		
		TxtManagement inventaireTxt = new TxtManagement("inventaire");
		inventaireTxt.updateLine("Tomate", 0);
		inventaireTxt.updateLine("150", 1);
		inventaireTxt.updateLine("150", 2);
		inventaireTxt.updateLine("Salade", 3);
		inventaireTxt.updateLine("90", 4);
		inventaireTxt.updateLine("75", 5);
		inventaireTxt.ecrireTexte();

		Inventaire currInv = new Inventaire(inventaireTxt);
		
		currInv.affInv();
		currInv.subInvItem("Tomate", 12);
		currInv.subInvItem("Salade", 63);
		currInv.affInv();
		TxtManagement listeDeCourse = new TxtManagement("liste_de_course");
		currInv.listeCourse(listeDeCourse);
		currInv.updateInv(inventaireTxt);
	}

}