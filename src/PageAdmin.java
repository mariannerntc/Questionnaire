
import javax.swing.*;
import java.awt.*;

public class PageAdmin extends JFrame 
{
static PageAdmin thePageAdmin;

JPanel pnPresentation;
JLabel lbPartieA;
JButton btCreer;
JButton btModif;
JButton btSuppr;
JButton btStat;
JButton btRet;
JButton btExi;
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
   thePageAdmin = new PageAdmin();
} 

/**
 */
public PageAdmin() 
{
   super( "GESTION DE QUESTIONNAIRE" );

   pnPresentation = new JPanel();
   GridBagLayout gbPresentation = new GridBagLayout();
   GridBagConstraints gbcPresentation = new GridBagConstraints();
   pnPresentation.setLayout( gbPresentation );

   lbPartieA = new JLabel( "Partie Administrateur"  );
   gbcPresentation.gridx = 4;
   gbcPresentation.gridy = 1;
   gbcPresentation.gridwidth = 12;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 1;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( lbPartieA, gbcPresentation );
   pnPresentation.add( lbPartieA );

   btCreer = new JButton( "Créer un nouveau Formulaire"  );
   gbcPresentation.gridx = 2;
   gbcPresentation.gridy = 5;
   gbcPresentation.gridwidth = 7;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 0;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( btCreer, gbcPresentation );
   pnPresentation.add( btCreer );

   btModif = new JButton( "Modifier un formulaire"  );
   btModif.setActionCommand( "" );
   gbcPresentation.gridx = 11;
   gbcPresentation.gridy = 5;
   gbcPresentation.gridwidth = 7;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 0;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( btModif, gbcPresentation );
   pnPresentation.add( btModif );

   btSuppr = new JButton( "Supprimer un formulaire"  );
   btSuppr.setActionCommand( "" );
   gbcPresentation.gridx = 2;
   gbcPresentation.gridy = 10;
   gbcPresentation.gridwidth = 7;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 0;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( btSuppr, gbcPresentation );
   pnPresentation.add( btSuppr );

   btStat = new JButton( "Obtenir des statistiques"  );
   btStat.setActionCommand( "" );
   gbcPresentation.gridx = 11;
   gbcPresentation.gridy = 10;
   gbcPresentation.gridwidth = 7;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 0;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( btStat, gbcPresentation );
   pnPresentation.add( btStat );

   btRet = new JButton( "Retour"  );
   btRet.setActionCommand( "" );
   gbcPresentation.gridx = 2;
   gbcPresentation.gridy = 15;
   gbcPresentation.gridwidth = 7;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 0;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( btRet, gbcPresentation );
   pnPresentation.add( btRet );

   btExi = new JButton( "Quitter le programme"  );
   btExi.setActionCommand( "" );
   gbcPresentation.gridx = 11;
   gbcPresentation.gridy = 15;
   gbcPresentation.gridwidth = 7;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 0;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( btExi, gbcPresentation );
   pnPresentation.add( btExi );

   setDefaultCloseOperation( EXIT_ON_CLOSE );

   setContentPane( pnPresentation );
   pack();
   setVisible( true );
} 
} 
