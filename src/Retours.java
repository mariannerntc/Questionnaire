import java.io.Serializable;

/**
 * Created by Tiphaine Dumur on 22/03/17.
 **/
public class Retours implements Serializable{
    private String type;
    private static final long serialVersionUID = 6529685098267757690L;

    //Cette classe permet de recupérer les retours aux questions
    public Retours(String typeInit){
        this.type = typeInit;

    }
}
