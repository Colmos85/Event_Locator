package Event_Locator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class Test {
	
	static final int MAX_COORDS = 10;
	static final int MIN_COORDS = -10;
	
	static Coordinate[][] gridWorld = new Coordinate[(MAX_COORDS*2)+1][(MAX_COORDS*2)+1];
	
	static int eventIDCounter;
	
	public static void main(String []args){
     
		System.out.println("***************************************");
		System.out.println("*            Event Locator            *");
		System.out.println("***************************************");
		
		generateSeedData();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nPlease enter coordinates (x,y):");
		String coords = scanner.nextLine();
		
		while(!coords.matches("([-+]?[0-9]|[-+]?10),([-+]?[0-9]|[-+]?10)"))
		{
			System.out.println("Input error - coordinates must be in the range of -10 to 10 and comma seperated! e.g. 5,5"); //{X}
			System.out.println("Please re-enter coordinates:");
			coords = scanner.nextLine();
		}
		
		int commaIndex = coords.indexOf(",");
		int xCoord = Integer.parseInt(coords.substring(0, commaIndex));
		int yCoord = Integer.parseInt(coords.substring(commaIndex+1));
		
		searchAlgortihm(xCoord, yCoord);

		
		
		
	} // End of main method
	
	/**
	 * Method to generate random events in and assign them to Locations
	 */
	public static void generateSeedData()
	{
		eventIDCounter = 1; //set counter for ID of events to 1
		// randomly generate between 20 to 50 events and  
		
		Random rand = new Random(20);
		
		// generate the grid world
		for(int i = 0; i <= (MAX_COORDS*2); i++)
		{
			for(int j = 0; j <= (MAX_COORDS*2); j++)
			{
				gridWorld[i][j] = new Coordinate(i-MAX_COORDS,j-MAX_COORDS);
			}
		}
		
		System.out.print("Example: "); // + gridWorld[10][10].toString());
		gridWorld[20][20].print();
	}
	
	/**
	 * This method will perform a spiral search,
	 * starting with going up one, changing y coord + 1 as starting position
	 * 
	 * The spiral algorithm will alternate to add/subtract on x/y axis for 
	 * a count on the numMovements, which increments after by 1 after x and y axis has finished
	 * 
	 * @param x starting coord for x
	 * @param y starting coord for y
	 */
	public static void searchAlgortihm(int x, int y)
	{
		int dx = x;
		int dy = y+1;
		int numMovements = 1; // variable to indicate the amount of movements in an axis to go an one time.
		boolean signPositive = false; // alternate sign false is minus, true is positive
		
		checkLocation(dx,dy);
		System.out.println("-------------------------");
		
		int mainCounter = 1;
		boolean cont = true;  // what defines true???? when 5 locations are found...
		while(mainCounter  < 22)//cont)
		{
			
			// This loop does two iterations, first moves x-axis
			// second iteration moves y-axis
			for(int i=1; i <=2; i++)
			{
				//controls how many movements in any one direction
				for(int j=1; j<=numMovements; j++)
				{
					if(i==1 && !signPositive)
						dx-=1;
					else if(i==2 && !signPositive)
						dy-=1;
					else if(i==1 && signPositive)
						dx+=1;
					else if(i==2 && signPositive)
						dy+=1;
					
					// if x and y axis are not outside of boundary, check location
					if((dx<10 && dy<10 ) || (dx>-10 && dy>-10))
						checkLocation(dx,dy);
				}
				System.out.println("-------------------------");
				// increment counter if on first outer loop
				if(i == 1)
					numMovements++;
			} // End outer for loop
			
			//reverse the sign
			signPositive = !signPositive;
			
			mainCounter ++;
		} // End of while
	}

	/**
	 * Check location 2D array to see if it has an Event.
	 * @param x
	 * @param y
	 */
	public static void checkLocation(int x, int y)
	{
		System.out.println("Location: "+x+","+y);
		// will need to add 10 to the x and y coords to match the 2D array
	}
}
