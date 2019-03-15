// Name: Ishan Mohanty	
// USC NetID: 4461-3447-18
// CSCI455 PA2
// Fall 2018

/**
 * Packages used are displayed below
 */
import java.util.Scanner;
import java.util.ArrayList;

/**
   class BulgarianSolitaireSimulator.   
   Simulates the Bulgarian solitaire game with or without the user input mode("-u")
   and with or without the single step mode("-s").  
   
   if user mode(-u) is applied, then user has to specify starting sequence of game in 
   a proper format according to the rules mentioned else the program generates it's own
   random sequence.  
   
   if single step(-s) is specified, then user can check the configuration of the piles after 
   each step of the game else the game runs till it reaches the end point according to
   the specified rules.  
   
   Error-checking is done to get a proper starting sequence according to the rules of the game.
 */
public class BulgarianSolitaireSimulator {
   /**
      Main method used for simulating the solitaire game.
      @param args Input specified arguments with combinations
                  1. "-u -s" [user-mode & single-step] 
                  2. "-u" [user-mode & all-output]
                  3. "-s" [random sequence & single-step]
                  4. "" [random sequence & all-output]
    */
   public static void main(String[] args) {     
      //Captures the modes for the game from the user. [Command-line arguments]
      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
      
      //Declaration of the SolitaireBoard object-reference
      SolitaireBoard trick;
      Scanner scan = new Scanner(System.in);
      
      //Checks for user or random mode and constructs  
      if( userConfig == true ){         
         /**
            Stores proper user-input after error checking & calls parameterized constructor 
            to construct the initial sequence of piles and start the game.
           */
         ArrayList<Integer> userPile = userModeInput(scan);
         trick = new SolitaireBoard(userPile);
      }
      else{
         //calls default constructor for random initialization of piles and start game.
         trick = new SolitaireBoard();         
      }
          
      //Checks for single-step or all-output mode      
      if( singleStep == true ){            
         eachRoundOutput(scan,trick);         
      }
      else{            
         allOutput(trick);          
      }        
   }
   
   /**
      Helper function to get the input from user ("-u").
      @param in Scanner object-reference that takes input from the keyboard 
      @return user-input
    */
   private static ArrayList<Integer> userModeInput(Scanner in) {        
      //Intialize a new ArrayList for user input & a flag for prompting the user 
      ArrayList<Integer> userInput = new ArrayList<Integer>();
      boolean doPromptUser = true; 
      
      System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
      System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
      System.out.println("Please enter a space-separated list of positive integers followed by newline:");      
      
      //Prompts user for input if input is incorrect according to game.
      while( doPromptUser ) {              
         boolean nonIntErrorFlag = false;
         boolean nonPositiveErrorFlag = false;  
         
         String line = in.nextLine();
         Scanner lineScanner = new Scanner(line);   
         
         //Accepts input from user and performs first-round of error-check for non-integer errors
         while( lineScanner.hasNext() ) {          
            if( !( lineScanner.hasNextInt() ) ) {   
               nonIntErrorFlag = true;
               doPromptUser = true;                
               userInput.clear();
               break;              
            }
            else{               
                doPromptUser = false;
                userInput.add( lineScanner.nextInt() );               
            }             
         }         
         
         //Call errorCheck function to perform error checking for non-positive numbers.
         nonPositiveErrorFlag = errorCheck(userInput);      
         
         /**
            Check flag states and take appropriate decision.
            To prompt again for input or accept input.
          */
         if( ( nonIntErrorFlag == false ) && ( nonPositiveErrorFlag == false) ) {
            doPromptUser = false;           
         }
         else{
            doPromptUser = true;
            userInput.clear();
            System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " +   
                               SolitaireBoard.CARD_TOTAL);
            System.out.println("Please enter a space-separated list of positive integers followed by newline:");
         }         
      }  
      
      return userInput;
   }
    
   /**
      Performs error-checking for non-positive integers.
      @param input user-input after first round of error-check  
      @return errorDecision : if there is an error due to non-positive integers or not
    */  
   private static boolean errorCheck( ArrayList<Integer> input) {   
      int sumCards = 0;
      boolean nonZeroFlag = false;
      boolean errorDecision = true; 
      
      //Getting sum of cards in pile if they are non-zero
      for( int i=0; i < input.size() ; i++ ) {
         if( input.get(i) > 0 ){
            sumCards += input.get(i);
         }
         else{        
            nonZeroFlag = true;
            break;            
         }
      } 
      
      //Checking various conditions to update the errorDecision flag.
      if( ( nonZeroFlag == false ) && ( sumCards == SolitaireBoard.CARD_TOTAL) && ( (input.size() > 0) && 
           (input.size() <= SolitaireBoard.CARD_TOTAL) )  ) {
         errorDecision = false;
      }

      return errorDecision;
   }  
   
   /**
      Displays output of each round if it is in a valid state.
      @param prompt Prompts for user to press enter/return to continue to the next step
      @param cardTrick Object-reference of SolitaireBoard for invoking the configString method
                       to obtain the pile details after each round.
    */  
   private static void eachRoundOutput(Scanner prompt, SolitaireBoard cardTrick) { 
      System.out.println("Initial configuration: "+ cardTrick.configString() );
      int step = 1;
      
      //If the game has not reached it's end then continue.
      while( !( cardTrick.isDone() ) ) {
         //Plays one round of Bulgarian Solitaire
         cardTrick.playRound();
         //Obtains result after the round in string format
         System.out.println("[" + step + "]" + " " + "Current configuration: " + cardTrick.configString() );
         System.out.print("<Type return to continue>");
         //prompts user to enter for the next round display
         prompt.nextLine();
         step++;
      } 
      
      System.out.println("Done!");     
   }
   
   /**
      Displays output of all rounds till the end of the solitaire game
      @param cardTrick Object-reference of SolitaireBoard for invoking the configString method
                       to obtain the pile details after each round.
    */  
   private static void allOutput(SolitaireBoard cardTrick){
      System.out.println("Initial configuration: "+cardTrick.configString() );
      int step = 1;
      
      //If the game has not reached it's end then continue.
      while( !( cardTrick.isDone() ) ) {
         //Plays one round of Bulgarian Solitaire
         cardTrick.playRound();
         //Obtains result after the round in string format
         System.out.println("["+step+"]"+" Current configuration: "+cardTrick.configString() );
         step++;
      }       
      
      System.out.println("Done!");     
   }
   
}
