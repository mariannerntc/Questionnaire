/**
 * Created by Tiphaine Dumur on 26/04/17.
 * Cette classe va contenir toutes les fonctions auxiliaires de verification
 **/


public class Auxiliaires {
    public static boolean estUnNombre(String chaine) {
        try {
            Float.parseFloat(chaine);
            return true;
        } catch (NumberFormatException e){
            return false;
        }

    }

    public static boolean existe(String retour, String[] repsPossibles){
        //Fonction verifiant l'existance d'une reponse dans l'ensemble des questions possibles
        boolean test = false;
        for (String repPossible : repsPossibles){
            if ((repPossible.equals(retour))){
                test = true;
            }
        }
        return test;
    }
}
