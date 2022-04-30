package projet_Resto_CIR3;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Inventaire{
	
	//Varibles________________________________________________________________________________________
	
	public HashMap<String, Integer> inv = new HashMap<String, Integer>();
	public HashMap<String, Integer> defInv = new HashMap<String, Integer>();//TODO change to pairs in inv
	
	//Constructeurs___________________________________________________________________________________
	
	public Inventaire(ArrayList<String> keys, ArrayList<Integer> amounts, ArrayList<Integer> defaultAmounts) {
		if (keys.size() == amounts.size() && keys.size() == defaultAmounts.size()) {
			for (int i=0; i<keys.size(); i++) {
				inv.put(keys.get(i), amounts.get(i));
				defInv.put(keys.get(i), defaultAmounts.get(i));
			}
		}else {
			System.out.println("Vous vous etes chies dessus, keys.size != amounts.size != defaultAmounts.size");
		}
	}
	
	//Methods_________________________________________________________________________________________
	
	public void affInv() {//TODO aff defVals when got pairs
		for(Map.Entry<String, Integer> ent : inv.entrySet()) {
			System.out.println(ent.getKey() + "\t : \t" + ent.getValue());
		}
		System.out.println();
	}
	
	public void subInvItem(String key, int amounts) {
		if(inv.containsKey(key)) {
			int tmp = inv.get(key)-amounts;
			if(tmp<0) {tmp = 0;}
			inv.replace(key, tmp);
		}else {
			System.out.println("Vous etes une merde, key n'existe pas");
		}
	}
	
	
	
}
