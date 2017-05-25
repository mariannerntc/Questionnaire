import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tiphaine Dumur on 24/05/17.
 **/
public class Statistiques {
    public static void stats() {
        String nomFormulaireChoisi = Formulaire.afficherFormulaires();
        System.out.println("Le formulaire choisi est: " + nomFormulaireChoisi);
        Formulaire questionnaire = null;
        try {
            questionnaire = LectureQuestionnaires.lectureFormulaire(nomFormulaireChoisi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String newargs[] = {"--no-save"};
        Rengine r = new Rengine(newargs, false, null);//Interface entre R et Java

        File f = new File(nomFormulaireChoisi + ".csv");//On ouvre le fichier csv du questionnaire choisi
        //methode pour tester l'existence
        if (f.exists()) {
            System.out.println("Le fichier existe bien!");
            REXP x; //REXP transforme les objets R en objet java
            //x = r.eval("library(JavaGD)");
            //x = r.eval("JavaGD()");// Permettent de fermer le pop-up des stats
            //x = r.eval("data<-read.csv(file='/home/eisti/testt.csv', head=TRUE, sep=',')");
            x = r.eval("data <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=';', na.strings='/', dec='.', strip.white=TRUE)");
            int choix;
            List<String> variablesAEtudier;
            Scanner sc  = new Scanner(System.in);
            System.out.println("Veuillez choisir le type de statistiques que vous désirez obtenir");
            System.out.println("    1- Afficher les retours");
            System.out.println("    2- Statistiques Univariées");
            System.out.println("    3- Statistiques bivariées");
            System.out.println("    4- Statistiques multivariées");
            System.out.println("    5- Retour");
            choix = sc.nextInt();
            while(!((choix <6)&(choix>0))){
                Menu.verifieChoix(1,5,choix,sc);
            }

            switch (choix){
                case 1:
                    //Affiche le tableau des retours
                    LectureQuestionnaires.afficherCSV(nomFormulaireChoisi);
                    break;
                case 2:
                    //Affiche toutes les statistiques univariées
                    break;
                case 3:
                    //etudie les variables deux à deux
                    break;
                case 4:
                    variablesAEtudier = Auxiliaires.choixQuestion(questionnaire);
                    //effectue l'ACP
                    break;
                default:
                    //Revient au menu
                    Menu.menuAdmin();
                    break;
            }
            //L'utilisateur choisi les stats qu'il veux voir
                //Afficher les réponse
                    //Tu affiche le .csv
                //Univarié
                //Bivarié
                //Multivarié
            //x = r.eval("summary(data)");
            //x = r.eval("plot(data)");
            //x = r.eval("library(FactoMineR)");
            //x = r.eval("res.pca = PCA(data, scale.unit=TRUE, ncp=3, graph=T)");
            //System.out.println(x);
        } else {
            System.out.println("Personne n'a répondu au questionnaire!");
            Formulaire.afficherFormulaires();
        }//Retourne au menu des stats
    }
}
