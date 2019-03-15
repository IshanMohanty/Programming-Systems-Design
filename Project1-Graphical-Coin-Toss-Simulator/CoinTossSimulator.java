// Name: Ishan Mohanty
// USC NetID: 4461-3447-18
// CS 455 PA1
// Fall 2018

/**
 * Packages used are displayed below
*/

import java.util.Random;

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * impl1ementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

public class CoinTossSimulator 
{    
   
    /**
      Constants
    */
   
    // TT- 00 (0), TH - 01 (1) , HT - 10, HH- 11 (3) [ Binary to Decimal representation ].
   
    private static final int NUM_OUTCOMES = 4;    // 4 outcomes as shown above
    private static final int TWO_TAILS = 0;
    private static final int TAIL_HEAD = 1;
    private static final int HEAD_TAIL = 2;
    private static final int TWO_HEADS = 3;			
     
    /** 
      Instance Variables Declaration 
    */

    private int cumulativeTrials;
    private int numTwoHeads;
    private int numTwoTails;
    private int numHeadTails;
        
    /**
      Creates a coin toss simulator with no trials done yet.
    */
   
    public CoinTossSimulator() 
    {
        cumulativeTrials = 0;
	    numTwoHeads = 0;
	    numTwoTails = 0;
	    numHeadTails = 0;
    }

   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them, adds these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   
    public void run(int numTrials) 
    {

        cumulativeTrials += numTrials;
	
        // Creates Random generator object
	    Random generator = new Random();
	
        
        for( int i = 0; i < numTrials ; i++)
	    {       
		    int outcome = generator.nextInt(NUM_OUTCOMES);    //generates random numbers [0,1,2,3]
		    switch(outcome)
		    {
			    case TWO_TAILS:	numTwoTails += 1;
			 		            break;
			
			    case TAIL_HEAD: numHeadTails += 1;
					            break;
			
			    case HEAD_TAIL: numHeadTails += 1;
					            break;

			    case TWO_HEADS: numTwoHeads += 1;
					            break;

			    default: System.out.println("Invalid outcome of two flips!");
		    }
	    } 
				
   }
   
   /**
      Get number of trials performed since last reset.
      @return the Cumulative trials
   */
   
   public int getNumTrials() 
   {
       return cumulativeTrials; 
   }

   /**
      Get number of trials that came up two heads since last reset.
      @return the number of double heads
   */
   
   public int getTwoHeads() 
   {
       return numTwoHeads; 
   }

   /**
     Get number of trials that came up two tails since last reset.
     @return the number of double tails
   */  
   
   public int getTwoTails() 
   {
       return numTwoTails; 
   }

   /**
     Get number of trials that came up one head and one tail since last reset.
     @return the number of head-tail & tail-head combination
   */
   
   public int getHeadTails() 
   {
       return numHeadTails; 
   }

   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   
   public void reset() 
   {
       cumulativeTrials = 0;
	   numTwoHeads = 0;
	   numTwoTails = 0;
	   numHeadTails = 0;
   }

}
