/*
** Nom: Menu.java
** But: afficher un/des menu(s)
** Date: 15/02/17
*/


import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;


//main() - menu() - menuAdmin() - menuClient() - afficherFormulaires()
//suppr() - stats()
//verifieChoix (int, int , int, Scanner)
public class Menu {

    public static void main(String[] args){
        menu();
    }

    
    //Postcondition: affiche le menu principal permettant d'accéder à la partie admin ou client
    public static void menu(){ 
        //System.out.println("\033[H\033[2J"); //Si l'application est lancée sur un terminal 'clean' le terminal
        System.out.flush();

        System.out.println(" _______________________________________________________");
        System.out.println("|        ___________________________         __         |");
        System.out.println("|       |                           |>      <- )        |");
        System.out.println("|       |       BIENVENUE ~ !       |       /( \\        |");
        System.out.println("|       |___________________________|       \\_\\_>       |");
        System.out.println("|                                           \"  \"        |");
        System.out.println("| 1- Répondre à un formulaire                           |");
        System.out.println("| 2- Accéder à la partie administrateur                 |");
        System.out.println("|                                                       |");
        System.out.print("|    Que souhaitez vous faire? ");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt(); 
        verifieChoix(1, 2, choix, sc); //Vérifie si l'utilisateur a rentré 1 ou 2

        //Selon le choix
        if (choix==2){ menuAdmin(); } //affiche la partie admin
        else{ menuClient();} //affiche la partie client
        
        System.out.println("");
    }
    

    //Postcondition: affiche le menu de la partie administrateur et ses différents choix
    public static void menuAdmin() {
        System.out.println("|_______________________________________________________| ");  
        System.out.println("");
        System.out.println("                  _          _          _                 ");
        System.out.println("                >(')_____, <(')_____, >(')_____,          ");
        System.out.println("                  (` =~~/    (` =~~/    (` =~~/           ");
        System.out.println("              ^~^~^`---'~^~^~^`---'~^~^~^`---'~^~^~^      ");
        System.out.println("          ______________________________________________  ");
        System.out.println("         |                                              | ");
        System.out.println("         |             PARTIE ADMINISTRATEUR            | ");
        System.out.println("         |______________________________________________| ");
        System.out.println("");
        System.out.println("           1- Créer un nouveau formulaire              ");
        System.out.println("           2- Modifier un formulaire                   ");
        System.out.println("           3- Supprimer un formulaire                  ");
        System.out.println("           4- Obtenir des statistiques                    ");
        System.out.println("           5- Retour                                      ");
        System.out.println("           6- Quitter le programme                        ");
        System.out.println("");
        System.out.print("              Que souhaitez vous faire? ");
        
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();      
        choix = verifieChoix(1,6,choix,sc); //Vérifie que le choix de l'utilisateur correspond à un numéro du menu
        System.out.println("");
        
        switch (choix) { //Selon le choix...
            case 1: Formulaire.creerFormulaire(); //CREE un nouveau formulaire
            		menu(); //permet de boucler
                	break;
            case 2: System.out.println("Vous souhaitez modifier un questionnaire. Veuillez choisir le numéro correspondant. ");
            		String nomFormulaireChoisi = Formulaire.afficherFormulaires(); //Affiche tous les formulaires
                    if (nomFormulaireChoisi != null) {
                        Formulaire.modifierFormulaire(nomFormulaireChoisi); //Et en MODIFIE un
                    }
                    menu(); 
            		break;
            case 3: Formulaire.supprimerFormulaire(); //Affiche tous les formulaires pour en SUPPRIMER un
            		menu(); 
                    break;
            case 4: stats(); //Affiches les STATS
                    break;
            case 5: menu(); //Retourne au menu principal
                    break;
            case 6: System.out.println("Programme quitté. A bientôt! "); //Quitte le programme
                    break;
            default: System.out.println("Erreur de saisie dans la partie admin, le programme se quitte."); //On ne doit jamais arriver ici
                     break;
        }
        
        System.out.println("");
         //ferme le flux du scanner
    }



    public static void menuClient(){
        System.out.println("|_______________________________________________________| ");
        System.out.println("");
        System.out.println("                           .' '.            __            ");
        System.out.println("                  .        .   .           (__\\_         ");
        System.out.println("                   .         .         . -{{_(|8)         ");
        System.out.println("                     ' .  . ' ' .  . '     (__/           ");
        System.out.println("          ______________________________________________  ");
        System.out.println("         |                                              | ");
        System.out.println("         |                 PARTIE CLIENT                | ");
        System.out.println("         |______________________________________________| ");
        System.out.println("");
        System.out.println("Vous souhaitez répondre à un questionnaire. Veuillez choisir le numéro correspondant.  ");
        String nomFormulaireChoisi = Formulaire.afficherFormulaires();
        
        System.out.println("Le nom du formulaire choisi: "+nomFormulaireChoisi);
        
        if (!dateCorrecte(nomFormulaireChoisi)){
        	System.out.println("Vous ne pouvez pas répondre à ce formulaire. Soit la session n'a pas commencée soit elle est finie.");
        	menuClient();
        }
        else{
            try {
                Formulaire formulaire = LectureQuestionnaires.lectureFormulaire(nomFormulaireChoisi);
                EnregistrmentRetours.principale(formulaire);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        menu();
    }
    
    
    //Pre: nomFormulaire est une chaine de charactere correspondante à un formulaire existant
    //Post: renvoie true si la date du jour est entre la date de début et de fin de session du formulaire
    //      renvoie faux sinon
    public static boolean dateCorrecte(String nomFormulaire) {
    	Boolean res = false;
        try {
        	LocalDate auj = LocalDate.now(); //la date d'aujourd'hui
            Formulaire formulaire = LectureQuestionnaires.lectureFormulaire(nomFormulaire); //on récupère le formulaire
            LocalDate debut = formulaire.getDateDebut(); //on note sa date de début
            LocalDate fin = formulaire.getDateFin(); //et de fin
          

            /*System.out.println("Aujourd'hui nous sommes le: "+auj);
            System.out.println("La date debut du formulaire "+nomFormulaire+" est: "+debut);
            System.out.println("La date limite du formulaire "+nomFormulaire+" est: "+fin);*/
            

            res = (auj.isAfter(debut) && auj.isBefore(fin));
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    
    
    public static void stats(){

    	Statistiques.stats();


    	}

 
    	

    	

    	
      	
    	
    	//Faire des demandes ? un à un ou tout en meme temps?
    	//Demander s'il veut à nouveau d'autres stats? 
    	//s'il veut plus:
    	//menu();


    
    //Precondition: a, b et choix sont des entiers et sc un Scanner
    //Postcondition: vérifie si la réponse saisie (choix) est comprise entre a et b (compris) sinon redemande à l'utilisateur une nouvelle saisie
    //               renvoie un entier
    public static int verifieChoix(int a, int b, int choix, Scanner sc){ 
        while (choix<a || choix>b){
            System.out.print("               Veuillez saisir une valeur correcte (comprise entre "+a+" et "+b+" ): ");
            choix=sc.nextInt();
        }
        return choix;
    }

}

