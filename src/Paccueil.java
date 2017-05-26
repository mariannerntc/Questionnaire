
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Paccueil extends JFrame
{


   /**
    */
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
      JLabel lbBienvenue= new JLabel("<html><HUGE>Bienvenue sur notre application de gestion de questionnaires.</HUGE></html>");
      lbBienvenue.setHorizontalAlignment(JLabel.CENTER);
      lbBienvenue.setPreferredSize(new Dimension(40,80));
      pane.add(lbBienvenue, BorderLayout.PAGE_START);

      // Ajout d'un second Label
      JLabel lbPresentation = new JLabel("<html>PROJET GENIE LOGICIEL N°2 <br> Ce logiciel vous facilite la gestion de vos formulaires,<br> " +
              "L'administrateur pourra gérer l'ensemble des formulaires existant, <br>" +
              "l'utilisateur pourra répondre aux formulaires directement sur l'application.<br>" +
              "Auteur : ARIES, BELGUENDOUZ, DUMUR, RENTCHLER, SCHALL </html>");
      lbPresentation.setHorizontalAlignment(JLabel.CENTER);
      lbPresentation.setPreferredSize(new Dimension(400, 400));
      pane.add(lbPresentation, BorderLayout.CENTER);

      //Ajout des deux bouton
      JButton tbtBoutonForm = new JButton("Accéder à la partie client");
      JButton tbtBoutonAdmin = new JButton("Accéder à la partie administrateur");
      JPanel subPanel = new JPanel();
      subPanel.add(tbtBoutonForm);
      subPanel.add(tbtBoutonAdmin);
      pane.add(subPanel, BorderLayout.PAGE_END);

      //MouseListener permettant les actions en cas de clique sur l'un des boutons
      tbtBoutonForm.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);

            //On se redirige puis on referme la page actuelle
            PageClient pgc = new PageClient();
            pgc.setVisible(true);
            frame.dispose();
         }
      });

      tbtBoutonAdmin.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);


            PageAdmin pgc = new PageAdmin();
            pgc.setVisible(true);
            frame.dispose();
         }
      });

   }

   /** Fonction qui va créé la frame */
   private static void creationFr() {

      //Creation de la page
      JFrame frame = new JFrame("GESTION DE QUESTIONNAIRE");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      addComponentsToPane(frame, frame.getContentPane());
      frame.pack();
      frame.setSize(800,700);
      frame.setVisible(true);
   }


   public static void main()
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

      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            creationFr();
         }
      });
   }

}
