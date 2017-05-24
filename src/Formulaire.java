
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Attributs     (nomFormulaire, nbQuestions, dateDebut, dateFin, tabQuestions)
//Constructeurs (1 seul avec en paramètres les 5 attributs)
//getters       (disponibles pour chaques attributs)
//Méthodes      (creerFormulaire, modifierFormulaire, supprimerFormulaire)
public class Formulaire implements Serializable {
	//Attributs
    private String nomFormulaire;
    private int nbQuestions;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Question[] tabQuestions; //un tableau contenant l'ensemble des Questions du Formulaire
    private static final long serialVersionUID = 6529685098267757690L;

    //Constructeur
    public Formulaire(String nomFormulaire, int nbQuestions, LocalDate dateDebut, LocalDate dateFin,  Question[] tabQuestions) {
        this.nomFormulaire = nomFormulaire; 
        this.nbQuestions = nbQuestions;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tabQuestions = tabQuestions;
    }

    public void setQuestions(Question[] questions){
        this.tabQuestions = questions;

    }
    
    //Getters
    public int getNbQuestions() {return (this.nbQuestions);}
    public LocalDate getDateDebut() {return (this.dateDebut);}
    public LocalDate getDateFin() {return (this.dateFin);}
    public String getNomFormulaire() {return  (this.nomFormulaire);}
    public Question[] getTabQuestions() {return this.tabQuestions;}
    
    //Postcondition: crée un nouvel objet de type Formulaire suite à quelques questions et l'enregistre
    public static void creerFormulaire() { 
    	System.out.println("              -----------------------------------------------------------");
        System.out.print("              Quel est le nom de votre Formulaire? ");
        Scanner sc = new Scanner(System.in); 
        String nomQ = sc.nextLine();
        System.out.print("              Combien de question contient votre Formulaire? ");
        int nbQuestions = sc.nextInt(); 
        System.out.print("              Quelle est sa date de début? (sous forme jj/mm/aaaa) ");
        sc.nextLine();//On vide la ligne avant d'en lire une autre sinon il ne demande pas la saisie
        LocalDate dateDebut = verifieConvertisDate(sc.nextLine(), sc);
        System.out.print("              Quelle est sa date de fin? (sous forme jj/mm/aaaa) ");
        LocalDate dateFin = verifieConvertisDate(sc.nextLine(), sc);
        while(dateFin.isBefore(dateDebut)){
        	System.out.println("Veuillez indiquer une date postérieur à la date de début.");
        	dateFin = verifieConvertisDate(sc.nextLine(), sc);
        }


        Question[] tabQuestions = new Question[nbQuestions]; //tabQuestions est un tableau correspondant à l'ensemble des Questions

        for (int i=0; i<nbQuestions; i++) { //boucle permettant de définir toutes les questions du Formulaire
            int numeroQuestion = i + 1;
            System.out.println("");
            System.out.println("              ---Question "+(numeroQuestion)+" sur "+nbQuestions+": ");
            System.out.print("                  Quel est l'intitulé de la question? ");
            String intitule = sc.nextLine();
            System.out.println("");
            System.out.print("                  Résumez cette question en un mot (pour l'associer dans les statistiques): ");
            String intituleCourt = sc.nextLine(); //pour le fichier .csv
            System.out.println("");
            System.out.print("                  Quel est le type de retour attendu? (o pour ouverte, f pour fermée et n pour numérique) ");
            String typeretour = sc.nextLine(); //Marquer juste o, n ou f

            while(!typeretour.equals("o") && !typeretour.equals("f") && !typeretour.equals("n")){ //tant que la réponse n'est pas o, f ou n
                System.out.print("                 Veuillez rentrer o, f ou n: ");
                typeretour = sc.nextLine();
                System.out.println("");
            }
            if(typeretour.equals("f")){ //si c'est une question fermée il y a plusieurs réponses prédéfinies -> Pour le moment ça fonctionne pas des masses
                System.out.print("                  Combien y a-t-il de réponses possibles? ");
                int nbRep = sc.nextInt();
                String[] rePossible = new String[nbRep]; //tableau de string
                if (nbRep > 0) {
                    sc.nextLine(); //On vide la ligne avant d'en lire une autre
                    for (int j=0; j<nbRep; j++) { //On boucle pour obtenir toutes les réponses fermées
                        System.out.print("                  -Entrez une réponse possible: ");
                        rePossible[j] = sc.nextLine();
                    }
                }
                tabQuestions[i] = new Question(intitule, intituleCourt, typeretour, rePossible); //On remplis le tableau de question avec la nouvelle question
            }
            else{ tabQuestions[i] = new Question(intitule, intituleCourt, typeretour);} //Sinon on remplis le tableau de question avec la nouvelle question sans tableau
        }

        Formulaire form = new Formulaire(nomQ, nbQuestions, dateDebut, dateFin, tabQuestions); //On crée un nouvel objet Formulaire
        EnregistrementFormulaire.enregistrerF(form);//on enregistre le formulaire dans un fichier .txt

        try { //Le Fichier ListeFormulaires.txt contient le nom de tous les Formulaires
            FileWriter fw = new FileWriter("ListeFormulaires.txt", true); //true permet d'écrire à la suite du fichier pour ne pas écraser les données précédentes
            fw.write(nomQ+"\n");
            fw.close();
        } catch (IOException e) { // Se produit lors d'une erreur d'écriture ou de lecture
            e.printStackTrace();
            System.out.println("Erreur. Le formulaire n'a pas pu être ajouté à la liste des formulaires.");
        }

        //Menu.menuAdmin(); //Retour au menu admin
    }

