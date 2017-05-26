
import javax.swing.*;
        import java.awt.*;
        import java.io.File;
        import java.io.FileNotFoundException;

public class PageReponse extends JFrame
{
    static PageReponse thePageReponse;

    JPanel pnPageReponse;

    JCheckBox cbOui;
    JCheckBox cbNon;
    JTextField tfRep;
    JTable tbTable0;
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

    }

    /**
     */
    public PageReponse(String nomFormulaire)
    {


        super( "TITLE" );
        pnPageReponse = new JPanel();
        GridBagLayout gbPageReponse = new GridBagLayout();
        GridBagConstraints gbcPageReponse = new GridBagConstraints();
        pnPageReponse.setLayout( gbPageReponse );




        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Formulaire formu = null;

        try{
            formu = LectureQuestionnaires.lectureFormulaire(nomFormulaire);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Question[] quest = formu.getTabQuestions();
        int i;

        for (i =0; i<formu.getNbQuestions();i++)
        {
            String type = quest[i].getTyperetour();
            System.out.println("Question 1:" +type);

            if (type.equals("o"))
            {


            }else if(type.equals("f"))
            {
                System.out.println("un fermé");
            }
        }
        cbOui = new JCheckBox( "Oui"  );
        gbcPageReponse.gridx = 4;
        gbcPageReponse.gridy = 5;
        gbcPageReponse.gridwidth = 1;
        gbcPageReponse.gridheight = 1;
        gbcPageReponse.fill = GridBagConstraints.BOTH;
        gbcPageReponse.weightx = 1;
        gbcPageReponse.weighty = 0;
        gbcPageReponse.anchor = GridBagConstraints.NORTH;
        gbPageReponse.setConstraints( cbOui, gbcPageReponse );
        pnPageReponse.add( cbOui );

        cbNon = new JCheckBox( "Non"  );
        gbcPageReponse.gridx = 15;
        gbcPageReponse.gridy = 5;
        gbcPageReponse.gridwidth = 1;
        gbcPageReponse.gridheight = 1;
        gbcPageReponse.fill = GridBagConstraints.BOTH;
        gbcPageReponse.weightx = 1;
        gbcPageReponse.weighty = 0;
        gbcPageReponse.anchor = GridBagConstraints.NORTH;
        gbPageReponse.setConstraints( cbNon, gbcPageReponse );
        pnPageReponse.add( cbNon );

        tfRep = new JTextField( );
        gbcPageReponse.gridx = 9;
        gbcPageReponse.gridy = 10;
        gbcPageReponse.gridwidth = 1;
        gbcPageReponse.gridheight = 1;
        gbcPageReponse.fill = GridBagConstraints.BOTH;
        gbcPageReponse.weightx = 1;
        gbcPageReponse.weighty = 0;
        gbcPageReponse.anchor = GridBagConstraints.NORTH;
        gbPageReponse.setConstraints( tfRep, gbcPageReponse );
        pnPageReponse.add( tfRep );

        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( pnPageReponse );
        pack();
        setVisible( true );
    }
}
