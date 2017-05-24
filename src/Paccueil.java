
import javax.swing.*;
import java.awt.*;

public class Paccueil extends JFrame
{
   static Paccueil thePaccueil;

   JPanel pnPresentation;
   ButtonGroup rbgPresentation;
   JTextArea tfBienvenue;
   JButton tbtBoutonForm;
   JButton tbtBoutonAdmin;
   JTextArea taArea0;
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
      thePaccueil = new Paccueil();
   }

   /**
    */
   public Paccueil()
   {
      super( "LES FORMUS DU TURFU" );

      pnPresentation = new JPanel();
      rbgPresentation = new ButtonGroup();
      GridBagLayout gbPresentation = new GridBagLayout();
      GridBagConstraints gbcPresentation = new GridBagConstraints();
      pnPresentation.setLayout( gbPresentation );

      tfBienvenue = new JTextArea("Bienvenue" );
      gbcPresentation.gridx = 4;
      gbcPresentation.gridy = 2;
      gbcPresentation.gridwidth = 12;
      gbcPresentation.gridheight = 3;
      gbcPresentation.fill = GridBagConstraints.BOTH;
      gbcPresentation.weightx = 1;
      gbcPresentation.weighty = 0;
      gbcPresentation.anchor = GridBagConstraints.NORTH;
      gbPresentation.setConstraints( tfBienvenue, gbcPresentation );
      pnPresentation.add( tfBienvenue );

      tbtBoutonForm = new JButton( "Répondre à un formulaire"  );
      tbtBoutonForm.setActionCommand("form");

      rbgPresentation.add( tbtBoutonForm );
      gbcPresentation.gridx = 2;
      gbcPresentation.gridy = 15;
      gbcPresentation.gridwidth = 7;
      gbcPresentation.gridheight = 3;
      gbcPresentation.fill = GridBagConstraints.BOTH;
      gbcPresentation.weightx = 1;
      gbcPresentation.weighty = 0;
      gbcPresentation.anchor = GridBagConstraints.NORTH;
      gbPresentation.setConstraints( tbtBoutonForm, gbcPresentation );
      pnPresentation.add( tbtBoutonForm );

      tbtBoutonAdmin = new JButton( "Accéder à la partie administrateur"  );
      tbtBoutonAdmin.setActionCommand("admin");

      rbgPresentation.add( tbtBoutonAdmin );
      gbcPresentation.gridx = 11;
      gbcPresentation.gridy = 15;
      gbcPresentation.gridwidth = 7;
      gbcPresentation.gridheight = 3;
      gbcPresentation.fill = GridBagConstraints.BOTH;
      gbcPresentation.weightx = 1;
      gbcPresentation.weighty = 0;
      gbcPresentation.anchor = GridBagConstraints.NORTH;
      gbPresentation.setConstraints( tbtBoutonAdmin, gbcPresentation );
      pnPresentation.add( tbtBoutonAdmin );

      JTextArea taArea0 = new JTextArea(
              "Logiciel de traitement de formulaire.\n " +
                      "Produit par moi  \n " +
                      "Réalisé par moi \n " +
                      "Pensé par moi \n"
      );
      taArea0.setFont(new Font("Serif", Font.ITALIC, 16));
      taArea0.setLineWrap(true);
      taArea0.setWrapStyleWord(true);
      gbcPresentation.gridx = 1;
      gbcPresentation.gridy = 6;
      gbcPresentation.gridwidth = 18;
      gbcPresentation.gridheight = 7;
      gbcPresentation.fill = GridBagConstraints.BOTH;
      gbcPresentation.weightx = 1;
      gbcPresentation.weighty = 1;
      gbcPresentation.anchor = GridBagConstraints.NORTH;
      gbPresentation.setConstraints( taArea0, gbcPresentation );
      pnPresentation.add( taArea0 );

      setDefaultCloseOperation( EXIT_ON_CLOSE );

      setContentPane( pnPresentation );
      pack();
      setVisible( true );


      Controller controller = new Controller();
      tbtBoutonForm.addActionListener( controller);
      tbtBoutonAdmin.addActionListener(controller);
   }
}
