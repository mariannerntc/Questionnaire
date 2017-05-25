import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import java.io.File;


public class Statistiques2 {
	public static void stats(){
	    
    	//String nomFormulaireChoisi = Formulaire.afficherFormulaires();
    	//System.out.println("Le formulaire choisi est: "+nomFormulaireChoisi);
		
		

    	
    	Rengine r = new Rengine(new String[]{"--no-save"}, false, null);
    	
    	System.out.println("test");

    	r.eval("library(JavaGD)"); // on lance la librairie JavaGD
    	r.eval("library(FactoMineR)"); //on "lance" la librairie factominer
    	
 
    	r.eval("Dataset <- read.table('/home/eisti/Documents/Questionnaire/Questionnaire/q1.csv', header=TRUE, sep=',', na.strings='NA', dec='.', strip.white=TRUE)");
    	r.eval("Dataset.PCA<-Dataset[, c('bonjour', 'salut')]");
    	r.eval("res<-PCA(Dataset.PCA , scale.unit=TRUE, ncp=5, graph = FALSE)");
    	//r.eval("JavaGD()");
    	//r.eval("JavaGD(width=1500, height=900, ps=12)");
    	//r.eval("plot.PCA(res, axes=c(1, 2), choix='var', new.plot=TRUE, col.var='black', col.quanti.sup='blue', label=c('var', 'quanti.sup'), lim.cos2.var=0)");
    	//r.eval("plot(Dataset)");
    	r.eval("JavaGD()"); //A chaque fois qu'on affiche un graphique on met cette commande pour que le graphique soit dans la fenetre
    	r.eval("plot.PCA(res, axes=c(1, 2), choix='ind', habillage='none', col.ind='black', col.ind.sup='blue', col.quali='magenta', label=c('ind', 'ind.sup', 'quali')");
    	//r.eval("JavaGD(width=800, height=700, ps=12)"); 
    	//r.eval("plot.PCA(res, axes=c(1, 2), choix=\"ind\", habillage=\"none\", col.ind=\"black\", col.ind.sup=\"blue\", col.quali=\"magenta\", label=c(\"ind\", \"ind.sup\", \"quali\"))"); 
    	r.eval("JavaGD(name='Individuals Factor Map', width=800, height=700)"); 
    	r.eval("plot.PCA(res, axes=c(1, 2), choix=\"var\", col.var=\"black\", col.quanti.sup=\"blue\", label=c(\"var\", \"quanti.sup\"), lim.cos2.var=0)");
    	
    	
    	//A LIRE
    	//Les 2 plot.PCA au dessus affiche l'ACP, pour l'automatiser il faudrait donc changer le r.eval à la ligne 25 changer le bonjour et le salut par les variables que l'admin veut, sachant qu'il peut en mettre un nombre "ilimité" tant qu'elles sont quantitatives
    	//ici actuellement pour faire marcher le programme il faut avoir le fichier q1.csv dans le répertoire du projet et que celui ci soit rempli
    	//A LIRE
    	
    	
    	/*File f = new File(nomFormulaireChoisi+".csv");
    	//methode pour tester l'existence
    	if ( f.exists() ) {
    		System.out.println("Le fichier existe bien!");
    	   	// REXP x; //REXP transforme les objets R en objet java
    	   	
        	r.eval("library(JavaGD)");
        	r.eval("JavaGD()");
        	//x = r.eval("data<-read.csv(file='/home/eisti/testt.csv', head=TRUE, sep=',')");
        	r.eval("data <- read.table('testquestionnaire.csv', header=TRUE, sep=',', na.strings='/', dec='.', strip.white=TRUE)");
        	//x = r.eval("summary(data)");
        	//r.eval("plot(data)");
        	r.eval("data.PCA<-data[, c('bonjour','jesepo')]");
        	r.eval("res<-PCA(data.PCA,scale.unit=TRUE,ncp=5,graph=FALSE)");
        	r.eval("plot.PCA(res,axes=c(1,2),choix='ind',habillage='none',col.ind='black', col.ind.sup='blue', col.quali='magenta', label=c('ind', 'ind.sup', 'quali'))");
        	//x = r.eval("library(FactoMineR)");
        	//x = r.eval("res.pca = PCA(data, scale.unit=TRUE, ncp=3, graph=T)");
        	
    	} else {
    		System.out.println("Personne n'a répondu au questionnaire!");
    		Formulaire.afficherFormulaires();
    		
    		
    	} */
}

}

 
