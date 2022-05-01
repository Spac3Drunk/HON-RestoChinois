package projet_Resto_CIR3;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class Staff {
	
	//Varibles________________________________________________________________________________________
	
	public HashMap<String, Pair<String, Integer>> staffList = new HashMap<String, Pair<String, Integer>>();
	public int nbCuistot = 0;
	public int nbServeur = 0;
	public int nbManager = 0;
	public int nbBarman = 0;
	
	//Constructeurs___________________________________________________________________________________
	
		public Staff(ArrayList<String> keys, ArrayList<Pair<String, Integer>> role_And_Daystreak) {
			if (keys.size() == role_And_Daystreak.size()) {
				for (int i=0; i<keys.size(); i++) {
					staffList.put(keys.get(i), role_And_Daystreak.get(i));
				}
			}else {
				System.out.println("Vous vous etes chinegrer dessus, keys.size != amounts.size != defaultAmounts.size");
			}
		}
		
		//Methods_________________________________________________________________________________________
		
		public void startSession(String name) {
			if(staffList.containsKey(name)) {
				String staffRole = staffList.get(name).getKey();
				if(staffList.get(name).getValue() < 3) {
					if(staffRole == "Cuisinier") {
						this.nbCuistot++;
					}else if(staffRole == "Serveur") {
						this.nbServeur++;
					}else if(staffRole == "Manageur") {
						this.nbManager++;
					}else if(staffRole == "Barman") {
						this.nbBarman++;
					}
					System.out.println(name + " peut travailler et a travaill� " + staffList.get(name).getValue() + " Jours d'affil� en tant que " + staffRole + " !");
					int tmp = staffList.get(name).getValue() + 1;
					Pair<String, Integer> tmpPair = new Pair<String, Integer>(staffList.get(name).getKey() , tmp);
					staffList.replace(name, tmpPair);
					
				}else if(staffList.get(name).getValue() >= 3) {
					
					System.out.println(name + " ne peut pas travailler, il a d�j� travaill� " + staffList.get(name).getValue() + " Jours d'affil� en tant que " + staffRole + " !");
					int tmp = 0;
					Pair<String, Integer> tmpPair = new Pair<String, Integer>(staffList.get(name).getKey() , tmp);
					staffList.replace(name, tmpPair);
					
				}
			}else {
				System.out.println("Vous etes un connard, name n'existe pas");
			}
		}
		
		public void newDay() {
			this.nbCuistot = 0;
			this.nbServeur = 0;
			this.nbManager = 0;
			this.nbBarman = 0;
		}
		
			
}
