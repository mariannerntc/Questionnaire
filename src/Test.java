import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Tiphaine Dumur on 25/04/17.
 **/
public class Test {
    public static void main(String args[]) throws FileNotFoundException {
        //Formulaire.creerFormulaire();
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom du formulaire créé");
        String nom = sc.nextLine();
        try {
            Formulaire questionnaire = LectureQuestionnaires.lectureFormulaire("TestRetours");
            System.out.println(questionnaire);
            Question[] listeQuestion = questionnaire.getTabQuestions();
            EnregistrmentRetours.principale(questionnaire);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Bla bla bla
    }
}