    //Precondition: aucune
    //Postcondition: affiche tous les formulaires déjà créés (en lisant le fichier ListeFormulaire.txt)
    //				 et renvoie une chaine correspondante au choix de l'utilisateur (le nom du formulaire)
    public static String afficherFormulaires(){
        System.out.println("           1- Retour                                      ");
        System.out.println("           2- Quitter le programme                        ");
        int indice = 3;

        //map permettant d'avoir la correspondance entre le numéro du choix et le nom du Formulaire
        Map<Integer, String> mapChoix = new HashMap<>();

        //Lecture du fichier ListeFormulaires.txt
        try{ 
            InputStream ips = new FileInputStream("ListeFormulaires.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr); 
            String ligne;
            while ((ligne=br.readLine())!=null){ //On lit les lignes une par une
                System.out.println("           "+indice+"- "+ligne); //Pour chaque ligne on affiche le nom du formulaire inscrit
                mapChoix.put(indice, ligne);
                indice++;
            }
            br.close();
        }catch (Exception e){System.out.println(e.toString());}
        

        System.out.print("               Quel est votre choix? ");

        Scanner sc = new Scanner(System.in);
        //sc.nextLine(); //ca faisait tout bugger
        int choix = sc.nextInt();
        choix = Menu.verifieChoix(1,indice,choix,sc);
       	System.out.println("");

        if (choix<3){
            if (choix==1) { Menu.menu();}
            else if(choix==2){ System.out.println("Programme quitté. A bientôt! "); System.exit(0);}
            else {System.out.println("Erreur de saisie dans afficherFormulaire, interruption du programme.");}
        }
        
        return (mapChoix.get(choix)); //null si la clé 'choix' n'existe pas
    }
    
    //Precondition: nomFormulaire est une chaine correspondant exactement au nom du Formulaire (et du fichier .txt)
    //Postcondition: met à jour le formulaire choisi après avoir choisi les modifications à faire
    public static void modifierFormulaire(String nomFormulaire) {
    	System.out.println("Vous souhaitez modifier le formulaire "+nomFormulaire);

        Formulaire formModif = null;
        try {
            formModif = LectureQuestionnaires.lectureFormulaire(nomFormulaire);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        for (int i=0; i <formModif.nbQuestions; i++) {
            System.out.println("Voulez-vous modifier la question :" + formModif.tabQuestions[i].getIntitule());
            System.out.println("Répondez par o ou n");
            String choix = sc.nextLine();
            if (choix.equals("o")) {
                System.out.println("Saisissez le nouvel intitulé");
                String nvQuestion = sc.nextLine();
                formModif.tabQuestions[i].setIntitule(nvQuestion);
                System.out.println("Le type de retour est : " +  formModif.tabQuestions[i].getTyperetour() + ". Voulez vous le modifier ? (répondre o ou n)");
                String ifmodif = sc.nextLine();
                if (ifmodif.equals("o")) {
                    System.out.println("Saisissez le nouveau type de retour (o pour ouverte, f pour fermée et n pour numérique)");
                    String nvInt = sc.nextLine();
                    formModif.tabQuestions[i].setTyperetour(nvInt);
                }
            }
        }
        System.out.println("Voulez-vous ajouter une question ?");
        String choix = sc.nextLine();
        if (choix.equals("o")){
            ajouterQuestion(formModif);
        }
        
        System.out.println("Vous avez modifié le formulaire avec succès !"); 
        File objt_serialize = new File(nomFormulaire+".ser");
        objt_serialize.delete(); // on supprime le fichier dans lequel l'objet avait été serializé 
        EnregistrementFormulaire.enregistrerF(formModif);
    }

    //Preconditions : formModif est un formulaire
    //PostCondition : Ajoute une question

    public static void ajouterQuestion(Formulaire form){
        Question question;
        Scanner sc = new Scanner(System.in);
        String intitule;
        String typeretour;
        String intituleCours;
        System.out.println("Veuiller saisir l'intitulé de la nouvelle question");
        intitule = sc.nextLine();
        System.out.println("Veuillez résumer cette question en un mot pour les statistiques");
        intituleCours = sc.nextLine();
        System.out.println("Veuiller enter le type de la question : o (Ouverte), f(Fermée) ou n(Numérique)");
        typeretour = sc.nextLine();
        while(!typeretour.equals("o") && !typeretour.equals("f") && !typeretour.equals("n")){ //tant que la réponse n'est pas o, f ou n
            System.out.print("                 Veuillez rentrer o, f ou n: ");
            typeretour = sc.nextLine();
            System.out.println("");
        }
        if(typeretour.equals("f")){ //si c'est une question fermée il y a plusieurs réponses prédéfinies -> Pour le moment ça fonctionne pas des masses
            System.out.print("                  Combien y a-t-il de réponses possibles? ");
            int nbRep = sc.nextInt();
            String[] rePossible = new String[nbRep]; //tableau de string
            if (nbRep > 0) {
                sc.nextLine(); //On vide la ligne avant d'en lire une autre
                for (int j=0; j<nbRep; j++) { //On boucle pour obtenir toutes les réponses fermées
                    System.out.print("                  -Entrez une réponse possible: ");
                    rePossible[j] = sc.nextLine();
                }
                question = new Question(intitule,intituleCours,typeretour,rePossible);
            }
            else{
                question = new Question(intitule,intituleCours,typeretour);
            }
        }
        else{
            question = new Question(intitule, intituleCours, typeretour);
        }

        Question[] questions = form.getTabQuestions();
        int l = questions.length;
        Question[] nvquestions = new Question[l+1];
        Question q;
        for (int iterateur = 0; iterateur<l; iterateur++){
            q = questions[iterateur];
            nvquestions[iterateur] = q;
        }
        nvquestions[l] = question;
        form.setQuestions(nvquestions);
    }

    //Pre :  aucune
    //Post : permet la suppression d'un formulaire une fois choisi
    public static void supprimerFormulaire() {
        System.out.println("Vous souhaitez supprimer un formulaire. Veuillez choisir le numéro correspondant.");
        String nomFormulaireChoisi = afficherFormulaires();
        supprimerFormulaireBis(nomFormulaireChoisi);
    }
    
    //Pre :  nomFormulaire est le nom d'un formulaire
    //Post : permet la suppression d'un formulaire
    public static void supprimerFormulaireBis(String nomFormulaire) {
    	
        String line = null;

        try {
            FileReader fr = new FileReader("ListeFormulaires.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter("ListeTemp.txt",true); //nouvelle liste des formulaires

            line = br.readLine();
            while(line != null) { //on recopie la liste des formulaires
                if (!(line.equals(nomFormulaire))) { //sauf si la ligne est le formulaire à supprimer
                    fw.write(line + "\n");
                }
                line = br.readLine();
            } 
            fw.close();
            br.close();
     
            File f = new File("ListeFormulaires.txt");
            f.delete(); //on supprime l'ancienne liste
            File e = new File("ListeTemp.txt");
            e.renameTo(new File("ListeFormulaires.txt")); //on remet l'ancien nom à la nouvelle
            
            File objt_serialize = new File(nomFormulaire+".ser");
            if ( objt_serialize.exists() ) {
                objt_serialize.delete(); // on supprime le fichier dans lequel l'objet avait été serializé 
            }
            File fichierstat = new File(nomFormulaire+".csv");
            if ( fichierstat.exists() ) {
                fichierstat.delete(); //on supprime le fichier dans lequel il y a les données pour les stats
            }
            System.out.println("Contenu de la liste des formulaires bien mise à jour.");

        }
        catch(FileNotFoundException ex) {
            System.out.println("Impossible d'ouvrir le fichier ListeFormulaires.txt ou nomFormulaire.ser");
        }
        catch(IOException ex) {
            System.out.println("Impossible de lire le fichier ListeFormulaires.txt ou nomFormulaire.ser");
        }
    }
    

    //Pre: dateLue est une chaine de charactere, sc est un Scanner
    //Post: renvoie la date saisie sous la forme jj/mm/aaaa et dans le type LocalDate (et non plus une string)
	public static LocalDate verifieConvertisDate (String dateLue, Scanner sc){
    	LocalDate date = null;
    	while (!(dateLue.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))){
    		System.out.print("\nVotre date n'est pas correcte, veuillez respecter le format jj/mm/aaaa: ");
    		dateLue = sc.nextLine();
    	}
    	try{
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy"); //indique le format de la date
            date = LocalDate.parse(dateLue, formatter); //convertis la String en Date
            //System.out.println(formatter.format(localDate)); //affiche la Date       	
        }catch(Exception e){
        	System.out.println("Votre date n'existe pas. Impossible de la convertir au format date.");
        	System.out.print("Merci de bien vouloir ressaisir une date correcte: ");
        	verifieConvertisDate(sc.nextLine(), sc);
        }finally{
        	return date;
        }
    }


    @Override
    public String toString(){
        return ("Nom du formulaire: "+this.getNomFormulaire()+ "\n "
        	  + "Date de debut : "+this.getDateDebut()+"\n"
        	  + "Date de fin : " +this.getDateFin());
    }
}