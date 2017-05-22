
import javax.swing.*;
import java.awt.*;

public class Paccueil extends JFrame 
{
static Paccueil thePaccueil;

JPanel pnPresentation;
ButtonGroup rbgPresentation;
JTextField tfBienvenue;
JToggleButton tbtBoutonForm;
JToggleButton tbtBoutonAdmin;
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
   super( "TITLE" );

   pnPresentation = new JPanel();
   rbgPresentation = new ButtonGroup();
   GridBagLayout gbPresentation = new GridBagLayout();
   GridBagConstraints gbcPresentation = new GridBagConstraints();
   pnPresentation.setLayout( gbPresentation );

   tfBienvenue = new JTextField( );
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

   tbtBoutonForm = new JToggleButton( ""  );
   tbtBoutonForm.setSelected( true );
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

   tbtBoutonAdmin = new JToggleButton( ""  );
   tbtBoutonAdmin.setSelected( true );
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

   taArea0 = new JTextArea(2,10);
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
} 
} 
