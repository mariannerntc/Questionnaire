
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class PageReponse
{


    JCheckBox cbNon;
    JTextField tfRep;

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
     * Fonction qui est censé rechercher un questionnaire et l'afficher
     */
    public PageReponse(String nomFormulaire)
    {


        JFrame frame = new JFrame("Resizing Table");

        List<String> columns = new ArrayList<String>();
        List<String> values = new ArrayList<>();

        columns.add(nomFormulaire);//la premiere ligne correspondra au nom du formulaire

        Formulaire formu = null;

        try{
            formu = LectureQuestionnaires.lectureFormulaire(nomFormulaire);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Question[] quest = formu.getTabQuestions(); // On recupere un tableau de question
        int i;

        for (i =0; i<formu.getNbQuestions();i++) // Pour chaque question
        {
            String type = quest[i].getTyperetour(); // on determine son type de retour


            if (type.equals("o"))
            {
                values.add(i, quest[i].getIntitule()); // On recupere et affiche la question

            }else if(type.equals("f"))
            {
                System.out.println("un fermé");
            }

        }


        //Mettre chaque question dans un tableau
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
