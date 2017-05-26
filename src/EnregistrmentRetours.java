import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



/**
 * Created by Tiphaine Dumur on 11/04/17.
 *
 * Cette classe va gerer l'enregistrement des retours aux questoinnaires
 *
 **/


public class EnregistrmentRetours {


    private static void ajout(String nomCsv, String ligneQuestions, String ligneReponse){
        //Cette méthode ajoute une ligne de réponse au ficher
        // TODO ajoute la ligne ligneReponse au fichier nonmCsv
    	
    	File monFichier = new File(nomCsv);
    	if(monFichier.exists()){ // si le fichier .csv existe déjà
    		
    		FileWriter fw = null;
            try {
                fw = new FileWriter(nomCsv,true);
                fw.write(ligneReponse + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	} 
    	else { //sinon il faut ajouter une ligne en plus contenant les questions
    		FileWriter fw = null;
            try {
                fw = new FileWriter(nomCsv,true);
                fw.write("id," + ligneQuestions + "\n" +ligneReponse + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
    }

    private static String saisieReponse(Formulaire questionnaire){
        //Cette fonction crée la ligne complète de toute les réponses d'un client au questionnaire

        Question[] questions = questionnaire.getTabQuestions(); //le tableau contient des var de type Questions

        Scanner sc = new Scanner(System.in);

        String retours;

        String ligneReponse = new String();//La ligne qui va contenir les retours

        int id = questionnaire.getId();

        for(Question question : questions){
            System.out.print(question+" ");
            String typeRetours = question.getTyperetour();

            //Ces ifs permettent de différentier les différent types de question
            if (typeRetours.equals("o")){
                retours = sc.nextLine();
            }
            else if(typeRetours.equals("f")){
                String[] repPossibles = question.getRepPossible();
                for(String reponsePossible : repPossibles){
                    System.out.print("\n"+reponsePossible);
                }
                do{
                    System.out.print("\nVeuillez entrer une des réponses possibles: ");
                    retours = sc.nextLine();
                } while(!(Auxiliaires.existe(retours, repPossibles)));

            }
            else{
                do{
                    System.out.print("\nVeuillez saisir un nombre: ");
                    retours = sc.nextLine();
                } while (!(Auxiliaires.estUnNombre(retours)));
            }

            ligneReponse = ligneReponse + retours + ",";
          
        }
        ligneReponse.substring(0,ligneReponse.length()-1);
        return(id + "," + ligneReponse);
    }
    
    public static String intituleQuestions(Formulaire questionnaire){
    	Question[] questions = questionnaire.getTabQuestions(); 
    	String chaineQuestions = new String();
    	for(Question q : questions){
    		chaineQuestions = chaineQuestions + q.getIntituleCourt() + ",";
    	}
        chaineQuestions.substring(0,chaineQuestions.length()-1);//Permet de retirer la dernière virgule
    	
    	return(chaineQuestions);
    }

    public static void principale(Formulaire questionnaire){
        //C'est cette fonction qui est appellée par le menu
    	String ligneQuestions = intituleQuestions(questionnaire);
        String ligneRetours = saisieReponse(questionnaire);
        String nomCsv = questionnaire.getNomFormulaire() + ".csv";

        ajout(nomCsv, ligneQuestions, ligneRetours);

        System.out.println("Merci pour votre contribution! ");
        questionnaire.setId(questionnaire.getId() + 1);
    }
}
