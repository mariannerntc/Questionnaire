
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PageAdmin extends JFrame 
{
static PageAdmin thePageAdmin;

JLabel lbPartieA;
JButton btCreer;
JButton btModif;
JButton btSuppr;
JButton btStat;
JButton btRet;
JButton btExi;
/**
 * Ajout des composant à un main panel
 */
public static boolean RIGHT_TO_LEFT = false;
    public static void addComponentsToPane(JFrame frame, Container pane){


       JLabel lbPartieA = new JLabel( "Partie Administrateur"  );
        lbPartieA.setHorizontalAlignment(JLabel.CENTER);
        lbPartieA.setPreferredSize(new Dimension(40,80));
        pane.add(lbPartieA, BorderLayout.PAGE_START);

        //Creation des boutons
        JButton btCreer = new JButton( "Créer un nouveau Formulaire"  );
        JButton btModif = new JButton( "Modifier un formulaire"  );
        JButton btSuppr = new JButton( "Supprimer un formulaire"  );
        JButton btStat = new JButton( "Obtenir des statistiques"  );
        JButton btRet = new JButton( "Retour"  );
        JButton btExi = new JButton( "Quitter le programme"  );

        //Ajout des boutons à un sous panel
        JPanel subPanel = new JPanel();
        subPanel.add(btCreer);
        subPanel.add(btModif);
        subPanel.add(btSuppr);
        subPanel.add(btStat);
        subPanel.add(btRet);
        subPanel.add(btExi);

        //Ajout du sous panel au main container
        pane.add(subPanel, BorderLayout.PAGE_END);





        // Fonctions qui commande les actions en fonctions des cliques boutons
        btCreer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);


                PageCreer pgc = new PageCreer();
                //pgc.setVisible(true);
                frame.dispose();
            }
        });

        btExi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);



                frame.dispose();
            }
        });

        btModif.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);


                PageCreer pgc = new PageCreer();
                pgc.setVisible(true);
                frame.dispose();
            }
        });

        btRet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);


               new Paccueil().main();
                frame.dispose();
            }
        });

        btStat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);


                PageStat pgc = new PageStat();
                //pgc.setVisible(true);
                frame.dispose();
            }
        });

        btSuppr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);


                PageClient pgc = new PageClient();
                pgc.setVisible(true);
                frame.dispose();
            }
        });
    }
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
    thePageAdmin = new PageAdmin();


} 

/**
 * Creation de la fenetre
 */
public PageAdmin()
{
   JFrame frame = new JFrame("GESTION DE QUESTIONNAIRE");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addComponentsToPane(frame, frame.getContentPane());
    frame.pack();
    frame.setSize(800,700);
    frame.setVisible(true);
} 
} 
