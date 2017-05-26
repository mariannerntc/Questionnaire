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
    	 Rengine r = Rengine.getMainEngine();
         if(r == null)
             r = new Rengine(new String[] {"--vanilla"}, false, null);
    	r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");
    	r.eval("library(JavaGD)"); // on lance la librairie JavaGD
    	r.eval("library(FactoMineR)"); //on "lance" la librairie factominer
    	
    	r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");//Recupere le fichier des retours
    	System.out.println("Veuillez selectionner les variables quantitatives que vous souhaitez étudier (Au moins 2)");
		List<String> variables = Auxiliaires.choixQuestion(questionnaire);
		String liste = new String();
		for (String intitule  : variables){
			liste = liste+"'"+ intitule +"', ";
		}
		liste = liste.substring(0,liste.length()-2);
    	r.eval("Dataset.PCA<-Dataset[, c("+ liste+")]");
    	r.eval("res<-PCA(Dataset.PCA , scale.unit=TRUE, ncp=5, graph = FALSE)");
    	r.eval("JavaGD(width=800, height=800 , ps=14)"); //A chaque fois qu'on affiche un graphique on met cette commande pour que le graphique soit dans la fenetre
    	r.eval("plot.PCA(res, axes=c(1, 2), choix=\"ind\", habillage=\"none\", col.ind=\"black\", col.ind.sup=\"blue\", col.quali=\"magenta\", label=c(\"ind\", \"ind.sup\", \"quali\"))"); 
    	r.eval("JavaGD(width=800, height=800, ps=14)"); 
    	r.eval("plot.PCA(res, axes=c(1, 2), choix=\"var\", col.var=\"black\", col.quanti.sup=\"blue\", label=c(\"var\", \"quanti.sup\"), lim.cos2.var=0)");
    	
    	menuApresGraphique();
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
    	System.out.println("Votre variable doit cependant etre quantitative");
    	System.out.println("Si la fenetre qui va s'ouvrir est vide, reessayez et choisisez une variable quantitative");
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine(); 
        //sc.close();
    	r.eval("JavaGD(width=800, height=800, ps=14)"); 
    	r.eval("with(Dataset, hist(" +choix+ ", scale='frequency', breaks='Sturges', col='darkgray'))");
 
        menuApresGraphique();
	}
	
	public static void boxplot(Formulaire questionnaire){
		
		Rengine r = Rengine.getMainEngine();
        if(r == null)
            r = new Rengine(new String[] {"--vanilla"}, false, null);
        
        String nomFormulaireChoisi = questionnaire.getNomFormulaire();
         
    	
    	r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");

    	r.eval("JavaGD(width=800, height=800, ps=14)"); 
    	//r.eval("with(Dataset, hist(" +choix+ ", scale='frequency', breaks='Sturges', col='darkgray'))");
    	//r.eval("with(Dataset, dotplot("+choix+", bin=FALSE))");
    	r.eval("boxplot(Dataset)");
    	
    	menuApresGraphique();


		
	}
	public static void afc(Formulaire questionnaire){
    	String nomFormulaireChoisi = questionnaire.getNomFormulaire();
   	 Rengine r = Rengine.getMainEngine();
        if(r == null)
            r = new Rengine(new String[] {"--vanilla"}, false, null);
		r.eval("library(FactoMineR)"); //on "lance" la librairie factominer
		r.eval("Dataset <- read.table('" + nomFormulaireChoisi + ".csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");
	   	System.out.println("Veuillez selectionner les variables quantitatives que vous souhaitez étudier (Au moins 2)");
			List<String> variables = Auxiliaires.choixQuestion(questionnaire);
			String listemca = new String();
			for (String intitule  : variables){
				listemca = listemca+"'"+ intitule +"', ";
			}
			listemca = listemca.substring(0,listemca.length()-2);
		
	   	r.eval("Dataset.MCA<-Dataset[, c("+ listemca+")]");
		//r.eval("Dataset.MCA<-Dataset[, c('saison', 'endroit', 'marquevoiture')]");
		r.eval("res<-MCA(Dataset.MCA, ncp=5, graph = FALSE)");
		r.eval("JavaGD(width=800, height=800, ps=14)"); 
		r.eval("plot.MCA(res, axes=c(1, 2), col.ind='black', col.ind.sup='blue', col.var='darkred', col.quali.sup='darkgreen', label=c('ind', 'ind.sup', 'quali.sup', 'var'))"); 
		r.eval("JavaGD(width=800, height=800, ps=14)"); 
		r.eval("plot.MCA(res, axes=c(1, 2), choix='var', col.var='darkred', col.quali.sup='darkgreen', label=c('var', 'quali.sup'))");
		menuApresGraphique();

		
	}
	

	public static void menuApresGraphique(){
    	Scanner sc2  = new Scanner(System.in);
        System.out.println("    1- Retour au menu principal");
        System.out.println("    2- Retour");
        System.out.println("    3- Quitter le programme");

        int choix2 = sc2.nextInt();
        while(!((choix2 <3)&(choix2>0))){
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
	


}



 
