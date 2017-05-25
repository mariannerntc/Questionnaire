import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Statistiques2 {
	public static void acp(Formulaire questionnaire){
	    
    	String nomFormulaireChoisi = questionnaire.getNomFormulaire();



		//Rengine r = new Rengine(new String[]{"--no-save"}, false, null);
        Rengine r = Rengine.getMainEngine();
        if(r == null)
            r = new Rengine(new String[] {"--vanilla"}, false, null);

    	r.eval("library(JavaGD)"); // on lance la librairie JavaGD
    	r.eval("library(FactoMineR)"); //on "lance" la librairie factominer
    	
 
    	r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");//Recupere le fichier des retours
    	System.out.println("Veuillez selectionner les variables quantitatives que vous souhaitez étudier (Au moins 2)");
		List<String> variables = Auxiliaires.choixQuestion(questionnaire);
		String liste = new String();
		for (String intitule  : variables){
			liste = liste + "'" + intitule + "', ";
		}
		liste.substring(0,liste.length()-1);
    	r.eval("Dataset.PCA<-Dataset[, c("+ liste+")]");
    	r.eval("res<-PCA(Dataset.PCA , scale.unit=TRUE, ncp=5, graph = FALSE)");
    	r.eval("JavaGD()"); //A chaque fois qu'on affiche un graphique on met cette commande pour que le graphique soit dans la fenetre
    	r.eval("plot.PCA(res, axes=c(1, 2), choix='ind', habillage='none', col.ind='black', col.ind.sup='blue', col.quali='magenta', label=c('ind', 'ind.sup', 'quali')");
    	//r.eval("JavaGD(width=800, height=700, ps=12)");     	//r.eval("plot.PCA(res, axes=c(1, 2), choix=\"ind\", habillage=\"none\", col.ind=\"black\", col.ind.sup=\"blue\", col.quali=\"magenta\", label=c(\"ind\", \"ind.sup\", \"quali\"))"); 
    	r.eval("JavaGD(name='Individuals Factor Map', width=800, height=700)"); 
    	r.eval("plot.PCA(res, axes=c(1, 2), choix=\"var\", col.var=\"black\", col.quanti.sup=\"blue\", label=c(\"var\", \"quanti.sup\"), lim.cos2.var=0)");
    	
    	
    	//A LIRE
    	//Les 2 plot.PCA au dessus affiche l'ACP, pour l'automatiser il faudrait donc changer le r.eval à la ligne 25 changer le bonjour et le salut par les variables que l'admin veut, sachant qu'il peut en mettre un nombre "ilimité" tant qu'elles sont quantitatives
    	//ici actuellement pour faire marcher le programme il faut avoir le fichier q1.csv dans le répertoire du projet et que celui ci soit rempli
    	//A LIRE
    	
    	
}
	
	public static void hist(Formulaire questionnaire){
		
        Rengine r = Rengine.getMainEngine();
        if(r == null)
            r = new Rengine(new String[] {"--vanilla"}, false, null);
        
        String nomFormulaireChoisi = questionnaire.getNomFormulaire();
         
    	
    	r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");
    	System.out.println("Pour quelle variable voulez vous un histogramme ?");
    	System.out.println("Veuillez choisir et écrire une des variables suivantes");
    	
    	
    	BufferedReader br = null;
		FileReader fr = null;

		try {
			String FILENAME = new String(nomFormulaireChoisi+".csv");
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			br = new BufferedReader(new FileReader(FILENAME));
			System.out.println(br.readLine());
			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine(); 
        //sc.close();
    	r.eval("JavaGD(width=800, height=700, ps=12)"); 
    	r.eval("with(Dataset, hist(" +choix+ ", scale='frequency', breaks='Sturges', col='darkgray'))");
    	
    	Scanner sc2  = new Scanner(System.in);
        System.out.println("    1- Retour au menu principal");
        System.out.println("    2- Retour");
        System.out.println("    3- Quitter le programme");

        int choix2 = sc.nextInt();
        while(!((choix2 <6)&(choix2>0))){
            Menu.verifieChoix(1,2,choix2,sc2);
        }

        switch (choix2){
            case 1:
                Menu.menuAdmin();
                break;
                
            case 2:
            	Statistiques.stats();
                break;

            case 3: System.out.println("Programme quitté. A bientôt! "); //Quitte le programme
            break;
        }
	}
	
	public static void boxplot(Formulaire questionnaire){
		
		Rengine r = Rengine.getMainEngine();
        if(r == null)
            r = new Rengine(new String[] {"--vanilla"}, false, null);
        
        String nomFormulaireChoisi = questionnaire.getNomFormulaire();
         
    	
    	r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");
    	System.out.println("Pour quelle variable voulez vous un histogramme ?");
    	System.out.println("Veuillez choisir et écrire une des variables suivantes");
    	
    	
    	BufferedReader br = null;
		FileReader fr = null;

		try {
			String FILENAME = new String(nomFormulaireChoisi+".csv");
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			br = new BufferedReader(new FileReader(FILENAME));
			System.out.println(br.readLine());
			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine(); 
        //sc.close();
    	r.eval("JavaGD(width=800, height=700, ps=12)"); 
    	r.eval("with(Dataset, hist(" +choix+ ", scale='frequency', breaks='Sturges', col='darkgray'))");
    	//r.eval("with(Dataset, dotplot("+choix+", bin=FALSE))");
    	Scanner sc2  = new Scanner(System.in);
        System.out.println("    1- Retour au menu principal");
        System.out.println("    2- Retour");
        System.out.println("    3- Quitter le programme");

        int choix2 = sc.nextInt();
        while(!((choix2 <6)&(choix2>0))){
            Menu.verifieChoix(1,2,choix2,sc2);
        }

        switch (choix2){
            case 1:
                Menu.menuAdmin();
                break;
                
            case 2:
            	Statistiques.stats();
                break;

            case 3: System.out.println("Programme quitté. A bientôt! "); //Quitte le programme
            break;
        }
		
	}}

//r.eval("library(FactoMineR)"); //on "lance" la librairie factominer
//r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");
/*
r.eval("Dataset.PCA<-Dataset[, c('q1', 'q2')]");
r.eval("res<-PCA(Dataset.PCA , scale.unit=TRUE, ncp=5, graph = FALSE)");
r.eval("JavaGD(width=800, height=700, ps=12)"); 
r.eval("plot.PCA(res, axes=c(1, 2), choix=\"ind\", habillage=\"none\", col.ind=\"black\", col.ind.sup=\"blue\", col.quali=\"magenta\", label=c(\"ind\", \"ind.sup\", \"quali\"))"); 
r.eval("JavaGD(name='Individuals Factor Map', width=800, height=700)"); 
r.eval("plot.PCA(res, axes=c(1, 2), choix=\"var\", col.var=\"black\", col.quanti.sup=\"blue\", label=c(\"var\", \"quanti.sup\"), lim.cos2.var=0)");
*/


 
