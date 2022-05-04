package projet_Resto_CIR3;

import java.util.ArrayList;

public class Salle {

	//Varibles________________________________________________________________________________________
	
	public ArrayList<Table> listeTable = new ArrayList<Table>();
	
	//Constructeurs___________________________________________________________________________________
	
	public Salle(int nbTable) {
		for(int i = 1; i<= nbTable;i++) {
				String tmpName = "Table_" + i;
				this.listeTable.add(new Table(tmpName));
			}
		}
			
			
	//Methods_________________________________________________________________________________________
	
	public void getTableName(int id) {
		System.out.println(this.listeTable.get(id-1).nomTable);
	}

}
