import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import java.io.File;


public class Statistiques2 {
	public static void stats(){
	    
    	//String nomFormulaireChoisi = Formulaire.afficherFormulaires();
    	//System.out.println("Le formulaire choisi est: "+nomFormulaireChoisi);
		
		

    	
    	Rengine r = new Rengine(new String[]{"--no-save"}, false, null);
    	
    	System.out.println("test");

    	r.eval("library(JavaGD)");
    	r.eval("JavaGD()");
    	r.eval("data <- read.table('testquestionnaire.csv', header=TRUE, sep=',', na.strings='/', dec='.', strip.white=TRUE)");
    	r.eval("library(FactoMineR)");
    	r.eval("data.PCA<-data[, c('bonjour','jesepo')]");
    	r.eval("res<-PCA(data.PCA,scale.unit=TRUE,ncp=5,graph=FALSE)");
    	r.eval("plot.PCA(res,axes=c(1,2),choix='ind',habillage='none',col.ind='black', col.ind.sup='blue', col.quali='magenta', label=c('ind', 'ind.sup', 'quali'))");
    	
    	
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
