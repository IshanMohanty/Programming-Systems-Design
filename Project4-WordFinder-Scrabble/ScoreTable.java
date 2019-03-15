// Name: Ishan Mohanty   
// USC NetID: 4461-3447-18
// CS 455 PA4
// Fall 2018

/**
 * Packages used are displayed below
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
   Maintains a ScoreTable.
   Maps each word with it's respective score.
*/
public class ScoreTable{
   
   //Instance Variables Declaration 
   private Map<String,Integer> wordScorePair;
   
   //Constants declaration  
   private static final char LOWERCASE_A = 'a';
   private static final char UPPERCASE_A = 'A';
   //Hard-coded scores for each alphabet from A-Z or a-z
   private static final int[] SCRABBLE_SCORES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1,
                                                 1, 4, 4, 8, 4, 10};
   
   /**
      Produces a scoretable with a word-score pair
      and stores it in a particular order using a
      TreeMap.
      @param wordList Contains an ArrayList with 
                      anagrams of different subsets
   */
   public ScoreTable( ArrayList<String> wordList ){
        
        //Store the word-score details in an ordered TreeMap
        wordScorePair = new TreeMap<String, Integer>();	  
      
        //Calculate score for every word present in the word-list
		for( String word : wordList ){ 
           int score = 0;		
           for(int i=0; i < word.length() ; i++){
               if( Character.isLowerCase(word.charAt(i)) ){
                   score += SCRABBLE_SCORES[ word.charAt(i) - LOWERCASE_A ];
               }
               else{
                   score += SCRABBLE_SCORES[ word.charAt(i) - UPPERCASE_A ];
               }
		   }	 
           //Update the map
           wordScorePair.put(word,score);
        }
      
   }
   
   /**
      Getter function to get the wordscore map.
      @return returns a copy of an ordered scoretable map.
   */
   public Map<String,Integer> getWordScoreInfo(){    
      
      return new TreeMap<String,Integer>(wordScorePair);  
      
   }
   
}
