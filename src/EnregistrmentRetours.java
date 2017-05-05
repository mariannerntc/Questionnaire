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


    private static void ajout(String nomCsv, String ligneReponse){
        //Cette méthode ajoute une ligne de réponse au ficher
        // TODO ajoute la ligne ligneReponse au fichier nonmCsv
        FileWriter fw = null;
        try {
            fw = new FileWriter(nomCsv,true);
            fw.write(ligneReponse + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

    private static String saisieReponse(Formulaire questionnaire){
        //Cette fonction crée la ligne complète de toute les réponses d'un client au questionnaire

        Question[] questions = questionnaire.getTabQuestions();

        Scanner sc = new Scanner(System.in);

        String retours;

        String ligneReponse = new String();

        for(Question question : questions){
            System.out.println(question);
            String typeRetours = question.getTyperetour();

            //Ces ifs permettent de différentier les différent types de question
            if (typeRetours.equals("o")){
                retours = sc.nextLine();
            }
            else if(typeRetours.equals("f")){
                String[] repPossibles = question.getRepPossible();
                for(String reponsePossible : repPossibles){
                    System.out.println(reponsePossible);
                }
                do{
                    System.out.println("Veuillez entrer une des réponses possibles");
                    retours = sc.nextLine();
                } while(!(Auxiliaires.existe(retours, repPossibles)));

            }
            else{
                do{
                    System.out.print("Veuillez saisir un nombre");
                    retours = sc.nextLine();
                } while (!(Auxiliaires.estUnNombre(retours)));
            }


            ligneReponse = ligneReponse + retours + ";";

        }
        return(ligneReponse);
    }

    public static void principale(Formulaire questionnaire){
        //C'est cette fonction qui est appellée par le menu
        String ligneRetours = saisieReponse(questionnaire);
        String nomCsv;
        nomCsv = questionnaire.getNomFormulaire() + ".csv";

        ajout(nomCsv, ligneRetours);

        System.out.println("Merci pour votre contribution");
    }
}
