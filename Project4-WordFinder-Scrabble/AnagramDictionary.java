// Name: Ishan Mohanty
// USC NetID: 4461-3447-18 
// CS 455 PA4
// Fall 2018

/**
 * Packages used are displayed below
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
   //Instance Variables Declaration 
   private Map<String, ArrayList<String> > specialCaseDictionary;   //Dictionary for storing anagrams

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      
      //Creating file, scanner and specialCaseDictionary objects
      specialCaseDictionary = new HashMap< String , ArrayList<String> >();
      File dictionaryFile = new File(fileName);
      Scanner readText = new Scanner(dictionaryFile);
      
      /**
         Processing dictionary files such that all anagrams of a 
         sorted-word(key) from the dictionary are stored in the
         specialCaseDictionary that represents the details of 
         all anagrams of a sorted word from the given dictionary.
      */      
      while(readText.hasNextLine()){
         String dictionaryWord = readText.nextLine();
         String dictionaryKey = sortWord(dictionaryWord);  
         if( specialCaseDictionary.containsKey( dictionaryKey ) ){
            specialCaseDictionary.get(dictionaryKey).add(dictionaryWord);
         }
         else{
            ArrayList<String> anagramsList = new ArrayList<String>();
            anagramsList.add(dictionaryWord);
            specialCaseDictionary.put(dictionaryKey,anagramsList);
         }
      }
      
      //Close reading operation
      readText.close();
      
   }
      
              
   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      
      String searchKey = sortWord(s);
      if( specialCaseDictionary.containsKey(searchKey) ){
         return specialCaseDictionary.get(searchKey);
      }
      return new ArrayList<String>();
      
   }
   
   /**
      Helper function used for sorting a given string
      in an alphabetical order.
      @param word Given String that needs sorting
      @return Sorted string in alphabetical order
   */
   private static String sortWord(String word){
      
      char[] characterList = word.toCharArray();
      Arrays.sort(characterList);
      return (new String(characterList));
      
   }   
   
}
