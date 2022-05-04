package projet_Resto_CIR3;

import java.util.ArrayList;

public class Table {
	
	//Varibles________________________________________________________________________________________
	
		public int nbPlaces;
		public ArrayList<Plats> listePlatsCommandés = new ArrayList<Plats>();
		
	//Constructeurs___________________________________________________________________________________
		
		public Table(int nbSeats) {
			this.nbPlaces = nbSeats;
		}
		
	//Methods_________________________________________________________________________________________
		
		public void commandePlat(int id,Menu cartePlats) {
			Plats platCommandé = cartePlats.listePlats.get(id);
			System.out.println(platCommandé.ingredientsNecessaires.get(0));
		}

}
