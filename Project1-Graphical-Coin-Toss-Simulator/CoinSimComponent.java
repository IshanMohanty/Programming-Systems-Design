// Name: Ishan Mohanty   
// USC NetID: 4461-3447-18
// CS 455 PA1
// Fall 2018

/**
 * Packages used are displayed below
 */

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * class CoinSimComponent
 * 
 * Extends JComponent, intiates simulation run to CoinTossSimulator Class 
 * and gives drawing instructions to Bar Class.
 * 
 */

public class CoinSimComponent extends JComponent
{
    /**
	  Constants 
	*/

    private static final double BAR_WIDTH_SCALE_FACTOR = 0.05;
	private static final double VERTICAL_BUFFER_SCALE_FACTOR = 0.05;

	/**
	  Instance variables
	*/

	private int totalTrials;

	private int twoHeadsCount;
	private int headTailCount;
	private int twoTailsCount;

	//probability percentage

	private int twoHeadsPercent;
	private int headTailPercent;
	private int twoTailsPercent;

	/**
	  Constructs a CoinTossSimulator object for running simulations.
	  @param numTrials the number of times the coin toss experiment
	  must be performed.
	*/

	public CoinSimComponent(int numTrials)
	{	
		CoinTossSimulator runCoinSim = new CoinTossSimulator();
		runCoinSim.run( numTrials );	// Perform the experiment and record results

		totalTrials = runCoinSim.getNumTrials();

		twoHeadsCount = runCoinSim.getTwoHeads();
		headTailCount = runCoinSim.getHeadTails();		
		twoTailsCount = runCoinSim.getTwoTails();

		twoHeadsPercent = (int)Math.round((twoHeadsCount*100.0) / (totalTrials));
		headTailPercent = (int)Math.round((headTailCount*100.0) / (totalTrials));
		twoTailsPercent = (int)Math.round((twoTailsCount*100.0) / (totalTrials));

	}

	/**
	  Gives the exact dimensions and locations of the bar objects to be drawn
	  to the Bar Class. 
	  @param g the graphics context
	*/

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;   //Casting Graphics object to Graphics2D object

		/**
		  Local variables that are to be computed and passed as argument values 
		  to the Bar Class constructor for it's respective three Bar objects.
		*/

		int barWidth = (int)Math.round(getWidth()*BAR_WIDTH_SCALE_FACTOR);  // Fixed-value 'bw'
		int width = (int)Math.round( (getWidth() - 3*barWidth) / 4.0);  //formula for width 'w' as seen in the assignment 1 under graphics
		int posTwoHeads = width;   
		int posHeadTail = 2*width + barWidth;
		int posTwoTails = 3*width + 2*barWidth;
		int verticalBuffer = (int)Math.round(getHeight()*VERTICAL_BUFFER_SCALE_FACTOR);  // 'vb'

		double scale = totalTrials / (double) (getHeight() - 2*verticalBuffer);  // 's' pixels per application unit
		int posLabel = getHeight() - verticalBuffer;

		//Constructing Bar objects by providing necessary information from above.

		Bar barTwoHeads = new Bar(posLabel,posTwoHeads,barWidth,twoHeadsCount,scale,Color.RED,"Two Heads: "+ twoHeadsCount + "(" +
				              twoHeadsPercent + "%)");

		Bar barHeadTail = new Bar(posLabel,posHeadTail,barWidth,headTailCount,scale,Color.GREEN,"A Head and a Tail: "+ headTailCount + "(" +
				              headTailPercent + "%)");

		Bar barTwoTails = new Bar(posLabel,posTwoTails,barWidth,twoTailsCount,scale,Color.BLUE,"Two Tails: "+ twoTailsCount + "(" +
				              twoTailsPercent + "%)");

		// Instructing Bar Class to handle the drawing in the JFrame.

		barTwoHeads.draw(g2);
		barHeadTail.draw(g2);
		barTwoTails.draw(g2);

	}
    
}

