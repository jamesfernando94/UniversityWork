import java.util.*;

/**
 * NIMM!
 * 
 * @author  Ron Grau
 * @version 1.2
 * @since 27/10/2016 
 * 
 * This game works as follows:
 * The human player picks a pile size. The program then calculates a sensible maximum number of tokens 
 * that may be taken by any player in any single turn.
 * The players take turns in grabbing tokens from the pile. Any player can take any quantity that is less or equal than the maximum. 
 * So for instance, if the maximum bound turns out as 4,  player can take 4,3,2, or 1 token(s).
 * However, throughout the game, each distinct quantity may only be taken once. In other words, one cannot repeatedly take 
 * the same quantity in several turns of the same game
 * Whoever can make the last pick wins, such that the pile is empty, or the opponent is stuck and cannot move anymore. 
 */
public class Nimm {
    static int pile = 0; // number of tokens in the pile
    static int count = 0; // current number of tokens in the pile
    static int bound = 0; // the max any player can take
    static int[] humanHistory; // previous turns of the human player
    static int[] AIHistory; // previous turns of the AI player
    static int[] mmAIhistory; // array for storing simulated takes (use this in Minimax)
    static int[] mmHUMANhistory; // array for storing simulated takes (use this in Minimax)
    static Random r = new Random(); // this one just picks a random number - we can do better than this!
    
    private static int getAIMove() {
       // try a value
       int n = r.nextInt(bound+1);
       // if invalid, try again
       // move must not: be 0 || less than tokens in the pile || have been made before
       while( n == 0 || count-n < 0 || AIHistory[n] == 1) {
          n = r.nextInt(bound+1);
       }
       // make move
       return n;
    }
    
    private static int[] cloneHistory(int[] source) {
       // crude and simple utility method for cloning an array
       // you cannot use the assignment operator between arrays to create copies (= just creates references to the first array)
       // to get a true, separate instance, we need to copy over the data
       int dest[] = new int[source.length];
       int temp;
        for(int i = 0; i < source.length; i++) {
              temp = source[i];
              dest[i] = temp;
        }
        return dest;
    }
    
    private static void calcUpperBound() {
        int temp =0;
        while(temp + bound + 1 < pile ) {
            bound++;
            temp+=bound;
        }
    }

    private static String getUserCommand() {
        System.out.print("Please enter the number of tokens in the pile, or 'q' to quit: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        return s;
    }
    
    private static void processUserCommand(String c) {
        try {
            bound = 0;
            count = Integer.parseInt(c);
            pile = count;
            calcUpperBound();
            humanHistory = new int[bound+1];
            AIHistory = new int[bound+1];
            System.out.println();
            System.out.println("The pile has " +count+ " tokens.");
            System.out.print("The maximum you may take in any turn is "+bound);
        } catch(Exception e) {
            System.out.print("Good Bye!");
            System.exit(0);
        }  
    }
    
    private static boolean playerStuck(int[] history) {
         for (int i=1;i < history.length; i++) {
                // if there is any valid move possible, go on
                if(count - i >= 0 && 1 == Math.abs(history[i]-1)) {
                   // Hint: // System.out.println("Player could take " +i+ " tokens.");
                   return false;
                }
            }
            return true;
    }
    
    private static void play(String humanName) {
        Scanner humanInput = new Scanner(System.in);
        int humanMove = 0;
        int AImove = -1;
        
        while(count > 0) {
            
            // ** HUMAN MOVE **
            // check if a move can be made at all
            if(playerStuck(humanHistory)) {
                System.out.println("\n* "+humanName+" can't move anymore. \n\n** The AI wins! **");
                break;
            }
            // a move can be made; elicit input
            System.out.print("\nHow many do you want? ");
            humanMove = humanInput.nextInt();
            
            // input validity checks
            if( humanMove < 1) {
                System.out.println("You have to take at least one token per turn.");
                continue;
            }
            if( humanMove > bound) {
                System.out.println("You can't take that many tokens! (Maximum "+bound+")");
                continue;
            }
            if( count-humanMove < 0) {
                System.out.println("There aren't that many tokens left in the pile!");
                continue;
            }
            if(humanHistory[humanMove] == 1) {
                System.out.println("You cannot take the same amount twice!");
                continue;
            }
            // move appears to  be valid; make move
            count -= humanMove;
            System.out.println("* "+humanName+" takes " + humanMove);
            // record the move in history
            humanHistory[humanMove] = 1;
            
            // terminal test for human player
            if ( count == 0 ) {
                System.out.println("\n* The pile is empty. \n\n** "+humanName+" wins! **");
                break;
            }
            
            // ** AI MOVE **
            // stuck?
            if(playerStuck(AIHistory)) {
               System.out.println("\n* The AI player can't move anymore. \n\n** "+humanName+" wins! **");
               break;
            }
            
            AImove = getAIMove();
            System.out.println("* AI takes " + AImove);
            count -= AImove;
            AIHistory[AImove] = 1;
            
            // terminal test for AI player
            if ( count == 0 ) {
                System.out.println("\n* The pile is empty. \n\n** The AI wins! **");
                break;
            }
        }
        System.out.println("\nWould you like to play again?");
        processUserCommand(getUserCommand());
        play(humanName);
    }
      
    public static void main(String[] args) {
        Scanner humanInput = new Scanner(System.in);
        System.out.println("\n**********************");
        System.out.println("Hello, welcome to Nimm!");
        System.out.print("What is your name? ");
        String humanName = humanInput.next();
        processUserCommand(getUserCommand());
        play(humanName);
    }
}