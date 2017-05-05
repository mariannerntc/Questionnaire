import java.io.Serializable;
import java.util.Iterator;

public class Question implements Serializable {

    private String intitule;
    private String typeretour;
    private String[] repPossible;
    private static final long serialVersionUID = 6529685098267757690L;

    //Constructeur
    public Question(String intitule, String typeretour) {
        this.intitule = intitule;
        this.typeretour = typeretour;
    }
    //Surcharge
    public Question(String intitule, String typeretour, String[] repPossible) {
        this.intitule = intitule;
        this.repPossible = repPossible;
        this.typeretour = typeretour;//C'est beaucoup plus simple pour la sauvegarde de garder une unique lettre et pas de caractère spéciaux 
    }

    //Getters
    public String getIntitule() {return this.intitule;}
    public String getTyperetour() {return this.typeretour;}
    public String[] getRepPossible() {return this.repPossible;}

    public void setIntitule(String nvIntitule) {
        this.intitule = nvIntitule;
    }

    public void setTyperetour(String nvType) {
        this.typeretour = nvType;
    }

    public String toString(){
        return this.intitule;
    }


}