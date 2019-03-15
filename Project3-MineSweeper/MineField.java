// Name: Ishan Mohanty	
// USC NetID: 4461-3447-18
// CS 455 PA3
// Fall 2018

//Import statements
import java.util.Random;

/** 
  MineField
  class with locations of mines for a game.
  This class is mutable, because we sometimes need to change it once it's created.
  mutators: populateMineField, resetEmpty
  includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

   //Instance variables declaration
   private int numRows;    //number of rows
   private int numCols;    //number of columns
   private int numMines;   //number of mines
   private static Random mineGenerator;   //stores the number of randomnly generated mines
   private boolean[][] mineDataMap;    //stores information of the mine and non-mine layout

   //Constants declaration
   private static final String ROW_CONSTANT = "ROW";     //String value for row identification
   private static final String COLUMN_CONSTANT = "COL";     //String value for column identification


   /**
     Create a minefield with same dimensions as the given array, and populate it with the mines in the array
     such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
     this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {

      //Initialize instance variables
      int mineCount=0;
      numRows = mineData.length;
      numCols = mineData[0].length;
      mineDataMap = new boolean[numRows][numCols];

      //store mine and non-mine information from mineData into mineDataMap
      for(int i=0; i<numRows; i++){
         for(int j=0; j<numCols; j++){
            mineDataMap[i][j] = mineData[i][j];
            if(mineDataMap[i][j] == true){
               mineCount++;
            }
         }
      }

      //Initialize the number of mines
      numMines = mineCount;

   }


   /**
     Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
     populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
     numMines() will not correspond to the number of mines currently in the MineField.
     @param numRows  number of rows this minefield will have, must be positive
     @param numCols  number of columns this minefield will have, must be positive
     @param numMines   number of mines this minefield will have,  once we populate it.
     PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {

      //Initialize instance variables
      this.numRows = numRows;
      this.numCols = numCols;
      this.numMines = numMines;
      mineDataMap = new boolean[numRows][numCols]; //All values are intialized to false [non-mines]

   }


   /**
     Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
     ensuring that no mine is placed at (row, col).
     @param row the row of the location to avoid placing a mine
     @param col the column of the location to avoid placing a mine
     PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {

      //Verify if location is in-range, if false assert   
      assert inRange(row,col);

      //clears all mines present and ensures fresh start to every game 
      resetEmpty();

      //reference variable to 'Random' class. Helps generate random positions for mines.
      mineGenerator = new Random();

      //populate mines in random positions
      int totalMines = numMines();
      while( totalMines!=0 ) {

         //generates random locations
         int xLoc = mineGenerator.nextInt(numRows());
         int yLoc = mineGenerator.nextInt(numCols());

         /**
           Ensures no mine is placed in (row,col) and puts mines in new places all the time
           to prevent possibility of duplicate mine positions.
          */
         if( (xLoc != row) && (yLoc != col) && (mineDataMap[xLoc][yLoc]!=true) ) {
            mineDataMap[xLoc][yLoc] = true;
            totalMines--;
         }

      }

   }


   /**
     Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
     Thus, after this call, the actual number of mines in the minefield does not match numMines().  
     Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {

      for( int x = 0; x < numRows() ; x++ ) {
         for( int y = 0; y < numCols() ; y++ ) {
            mineDataMap[x][y] = false;
         }
      }			

   }


   /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
    */
   public int numAdjacentMines(int row, int col) {

      //Verify if location is in-range, if false assert   
      assert inRange(row,col);

      //Get indexes for traversing through neighborhood
      int rowStartIndex = getIndex(row-1,ROW_CONSTANT);
      int rowEndIndex = getIndex(row+1,ROW_CONSTANT);
      int colStartIndex = getIndex(col-1,COLUMN_CONSTANT);
      int colEndIndex = getIndex(col+1,COLUMN_CONSTANT);
      int adjMineCount = 0;

      //Traverse through the neighborhood to find adjacent mine count
      for( int x = rowStartIndex ; x <= rowEndIndex ; x++ ) {
         for( int y = colStartIndex ; y <= colEndIndex ; y++ ) {

            //travereses through all locations except the given (row,col)
            if( !( (x==row) && (y==col) ) ) {

               if( hasMine(x,y) == true ) {
                  adjMineCount++;
               }

            }
         }
      }

      return adjMineCount;

   }


   /**
     Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
     start from 0.
     @param row  row of the location to consider
     @param col  column of the location to consider
     @return whether (row, col) is a valid field location
    */
   public boolean inRange( int row, int col ) {

      boolean inRangeFlag = false;

      if( ( row>=0) && (row<numRows() ) && ( col>=0 ) && ( col<numCols() ) ) {	
         inRangeFlag = true;
      }

      return inRangeFlag;

   }


   /**
     Returns the number of rows in the field.
     @return number of rows in the field
    */  
   public int numRows() {

      return numRows;   

   }


   /**
     Returns the number of cols in the field.
     @return number of cols in the field
    */    
   public int numCols() {

      return numCols;       

   }


   /**
     Returns whether there is a mine in this square
     @param row  row of the location to check
     @param col  column of the location to check
     @return whether there is a mine in this square
     PRE: inRange(row, col)     
    */    
   public boolean hasMine( int row, int col ) {

      //Verify if location is in-range, if false assert   
      assert inRange(row,col);

      boolean hasMineFlag = false;	

      if( mineDataMap[row][col] == true ) {
         hasMineFlag = true;
      }		

      return hasMineFlag;  

   }


   /**
     Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
     some of the time this value does not match the actual number of mines currently on the field.  See doc for that
     constructor, resetEmpty, and populateMineField for more details.
    * @return numMines [ total number of mines ] 
    */
   public int numMines() {

      return numMines;

   }


   /**
     Returns the row or column position depending on the location type specified [ROW or COL]
     @param position row or column of the location
     @param locType string constant specifying "ROW" or "COL" type
     @return row or column index value
    */
   private int getIndex(int position,String locType){

      //Gets row index
      if( locType.equals(ROW_CONSTANT) ){

         //If row index is out of bound, assign boundary position values

         if(position < 0){
            position = 0;
         }

         if( position > numRows()-1 ){
            position = numRows()-1;
         }

         return position;

      }

      //Gets column index 
      else{

         //If row index is out of bound, assign boundary position values

         if(position < 0){
            position = 0;
         }

         if(position > numCols()-1){
            position = numCols()-1;
         }

         return position;

      }	

   }


}

