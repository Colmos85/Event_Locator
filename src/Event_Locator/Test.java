package Event_Locator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test {
	
	static final int MAX_COORDS = 10;
	static final int MIN_COORDS = -10;
	
	static int eventIDCounter;
	
	public static void main(String []args){
     
		System.out.println("***************************************");
		System.out.println("*            Event Locator            *");
		System.out.println("***************************************");
		
		generateSeedData();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nPlease enter corodinated (x,y):");
		String coords = scanner.nextLine();
		
		while(!coords.matches("([-+]?[0-9]|[-+]?10),([-+]?[0-9]|[-+]?10)"))
		{
			System.out.println("Input error - co-oodinated must be in the range of -10 to 10 and comma seperated! e.g. 5,5"); //{X}
			System.out.println("Please re-enter co-ordinates:");
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
		
		
	}
	
	
}
