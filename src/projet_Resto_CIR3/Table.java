package projet_Resto_CIR3;

import java.util.ArrayList;

public class Table {
	
	//Varibles________________________________________________________________________________________
	
		public int nbPlaces;
		public ArrayList<Plats> listePlatsCommandes = new ArrayList<Plats>();
		
	//Constructeurs___________________________________________________________________________________
		
		public Table(int nbSeats) {
			this.nbPlaces = nbSeats;
		}
		
	//Methods_________________________________________________________________________________________
		
		public void commandePlat(int id, Menu cartePlats, Inventaire inv) {
			Plats platCommand� = cartePlats.listePlats.get(id);
			
			int sizePlat = platCommand�.ingredientsNecessaires.size();
			int ingQuantity = 1;
			int dispoError = 0;
			for(int i = 0;i<sizePlat;i++) {
				ingQuantity = 1;
				if(sizePlat > i+1) {
					if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+1))) {
						ingQuantity++;
						if(sizePlat > i+2) {
							if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+2))) {
								ingQuantity++;
								i = i + 2;
							}else {
								i++;
							}
						}
					}
				}
				
				if(inv.dispo(platCommand�.ingredientsNecessaires.get(i), ingQuantity)) {
					
				}else {
					dispoError = 1;
				}
			}
			if(dispoError == 0) {
				listePlatsCommandes.add(platCommand�);
				for(int i = 0;i<sizePlat;i++) {
					ingQuantity = 1;
					if(sizePlat > i+1) {
						if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+1))) {
							ingQuantity++;
							if(sizePlat > i+2) {
								if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+2))) {
									ingQuantity++;
									i = i + 2;
								}else {
									i++;
								}
							}
						}
					}
					inv.subInvItem(platCommand�.ingredientsNecessaires.get(i),ingQuantity);
					
				}
			}else {
				System.out.println("Plus assez d'ingredients!");
			}
			
		}
		
		
		public void commandePlatMenu100ans(int id, Menu cartePlats, Inventaire inv) {
			Plats platCommand� = cartePlats.listePlats.get(id);
			if(!platCommand�.nomDuPlat.contains("Menu_100_ans")) {
				platCommand�.prix = 0;
				int sizePlat = platCommand�.ingredientsNecessaires.size();
				int ingQuantity = 1;
				int dispoError = 0;
				for(int i = 0;i<sizePlat;i++) {
					ingQuantity = 1;
					if(sizePlat > i+1) {
						if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+1))) {
							ingQuantity++;
							if(sizePlat > i+2) {
								if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+2))) {
									ingQuantity++;
									i = i + 2;
								}else {
									i++;
								}
							}
						}
					}
					
					if(inv.dispo(platCommand�.ingredientsNecessaires.get(i), ingQuantity)) {
						
					}else {
						dispoError = 1;
					}
				}
				if(dispoError == 0) {
					listePlatsCommandes.add(platCommand�);
					for(int i = 0;i<sizePlat;i++) {
						ingQuantity = 1;
						if(sizePlat > i+1) {
							if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+1))) {
								ingQuantity++;
								if(sizePlat > i+2) {
									if(platCommand�.ingredientsNecessaires.get(i).contains(platCommand�.ingredientsNecessaires.get(i+2))) {
										ingQuantity++;
										i = i + 2;
									}else {
										i++;
									}
								}
							}
						}
						inv.subInvItem(platCommand�.ingredientsNecessaires.get(i),ingQuantity);
						
					}
				}else {
					System.out.println("Plus assez d'ingredients!");
				}
			}else {
				System.out.println("Vous ne pouvez pas commander un menu 100 ans dans un menu 100 ans!");
			}
			
			
		}

}
