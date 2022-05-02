package projet_Resto_CIR3;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.io.IOException;

public class TxtManagement {
	
	//Varibles________________________________________________________________________________________
	
	public String nomDuFichier;
	public String filePath = "./src/Txts/";
	public ArrayList<String> texteEnLignes = new ArrayList<String>();
	public String texte;
	
	//Constructeurs___________________________________________________________________________________
	
	public TxtManagement(String fileName, String path) {
		this.nomDuFichier = fileName;
		this.filePath = path;
		this.lireTexte();
	}
	
	public TxtManagement(String fileName) {
		this.nomDuFichier = fileName;
		this.lireTexte();
	}
	
	//Methods_________________________________________________________________________________________
	
	public void updatePath(String Path) {
		this.filePath = Path;
	}
	
	public void updateName(String fileName) {
		this.nomDuFichier = fileName;
	}
	
	private void updateText() { // sync texteEnLignes -> texte
		if(!this.texteEnLignes.isEmpty()) {
			this.texte = this.texteEnLignes.get(0) + "\n";
			for (int i=1; i<this.texteEnLignes.size()-1; i++) {
				this.texte = this.texte + this.texteEnLignes.get(i) + "\n";
			}
			this.texte = this.texte + this.texteEnLignes.get(this.texteEnLignes.size()-1);
		}
	}
	
	public void updateLine(String newTexte, int line) {
		if(this.texteEnLignes.size() <= line) {
			while(this.texteEnLignes.size() <= line) {
				this.texteEnLignes.add("");
			}
		}
		this.texteEnLignes.set(line, newTexte);
		this.updateText();
	}
	
	public void ecrireTexte() { //save data to the txt
		byte[] data = this.texte.getBytes();
		Path chemin = Paths.get(this.filePath+this.nomDuFichier+".txt");
		try {
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(chemin));
			output.write(data);
			output.flush();
			output.close();
		}catch(Exception e){
			System.out.println("Erreur dans ecrireTexte : Error " + e);
		}
	}
	
	private void lireTexte() { //load the txt data
		Path chemin = Paths.get(this.filePath+this.nomDuFichier+".txt");
		File myFile = new File(this.filePath+this.nomDuFichier+".txt");
        try {
        	if (!myFile.exists()) {myFile.createNewFile();}
        	InputStream input = Files.newInputStream(chemin);
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        	this.texteEnLignes.clear();
            String s = null;
            while( (s = reader.readLine()) != null ) {
            	this.texteEnLignes.add(s);
            	}
            this.updateText();
            input.close();
        } catch (IOException e) {
    		System.out.println("Impossible de lire le txt : Error " + e);
        }
	}
	
	public void affBuffer() { //show current string data
		this.updateText();
		System.out.println("Contenu du buffer de " + this + " : ");
		for (int i=0; i<this.texteEnLignes.size(); i++) {
			System.out.println(this.texteEnLignes.get(i));
		}
	}
	
	public void affTxt() { //show inside of the txt
		Path chemin = Paths.get(this.filePath+this.nomDuFichier+".txt");
        try {
        	InputStream input = Files.newInputStream(chemin);
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        	
            System.out.println("Contenu de " + this.nomDuFichier + ".txt : ");
            String s = null;
            while( (s = reader.readLine()) != null ) {System.out.println(s);}
            input.close();
            
        } catch (IOException e) {
        	System.out.println("Impossible d'afficher le txt : Error " + e);
        }
	}
	
}