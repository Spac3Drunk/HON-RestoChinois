package projet_Resto_CIR3;
import java.util.ArrayList;
import javafx.util.Pair;

public class MainAppRunner {

	public static void main(String[] args) {
		ArrayList<String> ingredients = new ArrayList<String>(); //TODO verif doublons (note pas possible d'utiliser hashset ou hashtree)
		ArrayList<Pair<Integer, Integer>> quantite = new ArrayList<Pair<Integer, Integer>>();
		
		ingredients.add("tomate");
		quantite.add(new Pair<Integer, Integer>(150, 150));
		
		ingredients.add("salade");
		quantite.add(new Pair<Integer, Integer>(80, 62));
		
		Inventaire currInv = new Inventaire(ingredients, quantite);
		//quantite.add(1);
		//Inventaire testInv = new Inventaire(ingredients, quantite, quantite);
		
		currInv.affInv();
		currInv.subInvItem("tomate", 12);
		currInv.subInvItem("salade", 70);
		currInv.affInv();
		TxtManagement listeDeCourse = new TxtManagement("liste_de_course");
		currInv.listeCourse(listeDeCourse);
		
		
		TxtManagement testFile = new TxtManagement("test");
		testFile.affTxt();
		testFile.updateLine("dodo", 15);
		testFile.affBuffer();
		testFile.ecrireTexte();
	}

}
