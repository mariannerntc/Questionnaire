import java.io.Serializable;

/**
 * Created by Tiphaine Dumur on 22/03/17.
 **/
public class Retours implements Serializable{
    private String type;

    //Cette classe permet de recupérer les retours aux questions
    public Retours(String typeInit){
        this.type = typeInit;

    }
}
