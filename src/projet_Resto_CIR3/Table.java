package projet_Resto_CIR3;

import java.util.ArrayList;
import java.util.Map;

import javafx.util.Pair;

public class Table {
	
	//Varibles________________________________________________________________________________________
		
		public String nomTable;
		public ArrayList<Plats> listePlatsCommandes = new ArrayList<Plats>();
		
	//Constructeurs___________________________________________________________________________________
		
		public Table(String name) {
			this.nomTable = name;
		}
		
	//Methods_________________________________________________________________________________________
		
		public ArrayList<String> allPlatCommandeNamePrice() {
			ArrayList<String> res = new ArrayList<String>();
			for (int i=0; i<this.listePlatsCommandes.size(); i++) {
				String tmp = listePlatsCommandes.get(i).nomDuPlat;
				tmp = tmp.replace("_", " ");
				res.add(tmp + "\t : " + listePlatsCommandes.get(i).prix + "€");
			}
			return res;
		}
		
		public boolean commandePlat(int id, Menu cartePlats, Inventaire inv) {
			boolean res = true;
			Plats platCommande = new Plats(cartePlats.listePlats.get(id).nomDuPlat,
											cartePlats.listePlats.get(id).prix,
											cartePlats.listePlats.get(id).ingredientsNecessaires);
			
			int sizePlat = platCommande.ingredientsNecessaires.size();
			int ingQuantity = 1;
			int dispoError = 0;
			for(int i = 0;i<sizePlat;i++) {
				ingQuantity = 1;
				if(sizePlat > i+1) {
					if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+1))) {
						ingQuantity++;
						if(sizePlat > i+2) {
							if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+2))) {
								ingQuantity++;
								i = i + 2;
							}else {
								i++;
							}
						}
					}
				}
				
				if(inv.dispo(platCommande.ingredientsNecessaires.get(i), ingQuantity)) {
					
				}else {
					dispoError = 1;
				}
			}
			if(dispoError == 0) {
				listePlatsCommandes.add(platCommande);
				for(int i = 0;i<sizePlat;i++) {
					ingQuantity = 1;
					if(sizePlat > i+1) {
						if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+1))) {
							ingQuantity++;
							if(sizePlat > i+2) {
								if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+2))) {
									ingQuantity++;
									i = i + 2;
								}else {
									i++;
								}
							}
						}
					}
					inv.subInvItem(platCommande.ingredientsNecessaires.get(i),ingQuantity);
					
				}
			}else {
				res = false;
			}
			return res;
		}
		
		public boolean commandePlatMenu100ans(int id, Menu cartePlats, Inventaire inv) {
			boolean res = true;
			Plats platCommande = new Plats(cartePlats.listePlats.get(id).nomDuPlat,
											cartePlats.listePlats.get(id).prix,
											cartePlats.listePlats.get(id).ingredientsNecessaires);
			if(!platCommande.nomDuPlat.contains("Menu_100_ans")) {
				platCommande.prix = 0;
				int sizePlat = platCommande.ingredientsNecessaires.size();
				int ingQuantity = 1;
				int dispoError = 0;
				for(int i = 0;i<sizePlat;i++) {
					ingQuantity = 1;
					if(sizePlat > i+1) {
						if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+1))) {
							ingQuantity++;
							if(sizePlat > i+2) {
								if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+2))) {
									ingQuantity++;
									i = i + 2;
								}else {
									i++;
								}
							}
						}
					}
					
					if(inv.dispo(platCommande.ingredientsNecessaires.get(i), ingQuantity)) {
						
					}else {
						dispoError = 1;
					}
				}
				if(dispoError == 0) {
					listePlatsCommandes.add(platCommande);
					for(int i = 0;i<sizePlat;i++) {
						ingQuantity = 1;
						if(sizePlat > i+1) {
							if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+1))) {
								ingQuantity++;
								if(sizePlat > i+2) {
									if(platCommande.ingredientsNecessaires.get(i).contains(platCommande.ingredientsNecessaires.get(i+2))) {
										ingQuantity++;
										i = i + 2;
									}else {
										i++;
									}
								}
							}
						}
						inv.subInvItem(platCommande.ingredientsNecessaires.get(i),ingQuantity);
						
					}
				}else {
					res = false;
				}
			}else {
				//System.out.println("Vous ne pouvez pas commander un menu 100 ans dans un menu 100 ans!");
			}
			return res;
		}
		
		public void makeFacture() {
			String NewPath = "./src/Txts/Factures/";
			TxtManagement config = new TxtManagement("config",NewPath);
			int nbFacture = Integer.parseInt(config.texteEnLignes.get(1));
			TxtManagement listeTxt = new TxtManagement("Facture_"+ nbFacture, NewPath);
			
			listeTxt.updateLine("Facture n° : \t \t"+ nbFacture +" \n" , 0);
			nbFacture ++;
			config.updateLine(Integer.toString(nbFacture),1);
			config.ecrireTexte();
			int sizeComm = this.listePlatsCommandes.size();
			int totalCost = 0;
			String tmp;
			for(int i = 0; i < sizeComm; i++) {
				totalCost += this.listePlatsCommandes.get(i).prix;
				tmp = this.listePlatsCommandes.get(i).nomDuPlat;
				tmp = tmp.replace("_", " ");
				if(tmp.length() > 13) {
					tmp = tmp + "\t" + Integer.toString(this.listePlatsCommandes.get(i).prix) + "€";
					listeTxt.updateLine(tmp, i+1);
				}else {
					tmp = tmp + "\t\t" + Integer.toString(this.listePlatsCommandes.get(i).prix) + "€";
					listeTxt.updateLine(tmp, i+1);
				}
				
			}
			tmp = "Total : \t\t" + totalCost + "€.";
			listeTxt.updateLine(tmp, sizeComm+1);
			String stringToSplit = this.nomTable;
			String nombre = stringToSplit.substring(stringToSplit.indexOf('_') + 1,stringToSplit.length());

			tmp = "Table n€ : \t\t" + nombre;
			listeTxt.updateLine(tmp, sizeComm+2);
			listeTxt.ecrireTexte();
		}

}
