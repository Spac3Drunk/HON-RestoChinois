package projet_Resto_CIR3;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class Inventaire{
	
	//Varibles________________________________________________________________________________________
	
	public HashMap<String, Pair<Integer, Integer>> inv = new HashMap<String, Pair<Integer, Integer>>();
	
	//Constructeurs___________________________________________________________________________________
	
	public Inventaire(TxtManagement invTxt) {
		try {
			for (int i=0; i<invTxt.texteEnLignes.size(); i=i+3) {//TODO verif doublon dans les noms /!\ mais pas les string nombres
				inv.put(invTxt.texteEnLignes.get(i), new Pair<Integer, Integer>(
						Integer.parseInt(invTxt.texteEnLignes.get(i+1)),
						Integer.parseInt(invTxt.texteEnLignes.get(i+2))));
			}
		}catch(Exception e) {
			System.out.println("Erreur dans le format de " + invTxt.filePath + invTxt.nomDuFichier + " : Error " + e);
		}
	}
	
	//Methods_________________________________________________________________________________________
	
	public void affInv() {
		for(Map.Entry<String, Pair<Integer, Integer>> ent : inv.entrySet()) {
			System.out.println(ent.getKey() + "\t : \t" + ent.getValue().getValue()+ "\t /"+ ent.getValue().getKey());
		}
		System.out.println();
	}
	
	public void subInvItem(String name, int amounts) {
		if(inv.containsKey(name)) {
			int tmp = inv.get(name).getValue()-amounts;
			if(tmp<0) {tmp = 0;}
			Pair<Integer, Integer> tmpPair = new Pair<Integer, Integer>(inv.get(name).getKey() , tmp);
			inv.replace(name, tmpPair);
		}else {
			System.out.println("Vous etes une merde, name n'existe pas");
		}
	}
	
	public void listeCourse(TxtManagement listeTxt) {
		listeTxt.clearBuffer();
		listeTxt.updateLine("Ingredients manquants : \n" , 0);
		int count = 1;
		String tmp;
		for(Map.Entry<String, Pair<Integer, Integer>> ent : inv.entrySet()) {
			tmp = Integer.toString(ent.getValue().getKey() - ent.getValue().getValue());
			listeTxt.updateLine(ent.getKey() + "\t : \t" + tmp, count);
			count ++;
		}
		listeTxt.ecrireTexte();
	}
	
	public void updateInv(TxtManagement invTxt) {
		invTxt.clearBuffer();
		int count = 0;
		for(Map.Entry<String, Pair<Integer, Integer>> ent : inv.entrySet()) {
			invTxt.updateLine(ent.getKey(), count);
			count ++;
			invTxt.updateLine(Integer.toString(ent.getValue().getKey()), count);
			count ++;
			invTxt.updateLine(Integer.toString(ent.getValue().getValue()), count);
			count ++;
		}
		invTxt.ecrireTexte();
	}
}
