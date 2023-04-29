package cwk4; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk, Lewis Turnbull
 * @version 20/10/23
 */
public class GameGUI
{
    private WIN gp = new SpaceWars("Horatio");
    private JFrame myFrame = new JFrame("Game GUI");

    private JTextArea listing = new JTextArea();
    private JScrollPane scrollPane;
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight");
    private JButton viewStateBtn = new JButton("View Game State");
    private JButton clearBtn = new JButton("Clear Screen");
    private JPanel eastPanel = new JPanel();

    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the Forces menu
        JMenu forcesMenu = new JMenu("Forces");
        menubar.add(forcesMenu);

        // List all forces menu item
        JMenuItem listForcesItem = new JMenuItem("List All Forces ");
        listForcesItem.addActionListener(new ListForcesHandler());
        forcesMenu.add(listForcesItem);

        // List ASF menu item
        JMenuItem listASFItem = new JMenuItem("List ASF");
        listASFItem.addActionListener(new ListASFHandler());
        forcesMenu.add(listASFItem);

        // Activate force menu item
        JMenuItem activateForceItem = new JMenuItem("Activate Force");
        activateForceItem.addActionListener(new ActivateForceHandler());
        forcesMenu.add(activateForceItem);

        // Recall force menu item
        JMenuItem recallForceItem = new JMenuItem("Recall Force");
        recallForceItem.addActionListener(new RecallForceHandler());
        forcesMenu.add(recallForceItem);


        // Create the Battles menu
        JMenu battlesMenu = new JMenu("Battles");
        menubar.add(battlesMenu);

        // List battles menu item
        JMenuItem listBattlesItem = new JMenuItem("List Battles");
        listBattlesItem.addActionListener(new ListBattlesHandler());
        battlesMenu.add(listBattlesItem);
    }
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        listing.setEditable(false);

        scrollPane = new JScrollPane(listing, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //Allows the listing area to be scrolled

        myFrame.add(scrollPane);

        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightBtnHandler());
        fightBtn.setVisible(true);

        eastPanel.add(viewStateBtn);
        viewStateBtn.addActionListener(new ViewStateBtnHandler());
        viewStateBtn.setVisible(true);

        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearBtnHandler());
        clearBtn.setVisible(true);
        
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }

    /**
     * Converts a code from the WIN force activation method into readable text
     * @param code the integer code returned from a WIN force activation method
     * @return a String text describing what the code value means.
     */
    private String activation(int code)
    {
        return switch (code) {
            case 0 -> "force is activated";
            case 1 -> "force is not in the UFFDock";
            case 2 -> "not enough money";
            case -1 -> "no such force";
            default -> "Error";
        };
    }

    /**
     * Converts a code from the WIN do battle method into readable text
     * @param code the integer code returned from a WIN do battle method
     * @return a String text describing what the code value means.
     */
    private String fighting(int code)
    {
        switch (code)
        {
            case 0:return "Fight won";
            case 1:return "Fight lost as no suitable force available";
            case 2:return "Fight lost on battle strength, force destroyed";
            case 3:return "fight is lost and admiral completely defeated ";
        }
        return " no such fight ";
    }

    /**
     * Sets listing to visible and its contents as a list of all forces in the UFF
     * Function is called when the 'List All Forces' menu item is clicked
     */
    private class ListForcesHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getAllForces();
            listing.setText(xx);
            
        }
    }

    /**
     * Sets listing to visible and its contents as a list of all forces in the ASF
     * Function is called when the 'List ASF' menu item is clicked
     */
    private class ListASFHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String asfDetails = gp.getASFleet();
            listing.setText(asfDetails);
        }
    }

    /**
     * Displays an input text box to the user to enter a force fleet reference,
     * attempts to activate the force with the provided force fleet reference,
     * and shows a message displaying the result of this force activation attempt
     * Function is called when the 'Activate Force' menu item is clicked.
     * Nothing further happens if the user clicks 'Cancel' on the input box.
     */
    private class ActivateForceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter the force's fleet reference: ");
            if (input == null) {
                return;
            }
            int result = gp.activateForce(input);
            JOptionPane.showMessageDialog(myFrame, activation(result));
        }
    }

    /**
     * Displays an input text box to the user to enter a force fleet reference,
     * attempts to recall the force with the provided force fleet reference,
     * and shows a message notifying the user that the program is attempting to recall the force
     * Function is called when the 'Recall Force' menu item is clicked.
     * Nothing further happens if the user clicks 'Cancel' on the input box.
     */
    private class RecallForceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter the force's fleet reference: ");
            if (input == null) {
                return;
            }
            JOptionPane.showMessageDialog(myFrame, "Recalling force...");
            /*WIN interface does not allow the recall force method to return a code, so no status about
            force recall success/failure can be output.*/
            gp.recallForce(input);
        }
    }

    /**
     * Sets listing to visible and its content to a list of all battles held
     * Function is called when the 'List Battles' menu item is clicked.
     */
    private class ListBattlesHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String battlesDetails = gp.getAllBattles();
            listing.setText(battlesDetails);
        }
    }


    /**
     * Displays an input text box to the user to enter a battle number,
     * attempts to fight this battle if the input is a valid integer,
     * and displays a message showing the outcome of this battle in text.
     * If the input provided by the user is NOT an integer, an error message is displayed and no battle is attempted
     * Function is called when the 'Fight' button is clicked.
     * Nothing further happens if the user clicks 'Cancel' on the input box.
     */
    private class FightBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int result = -1;
            String inputValue = JOptionPane.showInputDialog("Fight number ?: ");
            if (inputValue == null) {
                return;
            }
            try {
                int num = Integer.parseInt(inputValue);
                result = gp.doBattle(num);
                JOptionPane.showMessageDialog(myFrame,fighting(result));
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(myFrame, "Not a valid number.");
            }

        }
    }

    /**
     * Sets listing to visible and its contents to the state of the game
     * Function is called when the 'View Game State' button is clicked
     */
    private class ViewStateBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String stateResults = gp.toString();
            listing.setText(stateResults);
        }
    }

    /**
     * Sets listing text to an empty string, clearing it
     * Function is called when the 'Clear Screen' button is clicked
     */
    private class ClearBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setText(null);
        }
    }


    /**
     * Runs the program
     * @param args program arguments
     */
    public static void main(String[] args)
    {
        new GameGUI();
    }
}
   
