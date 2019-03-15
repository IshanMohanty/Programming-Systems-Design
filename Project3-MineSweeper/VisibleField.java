// Name: Ishan Mohanty	
// USC NetID: 4461-3447-18
// CS 455 PA3
// Fall 2018

/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to moves the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.

  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).

   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):

   // values in the range [0,8] corresponds to number of mines adjacent to this square

   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   

   // Instance variables declaration
   private MineField mineField;  //reference to MineField class which contains a map of minefield information
   private int[][] visibleMineField;   //visible minefield seen by the user
   private int numMinesLeftGuess;   //keeps track of number of mines left to guess	

   //-----------------------------------------------------------	

   /**
     Create a visible field that has the given underlying mineField.
     The initial state will have all the mines covered up, no mines guessed, and the game
     not over.
     @param mineField  the minefield to use for this VisibleField
    */
   public VisibleField(MineField mineField) {

      //Get reference to MineField class
      this.mineField = mineField;

      //Intial State: COVERED
      visibleMineField = new int[mineField.numRows()][mineField.numCols()];	
      for( int i=0 ; i < mineField.numRows() ; i++ ) {
         for( int j=0 ; j < mineField.numCols() ; j++ ) { 
            visibleMineField[i][j] = COVERED;
         }
      }

      //Intialize number of mines left to guess
      numMinesLeftGuess = mineField.numMines();	

   }


   /**
     Reset the object to its initial state (see constructor comments), using the same underlying MineField. 
    */     
   public void resetGameDisplay() {

      //reset game to intial state: COVERED
      for( int i=0 ; i < getMineField().numRows() ; i++ ) {
         for( int j=0 ; j < getMineField().numCols() ; j++ ) {
            visibleMineField[i][j] = COVERED;
         }
      }

      //reset number of mines left to guess
      numMinesLeftGuess = getMineField().numMines();	

   }


   /**
     Returns a reference to the mineField that this VisibleField "covers"
     @return the minefield
    */
   public MineField getMineField() {

      return mineField;

   }


   /**
     get the visible status of the square indicated.
     @param row  row of the square
     @param col  col of the square
     @return the status of the square at location (row, col).  See the public constants at the beginning of the class
     for the possible values that may be returned, and their meanings.
     PRE: getMineField().inRange(row, col)
    */
   public int getStatus( int row, int col ) {

      //Verify pre-condition, assert if condition fails
      assert getMineField().inRange(row,col);
      return visibleMineField[row][col];

   }


   /**
     Return the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
     or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
     be negative, if they have guessed more than the number of mines in the minefield.     
     @return the number of mines left to guess.
    */
   public int numMinesLeft() {

      return numMinesLeftGuess;

   }


   /**
     Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
     changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
     changes it to COVERED again; call on an uncovered square has no effect.  
     @param row  row of the square
     @param col  col of the square
     PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess( int row, int col ) {

      //Verify pre-condition, assert if condition fails
      assert getMineField().inRange(row,col);

      //Changes visible minefield state when square right-clicked
      if( visibleMineField[row][col] == COVERED ){
         visibleMineField[row][col] = MINE_GUESS;
         numMinesLeftGuess--;	
      }
      else if( visibleMineField[row][col] == MINE_GUESS){
         visibleMineField[row][col] = QUESTION;
         numMinesLeftGuess++;	
      }
      else if( visibleMineField[row][col] == QUESTION){
         visibleMineField[row][col] = COVERED;
      }
      else{
         return;		
      }

   }


   /**
     Uncovers this square and returns false iff you uncover a mine here.
     If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
     the neighboring area that are also not next to any mines, possibly uncovering a large region.
     Any mine-adjacent squares you reach will also be uncovered, and form 
     (possibly along with parts of the edge of the whole field) the boundary of this region.
     Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
     @param row  of the square
     @param col  of the square
     @return false   iff you uncover a mine at (row, col)
     PRE: getMineField().inRange(row, col)
   */
   public boolean uncover(int row, int col) {

      //Verify pre-condition, assert if condition fails
      assert getMineField().inRange(row,col);

      if( getMineField().hasMine(row,col) == true ) {

         /**
           Update visible minefield with exploded mine and check for wrong mine guesses
           and covered mine's that are yet to be guessed.
          */
         visibleMineField[row][col] = EXPLODED_MINE; 	

         for( int i=0 ; i < getMineField().numRows() ; i++ ) {
            for( int j=0 ; j < getMineField().numCols() ; j++) {

               if( ( visibleMineField[i][j] == MINE_GUESS ) && ( getMineField().hasMine(i,j) == false ) ) {
                  visibleMineField[i][j] = INCORRECT_GUESS;
               }

               if( ( visibleMineField[i][j] != MINE_GUESS && visibleMineField[i][j] != EXPLODED_MINE ) 
                     && ( getMineField().hasMine(i,j) == true ) ) { 
                  visibleMineField[i][j] = MINE;
               }

            }
         }		

         return false;

      }	
      else{

         //uncovers the mine and performs recursive opening of mines according to the above described conditions.
         unveil(row,col);		

         return true;

      }

   }


   /**
     Returns whether the game is over.
     @return whether game over
    */
   public boolean isGameOver() {

      boolean gameOverFlag = false; //Stores game over result
      int nonMineCount = 0;   //Counter Squares that do not contain a mine

      //Iterate over the entire visible minefield and check for some conditions
      for( int i=0 ; i < getMineField().numRows() ; i++ ) {
         for( int j=0 ; j < getMineField().numCols() ; j++ ) {

            /** 
              Checks for an exploded mine, incorrect user mine guess or mines that have not been guessed by user,
              if any one of these conditions, the game is over and the user has lost the game.
             */
            if( visibleMineField[i][j] == EXPLODED_MINE || visibleMineField[i][j] == INCORRECT_GUESS || visibleMineField[i][j] == MINE ) {
               gameOverFlag = true;
               break;
            }

            /**
              Checks for all the non-mine squares and updates the nonMineCount if it is an empty square or
              a square that is associated with an adjacent mine [ 0-8 ].
             */
            if( getMineField().hasMine(i,j) == false ) {
               if( visibleMineField[i][j] >= 0 && visibleMineField[i][j] <= 8 ) {
                  nonMineCount++;
               }
            }

         }
      }

      /**
        Checks if all the non-mine squares are filled with numbers from 0-8 signifying that all the squares have
        been opened without the user unlocking a square containing a dangerous mine.
        if the condition is satisfied then the game is over and the user hence, wins the game in the case below.
       */
      if( nonMineCount == ( ( getMineField().numRows() * getMineField().numCols() ) - getMineField().numMines() ) ){
         gameOverFlag = true;
      }

      return gameOverFlag;

   }


   /**
     Return whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
     vs. any one of the covered states).
     @param row of the square
     @param col of the square
     @return whether the square is uncovered
     PRE: getMineField().inRange(row, col)
   */
   public boolean isUncovered( int row, int col ) {

      //Verify pre-condition, assert if condition fails
      assert getMineField().inRange(row,col);

      boolean isUncovered = true;	
      if( (visibleMineField[row][col] == COVERED) || (visibleMineField[row][col] == MINE_GUESS) || (visibleMineField[row][col] == QUESTION) ) {
         isUncovered = false;
      }		
      return isUncovered;

   }


   /**
     helper function that performs recursive opening of empty squares and 
     uncovers squares that are adjacent to mines that form a closed boundary.
     Detailed description of uncovering of squares are mentioned in the 
     comment section of the "uncover" method.
     @param row of the square
     @param col of the square
   */
   private void unveil( int row, int col ) {

      //Termination case, where we check the location index range.
      if( !(getMineField().inRange(row,col)) ){
         return;
      }

      /**
        Recursive square opening case, where we open a suare when square is either covered or put under question mark 
        provided the square has no mine in it's location.
       */
      if( ( visibleMineField[row][col] == COVERED || visibleMineField[row][col] == QUESTION ) && (getMineField().hasMine(row,col)==false) ) 
      {
         //Assign the square the number of adjacent mine value. 
         visibleMineField[row][col] = getMineField().numAdjacentMines(row,col);

         //if square contains some adjacent mines, we return.
         if(visibleMineField[row][col]!=0){
            return;
         }

         //perform recursion in all 8 neighbouring directions if applicable.
         unveil(row-1,col-1);
         unveil(row-1,col);
         unveil(row-1,col+1);
         unveil(row,col-1);
         unveil(row,col+1);
         unveil(row+1,col-1);
         unveil(row+1,col);
         unveil(row+1,col+1);

      }

      //Base case, returns for uncovered squares, non-question mark and squares that have mines.
      else{

         return;

      } 

   }

}
