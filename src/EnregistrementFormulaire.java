import java.io.*;


//Cette classe à pour rôle d'enregistrer le formulaire dans un fichier txt
    //Ce fichier portera le nom "formulaire.nom.txt"
public class EnregistrementFormulaire implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;
	
    public static void enregistrerF(Formulaire formulaire){
        ObjectOutputStream oos;
        String nomFichier = formulaire.getNomFormulaire();
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(nomFichier+".ser"))));

            oos.writeObject(formulaire);
            oos.close();
            System.out.println("");
        	System.out.println("              -----------------------------------------------------------");
            System.out.println("                  Le formulaire a bien été enregistré.");
            System.out.println("");
            //afficherContenuFichier(nomFichier+".txt");
            
        } catch (FileNotFoundException e) { // Si l'objet FileInputStream ne trouve aucun fichier
            e.printStackTrace();
        } catch (IOException e) { // Se produit lors d'une erreur d'écriture ou de lecture
            e.printStackTrace(); 
        } 
        
        File fichier = new File(nomFichier+".csv");
    }
    
    
    //Postcondition: permet d'afficher le contenu du fichier ayant pour nom 'nomFichier'
    public static void afficherContenuFichier(String nomFichier){ 
    	System.out.println("Ce qui a été enregistré dans le fichier: ");
    	ObjectInputStream ois;
    	try {
	        ois = new ObjectInputStream(
	                new BufferedInputStream(
	                  new FileInputStream(
	                    new File(nomFichier))));
	              
	        try {
	          System.out.println("-------------------------------------------------");
			  System.out.println("Fichier: "+nomFichier);
			  System.out.println(ois.readObject().toString());
			  System.out.println("-------------------------------------------------");
			} catch (ClassNotFoundException e) {
			  e.printStackTrace();
	        }
	        ois.close();
	        
	   } catch (FileNotFoundException e) { // Si l'objet FileInputStream ne trouve aucun fichier
	       e.printStackTrace();
	   } catch (IOException e) { // Se produit lors d'une erreur d'écriture ou de lecture
	       e.printStackTrace();
       }
    }
    
}
//Il faudra voir à determiner un chemin d'enregistrement pour le fichier