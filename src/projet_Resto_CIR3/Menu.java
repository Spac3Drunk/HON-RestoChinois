package projet_Resto_CIR3;

import java.util.ArrayList;

public class Menu {
	
	//Varibles________________________________________________________________________________________
	
	public int nbPlats;
	public ArrayList<Plats> listePlats = new ArrayList<Plats>();
	
	//Constructeurs___________________________________________________________________________________
	
	public Menu(TxtManagement txtPlats) {
        int sizeTxt = txtPlats.texteEnLignes.size();
        int nbPlates = 0;
        int tmpId = 0;
        for(int i = 0;i < sizeTxt;i++) {
            if(txtPlats.texteEnLignes.get(i).contains("/")) {
                nbPlates++;
            }
            this.nbPlats = nbPlates+1;
        }
        
        for(int j = 0;j < nbPlates+1;j++) {
            String tmpName = txtPlats.texteEnLignes.get(tmpId);
            int tmpPrice = Integer.parseInt(txtPlats.texteEnLignes.get(tmpId + 1));
            ArrayList<String> tmpIngredients = new ArrayList<String>();
            if(!txtPlats.texteEnLignes.get(tmpId).contains("Menu_100_ans")) {
                tmpIngredients.add(txtPlats.texteEnLignes.get(tmpId + 2));
                if(tmpId+2 < sizeTxt - 1) {
                    if(txtPlats.texteEnLignes.get(tmpId + 3).contains("/")) {
                        tmpId = tmpId + 4;
                    }else {
                        tmpIngredients.add(txtPlats.texteEnLignes.get(tmpId + 3));
                        if(tmpId+3 < sizeTxt - 1) {
                            if(txtPlats.texteEnLignes.get(tmpId + 4).contains("/")) {
                                tmpId = tmpId + 5;
                            }else {
                                tmpIngredients.add(txtPlats.texteEnLignes.get(tmpId + 4));
                                if(tmpId+4 < sizeTxt - 1) {
                                    if(txtPlats.texteEnLignes.get(tmpId + 5).contains("/")) {
                                        tmpId = tmpId + 6;
                                    }else {
                                        if(tmpId+5 < sizeTxt - 1) {
                                            tmpIngredients.add(txtPlats.texteEnLignes.get(tmpId + 5));
                                            tmpId = tmpId + 7;
                                        }    
                                    }
                                }
                            }
                        }
                    }
                }    
            }
            this.listePlats.add(new Plats(tmpName, tmpPrice , tmpIngredients));
        }
        
    }
	
	//Methods_________________________________________________________________________________________
	
	public void affMenu() {
		for(int i = 0; i < this.nbPlats; i++) {
			System.out.print(i+". ");
			this.listePlats.get(i).affPlat();
			System.out.println("==================================================");
		}
	}
	
	public void affUnPlat(int id) {
		this.listePlats.get(id).affPlat();
		System.out.println("==================================================");
	}
	
	public void affNbPlat() {
		System.out.println("Nombre de plats : " + this.nbPlats);
		
	}
	
}
