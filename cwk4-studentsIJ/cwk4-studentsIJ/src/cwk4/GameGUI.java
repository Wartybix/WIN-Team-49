package cwk4; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
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
        
        JMenuItem listForcesItem = new JMenuItem("List All Forces ");
        listForcesItem.addActionListener(new ListForcesHandler());
        forcesMenu.add(listForcesItem);

        JMenuItem listASFItem = new JMenuItem("List ASF");
        listASFItem.addActionListener(new ListASFHandler());
        forcesMenu.add(listASFItem);

        JMenuItem activateForceItem = new JMenuItem("Activate Force");
        activateForceItem.addActionListener(new ActivateForceHandler());
        forcesMenu.add(activateForceItem);

        JMenuItem recallForceItem = new JMenuItem("Recall Force");
        recallForceItem.addActionListener(new RecallForceHandler());
        forcesMenu.add(recallForceItem);


        // Create the Battles menu
        JMenu battlesMenu = new JMenu("Battles");
        menubar.add(battlesMenu);

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
    
    private class ListForcesHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getAllForces();
            listing.setText(xx);
            
        }
    }

    private class ListASFHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String asfDetails = gp.getASFleet();
            listing.setText(asfDetails);
        }
    }

    private class ActivateForceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter the force's fleet reference: ");
            int result = gp.activateForce(input);
            JOptionPane.showMessageDialog(myFrame, activation(result));
        }
    }

    private class RecallForceHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter the force's fleet reference: ");
            JOptionPane.showMessageDialog(myFrame, "Recalling force...");
            gp.recallForce(input);
        }
    }

    private class ListBattlesHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String battlesDetails = gp.getAllBattles();
            listing.setText(battlesDetails);
        }
    }
    
    
    
    private class FightBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int result = -1;
            String inputValue = JOptionPane.showInputDialog("Fight number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.doBattle(num);
            JOptionPane.showMessageDialog(myFrame,fighting(result));
        }
    }

    private class ViewStateBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setVisible(true);
            String stateResults = gp.toString();
            listing.setText(stateResults);
        }
    }

    private class ClearBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setText(null);
        }
    }
    
 
    
    public static void main(String[] args)
    {
        new GameGUI();
    }
}
   
