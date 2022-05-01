package projet_Resto_CIR3;

import java.io.*;
import java.nio.file.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TxtManagement {

	String nom;
	String texte;
	int choix = 0;

	Scanner sc = new Scanner (System.in);
	System.out.println("Choix : ");
	System.out.println("Ecriture (1)");
	System.out.println("Lecture (2)");
	choix = sc.nextInt();
	String skip = sc.nextLine();

	System.out.println("Ecrire le nom du fichier");
	nom = sc.nextLine();
	
	if(choix == 1) {
		System.out.println("Saisir le texte");
		texte = sc.nextLine();
		 byte[] data = texte.getBytes();
		 Path chemin = Paths.get("C:/Users/IFlee/eclipse-workspace/TestEcriture/src/testPack/"+nom+".txt");
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
	            System.out.println("Vous avez écrit : ' " + texte + " ' dans le fichier : " + nom + ".txt");
	        } catch (Exception e) {
	            System.out.println("Message " + e);
	        }
	}else if(choix == 2) {
		InputStream input = null;
		Path chemin = Paths.get("C:/Users/IFlee/eclipse-workspace/TestEcriture/src/testPack/"+nom+".txt");
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