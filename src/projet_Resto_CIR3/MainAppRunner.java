package projet_Resto_CIR3;
import java.util.ArrayList;

public class MainAppRunner {

	public static void main(String[] args) {
		ArrayList<String> ingredients = new ArrayList<String>(); //TODO verif doublons (note pas possible d'utiliser hashset ou hashtree)
		ArrayList<Integer> quantite = new ArrayList<Integer>();
		
		ingredients.add("tomate");
		quantite.add(150);
		
		ingredients.add("salade");
		quantite.add(62);
		
		Inventaire currInv = new Inventaire(ingredients, quantite, quantite);
		//quantite.add(1);
		//Inventaire testInv = new Inventaire(ingredients, quantite, quantite);
		
		currInv.affInv();
		currInv.subInvItem("tomate", 12);
		currInv.subInvItem("salade", 70);
		currInv.subInvItem("singe", 70);
		currInv.affInv();
		
	}

}
