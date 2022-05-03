package projet_Resto_CIR3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javafx.util.Pair;

public class Staff {
	
	//Varibles________________________________________________________________________________________
	
	public HashMap<String, Pair<String, Integer>> staffList = new HashMap<String, Pair<String, Integer>>();
	public ArrayList<String> workingStaff = new ArrayList<String>();
	public int nbCuistot = 0;
	public int nbServeur = 0;
	public int nbManager = 0;
	public int nbBarman = 0;
	
	//Constructeurs___________________________________________________________________________________
	
		public Staff(TxtManagement staffTxt) {
			try {
				for (int i=0; i<staffTxt.texteEnLignes.size(); i=i+4) {//TODO verif doublon dans les noms /!\ mais pas les string nombres
					String yesterday = (LocalDate.now().minusDays(1)).toString();
					if(yesterday.equals(staffTxt.texteEnLignes.get(i+3))) {
						this.staffList.put(staffTxt.texteEnLignes.get(i), new Pair<String, Integer>(
								staffTxt.texteEnLignes.get(i+1),
								Integer.parseInt(staffTxt.texteEnLignes.get(i+2))));
					}else {
						this.staffList.put(staffTxt.texteEnLignes.get(i), new Pair<String, Integer>(
								staffTxt.texteEnLignes.get(i+1), 0));
					}
				}
			}catch(Exception e) {
				System.out.println("Erreur dans le format de " + staffTxt.filePath + staffTxt.nomDuFichier + " : Error " + e);
			}
		}
		
		//Methods_________________________________________________________________________________________
		
		public void startShift(String name) {
			if(this.staffList.containsKey(name)) {
				String staffRole = this.staffList.get(name).getKey();
				if(staffRole.equals("Manageur")) {
					this.nbManager++;
					workingStaff.add(name);
				}else if(this.staffList.get(name).getValue() < 3) { // verif jours
					if(staffRole.equals("Cuistot")) {
						this.nbCuistot++;
					}else if(staffRole.equals("Serveur")) {
						this.nbServeur++;
					}else if(staffRole.equals("Barman")) {
						this.nbBarman++;
					}
					workingStaff.add(name);
				}else if(this.staffList.get(name).getValue() >= 3) {
					System.out.println(name + " ne peut pas travailler, il a déjà travaillé " + this.staffList.get(name).getValue() + " Jours d'affilé en tant que " + staffRole + " !");
				}
			}else {
				System.out.println("Vous etes un connard, name n'existe pas");
			}
		}
		
		public boolean startService(){
			boolean tmp = false;
			if(nbManager >= 1 && nbBarman >= 1 && nbServeur >= 2 && nbCuistot >= 4) {
				tmp = true;
			}
			return tmp;
		}
		
		public void endService(TxtManagement staffTxt) {//TODO make for everyone
			for(int i=0; i<this.workingStaff.size(); i++) {
				Pair<String, Integer> tmpPair = new Pair<String, Integer>(this.staffList.get(this.workingStaff.get(i)).getKey() , this.staffList.get(this.workingStaff.get(i)).getValue()+1);
				this.staffList.replace(this.workingStaff.get(i), tmpPair);
			}
			int count = 0;
			for(Map.Entry<String, Pair<String, Integer>> ent : this.staffList.entrySet()) {
				staffTxt.updateLine(ent.getKey(), count);
				count ++;
				staffTxt.updateLine(ent.getValue().getKey(), count);
				count ++;
				staffTxt.updateLine(Integer.toString(ent.getValue().getValue()), count);
				count ++;
				if(this.workingStaff.contains(ent.getKey())) {
					staffTxt.updateLine((LocalDate.now()).toString(), count);
				}
				count++;
			}
			staffTxt.ecrireTexte();
		}
		
		public void showWorkingstaff() {
			for(int i=0; i<this.workingStaff.size(); i++) {
				System.out.println(this.workingStaff.get(i) + "\n"
							+ this.staffList.get(this.workingStaff.get(i)).getKey() + " a travaillé "
							+ this.staffList.get(this.workingStaff.get(i)).getValue() + " jours d'affilés.");
			}
			System.out.println();
		}
		
		public void showAllStaff() {
			for(Map.Entry<String, Pair<String, Integer>> ent : this.staffList.entrySet()) {
				System.out.println(ent.getKey() + "\n" + ent.getValue().getKey()+ " a travaillé "+ ent.getValue().getValue() + " jours d'affilés.");
			}
			System.out.println();
		}
		
}
