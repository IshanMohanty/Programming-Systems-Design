// Name: Ishan Mohanty   
// USC NetID: 4461-3447-18
// CS 455 PA1
// Fall 2018

/**
 * Packages used are displayed below
 */

import java.util.Scanner;
import javax.swing.JFrame;

/**
 * class CoinSimViewer
 * 
 * Obtains number of trials from user and creates JFrame containing CoinSimComponent.
 * 
 */

public class CoinSimViewer
{
	public static void main(String[] args)
	{
		int numTrials = 0;	// Number of trials for the experiment

		Scanner in = new Scanner(System.in); 

		/** 
		  do-while loop for receiving the number of trials from the user.
		  Error-check is provided in this section for numbers less than 
		  or equal to zero. 
		 */

		do
		{       
			System.out.println();
			System.out.println("Enter the number of trials for the coin simulation experiment");
			numTrials = in.nextInt();
			System.out.println();			

			if( numTrials <= 0 )
			{
				System.out.println("ERROR: "+ numTrials + " is an invalid entry! Please enter a number greater than 0 ");
			}

		}
		while( numTrials <= 0 );


		/** 
		  This code section creates a Frame which holds and displays information
		  for the coin simulation experiment.		
		 */

		JFrame frame = new JFrame();
		frame.setSize(800,500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** 
		  Create a CoinSimComponent object and add it inside the JFrame created 
		  in the above section.
		 */

		CoinSimComponent component = new CoinSimComponent(numTrials);	//CoinSimComponent Constructor invoked	
		frame.add(component);
		frame.setVisible(true);
	}
}




