// Name: Ishan Mohanty
// USC NetID: 4461-3447-18
// CS 455 PA1
// Fall 2018

/**
 * Packages used are displayed below
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */

public class Bar
{
    /**
	  Constants
	*/	
   
    private static final double CENTER_LABEL_FACTOR = 4.0;
   
	/**
	  Instance Variables
	*/	

    private int locLabelY;
	private int locBarX;
	private int widthRectBar;
	private int heightBar;
	private double pixPerUnit;
	private Color colorBar;
	private String labelBar;	


	/**
	  Creates a labeled bar.  You give the height of the bar in application
	  units (e.g., population of a particular state), and then a scale for how
	  tall to display it on the screen (parameter scale). 

	  @param bottom  location of the bottom of the label
	  @param left  location of the left side of the bar
	  @param width  width of the bar (in pixels)
	  @param barHeight  height of the bar in application units
	  @param pixPerUnit  how many pixels per application unit
	  @param color  the color of the bar
	  @param label  the label at the bottom of the bar
	 */

	public Bar(int bottom, int left, int width, int barHeight,
			   double scale, Color color, String label)
    {

		locLabelY = bottom;
		locBarX = left;
		widthRectBar = width;
		heightBar = barHeight;
		pixPerUnit = scale;
		colorBar = color;
		labelBar = label;		 	

	}

	/**
	  Draw the labeled bar. 
	  @param g2  the graphics context
	*/
   
	public void draw(Graphics2D g2)
    {
       
		/**
          Code snippet given in assignment. 
          To get font info and find label dimensions
        */
       
        //-----------------------------------------------------------------
       
		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D labelBounds = font.getStringBounds(labelBar, context);
		int widthOfLabel  = (int)Math.round(labelBounds.getWidth());
		int heightOfLabel = (int)Math.round(labelBounds.getHeight());
        
        //-----------------------------------------------------------------
       
		int heightRectBar = (int)Math.round(heightBar/pixPerUnit);
        int locBarY = locLabelY - heightOfLabel - heightRectBar; // y-coordinate of rectangle
        
        // Rectangle(x,y,width,height) 
		Rectangle rectangularBar = new Rectangle(locBarX,locBarY,widthRectBar,heightRectBar);
		
        // Rectangular Bar of specified color drawn.       
		g2.setColor(colorBar);
		g2.fill(rectangularBar); 
               
        // Center the Label through trial & Error 
        int locLabelX = locBarX - (int)Math.round(widthOfLabel/CENTER_LABEL_FACTOR); 
        
        // Black color string drawn at specified location.
		g2.setColor(Color.BLACK);
        g2.drawString(labelBar,locLabelX,locLabelY);

	}
}
