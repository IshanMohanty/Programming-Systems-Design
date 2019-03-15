// Name: Ishan Mohanty   
// USC NetID: 4461-3447-18
// CS 455 PA4
// Fall 2018

/**
 * Packages used are displayed below
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   
   //Instance variables declaration
   private char[] processedRack;   //Array for storing processed rack
   
   /**
      Processes the rack by removing non-alphabets and sorts the rack
      @param givenRack Word-Sequence of a combination of alphabets,Special-characters,
                       numbers,white-spaces etc
   */     
   public Rack(String givenRack){
      String filteredRack = givenRack.replaceAll("[^a-zA-Z0-9]", "");
      processedRack = filteredRack.toCharArray();
      Arrays.sort(processedRack);     
   }   
   
   /**
      Wrapper method.
      Calculates multiplicity and assembles a unique word.
      Calls the allSubsets recursive function to generate all the subsets
      of the given processed rack.
      @return all subsets of the indicated multiset
   */
   public ArrayList<String> generateAllSubsets(){  
      
      /**
         Updates map with the alphabet as the key and respective value depicting
         number of occurences.
      */
      Map<Character,Integer> alphabetMap = new HashMap<Character,Integer>();
      for(int k=0; k < processedRack.length ; k++){      
         if(alphabetMap.containsKey(processedRack[k]) ){
            alphabetMap.put(processedRack[k],alphabetMap.get(processedRack[k]) + 1 );
         }
         else{
            alphabetMap.put(processedRack[k],1);
         }
      }
      
      //Calculate multiplicity of each aplhabet and form unique word
      int[] multiplicity = new int[ alphabetMap.keySet().size() ];
      String uniqueRack = "";
      int k=0;
      for( Map.Entry<Character,Integer> entryPair : alphabetMap.entrySet() ){
         uniqueRack += entryPair.getKey();
         multiplicity[k] = entryPair.getValue();
         k++;
      }
      
      //Store generated subsets
      ArrayList<String> subsets = allSubsets(uniqueRack,multiplicity,0);
      
      //remove the empty-string ""
      for( int i=0; i < subsets.size() ; i++){
         if("".equals(subsets.get(i)) ){
            subsets.remove(i);
         }
      }
     
      return subsets;
      
   }
   
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
    
}
