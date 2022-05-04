package projet_Resto_CIR3;

import java.util.ArrayList;

public class Plats {
	
	//Varibles________________________________________________________________________________________
	
	public String nomDuPlat;
	public int prix;
	public ArrayList<String> ingredientsNecessaires = new ArrayList<String>();
	
	//Constructeurs___________________________________________________________________________________
	
	public Plats(String plat, int price, ArrayList<String> ingredients) {
		this.nomDuPlat = plat;
		this.prix = price;
		this.ingredientsNecessaires = ingredients;
	}
	
	//Methods_________________________________________________________________________________________
	
	public void affPlat() {
		System.out.println(this.nomDuPlat);
		System.out.println("Prix : " + this.prix + " euros.");
		if(!this.nomDuPlat.contains("Menu_100_ans")) {
			System.out.println("Ingredients :");
			for(int i = 0;i < this.ingredientsNecessaires.size();i++) {
				System.out.println(this.ingredientsNecessaires.get(i));
			}
		}
		
	}
	
	public void affPlatFacture() {
		System.out.println(this.nomDuPlat);
		System.out.println("Prix : " + this.prix + " euros.");
	}
	
}
