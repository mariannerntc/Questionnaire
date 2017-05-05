import java.io.*;

/**
 * Created by Tiphaine Dumur on 24/03/17.
 * Cette classe à pour but de lire un fichier de questionnaire
 **/



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

    public static void affichageQuestionnaire(Formulaire questionnaire){

    }
}
