import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class LectureQuestionnaires implements Serializable{
    private static ObjectInputStream ois;
    private static final long serialVersionUID = 6529685098267757690L;

    
	public static Formulaire lectureFormulaire(String nom) throws FileNotFoundException {
        try {
            ois = new ObjectInputStream(
                    new FileInputStream(
                            new File(nom + ".ser")));
            try {
                Formulaire questionnaire = (Formulaire)ois.readObject();
                return questionnaire;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    
    public static JList listeFormulaires(){
          
        //listeDesFormulaires est une liste contenant tous les formulaires déjà créés et inscrits dans le fichier ListeFormulaire.txt
        ArrayList listeDesFormulaires = new ArrayList();

        //Lecture du fichier ListeFormulaires.txt
        try{ 
            InputStream ips = new FileInputStream("ListeFormulaires.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr); 
            String ligne;
            while ((ligne=br.readLine())!=null){ //On lit les lignes une par une
                listeDesFormulaires.add(ligne); //On rajoute chaque ligne du fichier comme nouvel élément de la liste
            }
            br.close();
        }catch (Exception e){System.out.println(e.toString());}
        //lectureListe(listeDesFormulaires);

        JList listf = new JList(listeDesFormulaires.toArray());
        return (listf); //null si la clé 'choix' n'existe pas
    }
    
    public static void lectureListe(ArrayList l){
    	for(int i = 0; i < l.size(); i++)
        {
    		System.out.println("\t" + i + " = " + l.get(i));
        }
    }
}
