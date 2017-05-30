import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * PAGE SERVANT A LA CREATION DE QUESTIONNAIRE
 */
public class PageCreer extends JFrame{

    public static void main(String args[]){
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch ( ClassNotFoundException e )
        {
        }
        catch ( InstantiationException e )
        {
        }
        catch ( IllegalAccessException e )
        {
        }
        catch ( UnsupportedLookAndFeelException e )
        {
        }
        new PageCreer();
    }
    public static boolean RIGHT_TO_LEFT = false;

    public PageCreer(){
        JFrame frame = new JFrame("GESTION DE QUESTIONNAIRE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Le conteneur n'est pas un borderlayout !"));
            return;
        }

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        JLabel lbBienvenue = new JLabel("<html>Création de questionnaire<br>Partie en construction </html>");
        lbBienvenue.setHorizontalAlignment(JLabel.CENTER);
        lbBienvenue.setPreferredSize(new Dimension(40, 80));
        pane.add(lbBienvenue, BorderLayout.PAGE_START);

        JButton retour = new JButton("Retour");
        pane.add(retour, BorderLayout.PAGE_END);

        retour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //On se redirige puis on referme la page actuelle
                PageAdmin pgc = new PageAdmin();
                pgc.setVisible(true);
                frame.dispose();
            }
        });

        frame.pack();
        frame.setSize(800,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
