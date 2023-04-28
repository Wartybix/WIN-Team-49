package cwk4; 
import java.util.*;

/**
 * Provide a command line user interface
 * 
 * @author A.A.Marczyk
 * @version 06/10/23
 */
public class GameUI
{
    private Scanner myIn = new Scanner(System.in);

    private void playGame()
    {
        int choice;
        System.out.print("Enter admiral's name: ");
        String s = myIn.nextLine();
        WIN gp = new SpaceWars(s, "battles.txt");
        choice = 100;
        while (choice != 0 )
        {
            choice = getMenuItem();
            if (choice == 1)  //All forces
            {
                System.out.println(gp.getAllForces());
            }
            else if (choice == 2) //List all battles
            {
                System.out.println(gp.getAllBattles());
            }
            else if (choice == 3) //get Force
            {
                System.out.print("Enter Force reference: ");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                System.out.println(gp.getForceDetails(ref));
            } 
            else if (choice == 4) //activate Force
            {
                System.out.print("Enter Force reference: ");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();

                int res = gp.activateForce(ref);
                System.out.println(activation(res));
            }
            else if (choice == 5) //List ASFleet
            {
                System.out.println(gp.getASFleet());
            }
            else if (choice == 6) //engage in a battle
            {
                System.out.print("Enter battle number: ");
                try {
                    int num = myIn.nextInt();
                    int result = gp.doBattle(num);
                    System.out.println(battleTextResult(result));
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                }
            }
            
            else if (choice == 7) //recall force
            {
                System.out.print("Enter force reference: ");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                gp.recallForce(ref);
                System.out.println("Force recalled");
                System.out.println(gp.getForceDetails(ref));
                
            }
            else if (choice==8) //view game state
            {
                System.out.println(gp);
            }
            // Uncomment after task 3.5
             else if (choice == 9) // Task 3.5 only
             {
                 System.out.println("Write to file");

                 gp.saveGame("olenka.txt");
             }
             else if (choice == 10) // Task 3.5 only
             {
                 System.out.println("Restore from file");
                 WIN restoredFile = gp.restoreGame("olenka.txt");
                 if (restoredFile == null) {
                     System.out.println("Error loading saved game.");
                     continue;
                 }
                 gp = gp.restoreGame("olenka.txt");
                 System.out.println(gp.toString());
             }
        }  
        System.out.println("Thank-you");
    }
    
    private int getMenuItem()
    {   
        int choice = 100;  
        System.out.println("Main Menu");
        System.out.println("0. Quit");
        System.out.println("1. List all forces");
        System.out.println("2. List all battles"); 
        System.out.println("3. View details of a force");
        System.out.println("4. Activate a force into the Active Star  fleet");
        System.out.println("5. List forces in Active Star Fleet");
        System.out.println("6. Engage in a battle");
        System.out.println("7. Recall a force");
        System.out.println("8. View the state of the game");
        //For Task 3.5 only
         System.out.println("9. Save this game");
         System.out.println("10. Restore a game");
       
        
        while (choice < 0 || choice  > 10)
        {
            System.out.print("Enter the number of your choice: ");
            choice =  myIn.nextInt();
        }
        return choice;        
    } 
    
    private String activation(int code)
    {
        switch (code)
        {
            case 0:return "force is activated"; 
            case 1:return "force is not in the UFFDock"; 
            case 2:return "not enough money";
            case -1:return "no such force";
            default: return "Error";
        }
    }
    
    private String battleTextResult(int code) {
        return switch (code) {
            case 0 -> "Battle won.";
            case 1 -> "Battle lost: no suitable force available.";
            case 2 -> "Battle lost: enemy force was stronger than player's";
            case 3 -> "Battle lost: Admiral is defeated.";
            case -1 -> "No such battle";
            default -> "Error";
        };
    }
    
    public static void main(String[] args)
    {
        GameUI myGame = new GameUI();
        myGame.playGame();
    }
}