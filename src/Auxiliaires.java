import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tiphaine Dumur on 26/04/17.
 * Cette classe va contenir toutes les fonctions auxiliaires de verification
 **/


public class Auxiliaires {
    public static boolean estUnNombre(String chaine) {
        try {
            Float.parseFloat(chaine);
            return true;
        } catch (NumberFormatException e){
            return false;
        }

    }

    public static boolean existe(String retour, String[] repsPossibles){
        //Fonction verifiant l'existance d'une reponse dans l'ensemble des questions possibles
        boolean test = false;
        for (String repPossible : repsPossibles){
            if ((repPossible.equals(retour))){
                test = true;
            }
        }
        return test;
    }

    public List<String> choixQuestion(Formulaire questionnaire){
        List<String> qChoisies = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String choix;
        for (Question question : questionnaire.getTabQuestions()){
            System.out.println("Souhaitez vous inclure la question ' " + question.getIntitule() + " '(o ou n");
            choix = sc.nextLine();
            if(choix.equals("o")){
                qChoisies.add(question.getIntituleCourt());
            }
        }

        return(qChoisies);

    }
}
