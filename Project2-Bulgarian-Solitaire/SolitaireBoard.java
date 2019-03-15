// Name: Ishan Mohanty   
// USC NetID: 4461-3447-18
// CSCI455 PA2
// Fall 2018

/**
 * Packages used are displayed below
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire. You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below. Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/
public class SolitaireBoard {   
   /**
      Constants
    */   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES  
   
   /**
   
      Representation invariant:
      
      1. Range of the number of piles at any point in the game must satisfy the following condition : 
         
         0 < numPiles <= CARD_TOTAL
         
      2. Sum of the cards in every pile must be equal to CARD_TOTAL at all times during the game.
      
   */
   
   /** 
      Instance Variables Declaration 
    */   
   private int[] pilesInfo;   // Partially filled array containing piles information
   private int numPiles;   // Keeps count of changing number of piles
   private Random generator;   // Generates random numbers for each pile
   
   /**
      Creates a solitaire board with the configuration specified in piles.
      @param piles It has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */   
   public SolitaireBoard(ArrayList<Integer> piles) {   
      //Initialize piles to begin the solitaire game      
      pilesInfo = new int[CARD_TOTAL+1];   //Intialize capacity of pilesInfo to CARD_TOTAL+1 if in case numPiles=CARD_TOTAL
      numPiles = 0;
      
      //Get Piles info from ArrayList into pilesInfo Array     
      for( int i=0 ; i < piles.size() ; i++) { 
         pilesInfo[i] = piles.get(i);
      }  
      numPiles = piles.size();
      
      //Assert if the representation invariants fail 
      assert isValidSolitaireBoard();   
   }
    
   /**
      Creates a solitaire board with a random initial configuration.
   */  
   public SolitaireBoard() {
      //Initialize pilesInfo to begin the solitaire game.      
      pilesInfo = new int[CARD_TOTAL+1];   //Intialize capacity of pilesInfo to CARD_TOTAL+1 if in case numPiles=CARD_TOTAL   
      numPiles = 0;
      
      //Initialize the Random object to get a random sequence of pilesInfo.
      generator = new Random();     
      
      //Allocate piles of varying sizes till the cards remaining are zero.
      int remCards = CARD_TOTAL;      
      while( remCards != 0 ) {       
         pilesInfo[numPiles] = 1 + generator.nextInt(remCards);
         remCards -= pilesInfo[numPiles];
         numPiles++;
      }
      
      //Assert if the representation invariants fail 
      assert isValidSolitaireBoard(); 
   }
  
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */   
   public void playRound() {     
      /**
         Deduct one card from each pile and sum it up to form a new pile.
         place new pile at the end of all the existing piles.
       */      
      int newPile = 0;   
      for(int i=0; i < numPiles ; i++) { 
         if( pilesInfo[i] > 0 ){
            pilesInfo[i] -= 1;
            newPile++;
         }
      }      
      pilesInfo[numPiles] = newPile;
      numPiles++;
      
      /**
         Overwrite all the zero piles with the next nearest non-zero pile.
       */
      int zeroTargetLoc = 0;
      boolean isZeroCase = true;
      
      while( isZeroCase ) {  
         //Replace each zero pile one by one by nearest non-zero pile.
         if( pilesInfo[zeroTargetLoc] == 0 ){
            
            for(int j = zeroTargetLoc + 1 ; j < numPiles ; j++) {
               pilesInfo[j-1] = pilesInfo[j];
            }  
            numPiles--;
            pilesInfo[numPiles] = 0;         
         }   
         else{
            zeroTargetLoc++;
         }
         
         //Stopping condition, here all zero piles have been replaced.
         if( zeroTargetLoc == numPiles ){
            isZeroCase = false;
         }
         else{            
            isZeroCase = true;
         }
      }
      
      //Assert if the representation invariants fail 
      assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
      @return endGameFlag
   */   
   public boolean isDone() {
      //Assert if the representation invariants fail 
      assert isValidSolitaireBoard();
      
      /**
         Initialization of number-of-piles counter, end-of-game counter &
         number-of-piles-found counter.
       */
      int pileNumCheck = NUM_FINAL_PILES;     
      int endGameCheck = 0;
      int numPilesFound = 1; 
      
      //Initialize flags
      boolean isVerified = true;
      boolean endGameFlag = false;
      
      //Verify each pile and update flag conditions.
      while( isVerified ) {
         /**
            Search for each pile element present in the end of the game.
            Update flag conditions if element is present.
          */
         for(int i = 0; i < numPiles ; i++) {
            if( pilesInfo[i] == pileNumCheck ){
               
               endGameCheck++;
               pileNumCheck--;
               break;              
            }
         }
         
         //Check and update flag conditons for multiple runs to verify pile elements.
         if( endGameCheck == numPilesFound ) {
            isVerified = true;
            numPilesFound++;
         }
         else{
            isVerified = false;
         }
      }
      
      //Check for final flag conditions and return flag to mark end of game or not.
      if( (pileNumCheck == 0) && (endGameCheck == NUM_FINAL_PILES) ) {
         endGameFlag = true;
      }      
      
      return endGameFlag;   
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
      @return currBoardConfig [pilesInfo in String format]
   */
   public String configString() {
      //Convert integer array to string.
      String currBoardConfig = Arrays.toString(pilesInfo);
      
      /**
         Convert the string into only a space-separated list by replacing
         commas, brackets, zeros and leading & trailing spaces.
       */
      currBoardConfig = currBoardConfig.replace("[","");
      currBoardConfig = currBoardConfig.replace("]","");
      currBoardConfig = currBoardConfig.replace(",","");
      currBoardConfig = currBoardConfig.replace(" 0","");
      currBoardConfig = currBoardConfig.trim();
      
      return currBoardConfig; 
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
      @return Sends back a flag describing validity state of the
              solitaire game for that particular round
   */
   private boolean isValidSolitaireBoard() {  
      //Intialize flag state
      boolean isValidFlag = false;  
      
      //Checks for range of numPiles and updates flag.
      if( (numPiles > 0) && (numPiles <= CARD_TOTAL) ) {
         isValidFlag = true;
      }
      
      /**
        Checks for sum of cards in the various piles.
        if the sum equals CARD_TOTAL, 
        then the solitaire board data is in valid state.
       */
      if( isValidFlag == true ) { 
         int sum = 0;
         for(int i=0; i < numPiles; i++){
         sum += pilesInfo[i];
         }
         
         if( (sum == CARD_TOTAL) ) {  
            isValidFlag = true;
         }
         else{
            isValidFlag = false;
         }
      }    
      
      return isValidFlag; 
   }
   
}
