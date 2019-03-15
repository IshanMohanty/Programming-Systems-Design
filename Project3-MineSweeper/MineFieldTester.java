
public class MineFieldTester{

   private static boolean[][] testMineField =
   {{true, false, false, false},
      {true, false, false, false},
      {false, true, true, false},
      {false, true, false, true}};

   public static void main(String[] args){
      /*
         MineField mine = new MineField(testMineField);
         System.out.println("Number of rows "+ mine.numRows());
         System.out.println("Number of cols "+ mine.numCols());
         System.out.println("Number of mines "+ mine.numMines());
         System.out.println("Number of current mines "+ mine.currentMines());

         int neighbour = mine.numAdjacentMines(2,1);
         System.out.println("number of adjacent mines "+ neighbour);		
         boolean rangeCheck1 = mine.inRange(-1,0);
         boolean rangeCheck2 = mine.inRange(4,4);
         boolean rangeCheck3 = mine.inRange(3,3);
         System.out.println("rangeCheck1 "+ rangeCheck1);
         System.out.println("rangeCheck2 "+ rangeCheck2);
         System.out.println("rangeCheck3 "+ rangeCheck3);

         System.out.println();
         System.out.println("Has mine 0,0 "+ mine.hasMine(0,0));
         System.out.println("Has mine 0,1 "+ mine.hasMine(0,1));

         mine.resetEmpty();
         System.out.println("Number of rows "+ mine.numRows());
         System.out.println("Number of cols "+ mine.numCols());
         System.out.println("Number of mines "+ mine.numMines());
         System.out.println("Number of current mines "+ mine.currentMines());
       */

      MineField mine = new MineField(4,4,8);
      mine.populateMineField(2,2);
      for(int i=0; i<mine.numRows();i++){
         for(int j=0; j<mine.numCols();j++){
            System.out.print(mine.hasMine(i,j)+" ");
         }
         System.out.println();
      }
      System.out.println("Number of rows "+ mine.numRows());
      System.out.println("Number of cols "+ mine.numCols());
      System.out.println("Number of mines "+ mine.numMines());

      int neighbour = mine.numAdjacentMines(2,1);
      System.out.println("number of adjacent mines "+ neighbour);
      boolean rangeCheck1 = mine.inRange(-1,0);
      boolean rangeCheck2 = mine.inRange(4,4);
      boolean rangeCheck3 = mine.inRange(3,3);
      System.out.println("rangeCheck1 "+ rangeCheck1);
      System.out.println("rangeCheck2 "+ rangeCheck2);
      System.out.println("rangeCheck3 "+ rangeCheck3);

      System.out.println();
      System.out.println("Has mine 0,0 "+ mine.hasMine(0,0));
      System.out.println("Has mine 0,1 "+ mine.hasMine(0,1));

      mine.resetEmpty();
      System.out.println("Number of rows "+ mine.numRows());
      System.out.println("Number of cols "+ mine.numCols());
      System.out.println("Number of mines "+ mine.numMines());


   }

}
