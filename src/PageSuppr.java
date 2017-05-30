
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PageSuppr extends JFrame
{

    public static boolean RIGHT_TO_LEFT = false;

    /**Fonction qui ajoute les divers panel, label et boutons au main panel */


    public static void addComponentsToPane(JFrame frame, Container pane) {

        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Le conteneur n'est pas un borderlayout !"));
            return;
        }

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        // Ajout du premier Label
        JLabel lbBienvenue = new JLabel("<html><HUGE>Suppression de questionnaire<br></HUGE>" +
                "Choissisez un questionnaire à supprimer</html>");
        lbBienvenue.setHorizontalAlignment(JLabel.CENTER);
        lbBienvenue.setPreferredSize(new Dimension(40, 80));
        pane.add(lbBienvenue, BorderLayout.PAGE_START);

        //Ajout De la liste de questionnaire au panel
        JList lsListForm = LectureQuestionnaires.listeFormulaires();

        lsListForm.setPreferredSize(new Dimension(40,40));
        pane.add(lsListForm,BorderLayout.CENTER);

        lsListForm = LectureQuestionnaires.listeFormulaires(); // Fonction qui liste tout les questionnaires

        //Si l'on clique sur l'un des questionnaires on le supprime
        lsListForm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                JList list = (JList)mouseEvent.getSource();
                String a = list.getSelectedValue().toString();

                Formulaire.supprimerFormulaireBis(a); //Suppression du questionnaire
                PageAdmin pgr = new PageAdmin();// On retourne sur la page Admin

                frame.dispose();
            }
        });


    }
    /**
     */
    public static void main( String args[] )
    {
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
        new PageSuppr();
    }

    /**
     * Fonction qui créé la frame contenant la liste des questionnaire
     */
    public PageSuppr()
    {
        JFrame frame = new JFrame("GESTION DE QUESTIONNAIRE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame, frame.getContentPane());
        frame.pack();
        frame.setSize(800,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}