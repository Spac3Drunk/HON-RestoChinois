package projet_Resto_CIR3;

import java.io.*;
import java.nio.file.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TxtManagement {
	
	//Varibles________________________________________________________________________________________
	public String nomDuFichier;
	public String filePath = "C:/Java/texteProjet/";
	public String texteAInscrire;
	
	
	//Constructeurs___________________________________________________________________________________
	
	public TxtManagement(String fileName, String path) {
		this.nomDuFichier = fileName;
		this.filePath = path;
	}
	
	public TxtManagement(String fileName) {
		this.nomDuFichier = fileName;
	}
	
	
	
	
	//Methods_________________________________________________________________________________________
	public void ecrireTexte() {
		byte[] data = this.texteAInscrire.getBytes();
		 Path chemin = Paths.get(this.filePath+this.nomDuFichier+".txt");
	        OutputStream output = null;
	        try {
	            // Un objet BufferedOutputStream est affecté à la référence OutputStream.
	            output = new BufferedOutputStream(Files.newOutputStream(chemin));
	            // Ecrire dans le fichier
	            output.write(data);
	 
	            // vider le tampon
	            output.flush();
	 
	            // fermer le fichier
	            output.close();
	        } catch (Exception e) {
	            System.out.println("Message " + e);
	        }
	}
	
	public void updateText(String textToWrite) {
		this.texteAInscrire = textToWrite;
	}
	
	public void updatePath(String Path) {
		this.filePath = Path;
	}
	
	public void updateName(String fileName) {
		this.nomDuFichier = fileName;
	}
	
	public void lireTexte() {
		InputStream input = null;
		Path chemin = Paths.get(this.filePath+this.nomDuFichier+".txt");
		System.out.println("Contenu de " + this.nomDuFichier + ".txt : ");
        try {
            input = Files.newInputStream(chemin);
             
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = null;
            s = reader.readLine();
            System.out.println(s);
            input.close();
 
        } catch (IOException e) {
            System.out.println("Message " + e);
        }
	}
	
}