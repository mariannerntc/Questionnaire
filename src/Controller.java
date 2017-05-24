import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by eisti on 23/05/17.
 */
public class Controller implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cmd = actionEvent.getActionCommand();

        switch (cmd){
            case "form" :
                new PageClient().setVisible(true);

                break;
            case "admin" : new PageAdmin().setVisible(true);
                break;

        }
    }



}
