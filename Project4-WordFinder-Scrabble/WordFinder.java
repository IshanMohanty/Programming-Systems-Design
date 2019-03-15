// Name: Ishan Mohanty	
// USC NetID: 4461-3447-18
// CSCI455 PA4
// Fall 2018

/**
 * Packages used are displayed below
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

/** 
  WordFinder
  
  class responsible for finding and displaying legal words with their
  respective scores in descending order from a given rack and thus, 
  emulating the popular "Scrabble" boardgame. 
  
  user can enter a particular dictionary from which the legal words can be
  retrieved from or the default dictionary "sowpods.txt" will be used.
 */
public class WordFinder{
   
   //Constants declaration
   private static final String STANDARD_DICTIONARY = "sowpods.txt";
   
   /**
      Main method used displaying scored legal words in descending order.
      @param args Input corresponds to [dictionaryFile], if not specified
                  default dictionary "sowpods.txt" taken.
   */
   public static void main(String[] args){
      
      //Take a decision on which Dictionary file to be used
      String fileName = "";
      if( args != null && args.length > 0 ){
         fileName = args[0];    
      }
      else{
         fileName = STANDARD_DICTIONARY;   
      }
      
      /**
         Dictionary file given to the AnagramDictionary class for
         processing the anagrams present in the given dictionary 
         and also handling exceptions if user-specified dictionary 
         file is not found.
      */   
      AnagramDictionary ad = null;   
      try{  
         ad = new AnagramDictionary(fileName);  
      }
      catch( FileNotFoundException e ){
         System.out.println("Error : The file : "+ fileName + " is not found !");
         System.out.println("Exiting application");
         return;
      }

      //Take in user-input for a rack
      System.out.println("Type . to quit.");
      Scanner in = new Scanner(System.in);
      while(true){

         System.out.print("Rack? ");
         String userInput = in.next();
         in.nextLine();

         if(".".equals(userInput)){
            break;
         }
         
         /**
            Process the given user-rack, generate subsets of the rack
            and get the anagrams of the subset compared with the given
            dictionary processed by the anagram class and store the 
            legal words.
         */
         Rack alphabetRack = new Rack(userInput);
         ArrayList<String> generatedSubsets = alphabetRack.generateAllSubsets();    
         ArrayList<String> answers = new ArrayList<String>();   
         for(String word : generatedSubsets){
            answers.addAll(ad.getAnagramsOf(word));      
         }
         
         //Maintain the score for all the legal words in a score-table
         ScoreTable scoreChart = new ScoreTable(answers);
         
         //Display the word-score pair in the descending order of their scores
         printAnswers(userInput,scoreChart.getWordScoreInfo());  

      } 
      
   }

   /**
      Helper function for displaying all the legal words in descending order
      of their scores.
      @param givenRack String-input from the user representing the rack
      @param scrabbleMap Map used for storing the words with their 
                         corresponding scores
   */  
   private static void printAnswers(String givenRack, Map<String,Integer> scrabbleMap ){
      
      //Store the map data into an arraylist for sorting purposes
      ArrayList<Map.Entry<String, Integer>> answerList = new ArrayList<Map.Entry<String, Integer>>();
      for(Map.Entry<String, Integer> record : scrabbleMap.entrySet()) {
         answerList.add(record);
      }
      //Sort the arraylist based on decreasing order of their scores
      Collections.sort( answerList, new wordScoreCompare() );
      
      //Display the information of all the word-score pairs
      System.out.println("We can make "+ answerList.size() +" words from "+"\""+ givenRack +"\"");
      if( answerList.size() > 0 ) {
         System.out.println("All of the words with their scores (sorted by score):");
         for(Map.Entry<String, Integer> entry : answerList){
            System.out.print(entry.getValue()+": ");
            System.out.println(entry.getKey());
         }
      }
      
   }
   
   /**
      wordScoreCompare
      Static inner class responsible for comparing the values of the scores of the
      respective words.
      Implements the Comparator interface and overrides compare method.
   */
   public static class wordScoreCompare implements Comparator< Map.Entry<String, Integer> > {
      
      /**
         Compares scores and enforces the descending order approach.
         @param entry1 It contains the word-score pair 
         @param entry2 It contains the word-score pair 
         @return Returns integer value
                 If VAL2 > VAL1 returns positive value
                    VAL2 < VAL1 returns negative value
                    VAL1 = VAL2 then returns value based
                                on word(KEY) comparison 
                                using compareTo                        
      */
      @Override
      public int compare( Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2){
         if( entry1.getValue() != entry2.getValue() ){
            return entry2.getValue() - entry1.getValue();
         }
         return entry1.getKey().compareTo( entry2.getKey() );
      }
      
   } 
   
}






