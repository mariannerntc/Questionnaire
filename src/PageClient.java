
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PageClient extends JFrame 
{
static PageClient thePageClient;

JPanel pnPresentation;
JLabel lbPartieC;
JList lsListForm;
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
   thePageClient = new PageClient();
} 

/**
 */
public PageClient() 
{
   super( "TITLE" );

   pnPresentation = new JPanel();
   GridBagLayout gbPresentation = new GridBagLayout();
   GridBagConstraints gbcPresentation = new GridBagConstraints();
   pnPresentation.setLayout( gbPresentation );

   lbPartieC = new JLabel( "Partie Client"  );
   lbPartieC.setAlignmentY((float) 1.0);
   gbcPresentation.gridx = 4;
   gbcPresentation.gridy = 1;
   gbcPresentation.gridwidth = 12;
   gbcPresentation.gridheight = 3;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 1;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( lbPartieC, gbcPresentation );
   pnPresentation.add( lbPartieC );

   //String []dataListForm = { "Chocolate", "Ice Cream", "Apple Pie" };
   lsListForm = LectureQuestionnaires.listeFormulaires();
   lsListForm.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
         super.mouseClicked(mouseEvent);

         JList list = (JList)mouseEvent.getSource();
         String a = list.getSelectedValue().toString();

         System.out.println("string a : " +a);
      }
   });

   gbcPresentation.gridx = 4;
   gbcPresentation.gridy = 5;
   gbcPresentation.gridwidth = 12;
   gbcPresentation.gridheight = 14;
   gbcPresentation.fill = GridBagConstraints.BOTH;
   gbcPresentation.weightx = 1;
   gbcPresentation.weighty = 1;
   gbcPresentation.anchor = GridBagConstraints.NORTH;
   gbPresentation.setConstraints( lsListForm, gbcPresentation );
   pnPresentation.add( lsListForm );

   setDefaultCloseOperation( EXIT_ON_CLOSE );

   setContentPane( pnPresentation );
   pack();
   setVisible( true );
} 
} 
