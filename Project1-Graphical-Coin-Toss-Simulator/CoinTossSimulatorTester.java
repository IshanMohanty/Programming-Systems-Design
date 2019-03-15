// Name: Ishan Mohanty   
// USC NetID: 4461-3447-18
// CS 455 PA1
// Fall 2018

/**
 * Class CoinTossSimulatorTester
 * 
 * a Class to test the CoinTossSimulator Class.
 * 
 */

public class CoinTossSimulatorTester
{
     /** 
         Verifies the addition of the individual counts of [HH, (HT,TH), TT] with the total trials.
         @param numTwoHeads number of trials in which two heads were obtained
         @param numHeadTail number of trials in which a head and a tail were obtained
         @param numTwoTails number of trials in which two tails were obtained
         @param numTrials cumulative number of trials
     */
   
    public String verifyTrials( int numTwoHeads, int numHeadTail , int numTwoTails, int numTrials ) 
    {
	    int countTrials = numTwoHeads + numHeadTail + numTwoTails;

	    if( countTrials == numTrials )
	    {
		    return "true";
	    }
	    else
	    {
		    return "false";
	    }
    }	
    
   /**
      Tests the methods of the CoinTossSimulator class and
      records and displays results from the simulation runs.
      @param args not used
   */
   
    public static void main(String []  args)
    {
      //Object created of type CoinTossSimulator
      CoinTossSimulator simTest = new CoinTossSimulator();
       
      //Object created of type CoinTossSimulatorTester
	  CoinTossSimulatorTester verifySim = new CoinTossSimulatorTester();	
          
	  System.out.println();			
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      //Constructor invoked
       
	  System.out.println("After Constructor :");
	  System.out.println("Number of trials [exp:0]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : " +        
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),	
                                                 simTest.getNumTrials()) );

	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();

      // trials added : 1
       
	  simTest.run(1);	
	  System.out.println("After run(1) :");
	  System.out.println("Number of trials [exp:1]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );

	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // trials added : 10
       
	  simTest.run(10);	
	  System.out.println("After run(10) :");
	  System.out.println("Number of trials [exp:11]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );
	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();

      // trials added : 90
       
	  simTest.run(90);	
	  System.out.println("After run(90) :");
	  System.out.println("Number of trials [exp:101]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );
	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // trials added : 237
       
	  simTest.run(237);	
	  System.out.println("After run(237) :");
	  System.out.println("Number of trials [exp:338]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );
	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // trials added : 162
       
	  simTest.run(162);	
	  System.out.println("After run(162) :");
	  System.out.println("Number of trials [exp:500]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );
	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // trials added : 500
       
	  simTest.run(500);	
	  System.out.println("After run(500) :");
	  System.out.println("Number of trials [exp:1000]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );
	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();

      // Reset invoked , trials initialized to 0
       
	  simTest.reset();	
	  System.out.println("After reset :");
	  System.out.println("Number of trials [exp:0]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );

	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // trials added : 27
       
	  simTest.run(27);	
	  System.out.println("After run(27) :");
	  System.out.println("Number of trials [exp:27]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );

	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
       
      // trials added : 73
       
	  simTest.run(73);	
	  System.out.println("After run(73) :");
	  System.out.println("Number of trials [exp:100]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );

	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // trials added : 1000000
       
	  simTest.run(1000000);	
	  System.out.println("After run(1000000) :");
	  System.out.println("Number of trials [exp:1000100]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );
	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();
      
      // Reset invoked , trials initialized to 0
       
	  simTest.reset();	
	  System.out.println("After reset :");
	  System.out.println("Number of trials [exp:0]: " + simTest.getNumTrials() );
	  System.out.println("Two-head tosses: " + simTest.getTwoHeads() );
	  System.out.println("Two-tail tosses: " + simTest.getTwoTails() );
	  System.out.println("One-head one-tail tosses: " + simTest.getHeadTails() );
	  System.out.println("Tosses add up correctly? : "+ 
                          verifySim.verifyTrials(simTest.getTwoHeads(),simTest.getHeadTails(),simTest.getTwoTails(),				     
                                                 simTest.getNumTrials()) );

	  System.out.println();
	  System.out.println("-----------------------------------------------------");
	  System.out.println();

    }

}
