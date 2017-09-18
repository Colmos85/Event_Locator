package Event_Locator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class Test {
	
	static final int MAX_COORDS = 10;
	
	static Coordinate[][] gridWorld = new Coordinate[(MAX_COORDS*2)+1][(MAX_COORDS*2)+1];
	
	static ArrayList<Event> closestEvents = new ArrayList();
	
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
		
		//perform a search in spiral pattern
		searchAlgortihm(xCoord, yCoord);

		System.out.println("Closest Events to ("+xCoord+","+yCoord+")");
		for(Event event:closestEvents)
		{
			System.out.println("\n" + event.toString() + " - $30.28, Distance " + calculateDistance(event.getLocation().getX(), event.getLocation().getY(), xCoord, yCoord));
		}
		
		
	} // End of main method
	
	/**
	 * Method to generate random events in and assign them to Locations
	 * 
	 * Note: As array cannot have negative value this method will create a mapping
	 * of the grid world to the actual coordinates
	 * i.e. gridWorld[0][0]  is at coordinates -10,-10
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
		
		// Add a couple of events hard coded first for testing
		Event newEvent1 = new Event(gridWorld[18][20], "Event 001"); //distance of 18 from 0,0
		gridWorld[18][20].setEvent(newEvent1);
		
		Event newEvent2 = new Event(gridWorld[9][10], "Event 002"); //distance of 1 from 0,0
		gridWorld[9][10].setEvent(newEvent2);
		
		Event newEvent3 = new Event(gridWorld[8][5], "Event 003"); //distance of 7 from 0,0
		gridWorld[8][5].setEvent(newEvent3);
		
		Event newEvent4 = new Event(gridWorld[10][14], "Event 004"); //distance of 4 from 0,0
		gridWorld[10][14].setEvent(newEvent4);
		
		Event newEvent5 = new Event(gridWorld[10][11], "Event 005"); //distance of 1 from 0,0
		gridWorld[10][11].setEvent(newEvent5);
		
		Event newEvent6 = new Event(gridWorld[5][10], "Event 006"); //distance of 5 from 0,0
		gridWorld[5][10].setEvent(newEvent6);
		
		Event newEvent7 = new Event(gridWorld[11][12], "Event 007"); //distance of 3 from 0,0
		gridWorld[11][12].setEvent(newEvent7);
		
		Event newEvent8 = new Event(gridWorld[0][0], "Event 008"); //distance of 20 from 0,0
		gridWorld[0][0].setEvent(newEvent8);
	}
	
	/**
	 * This method will perform a spiral search,
	 * starting with going up one, changing y coord + 1 as starting position
	 * 
	 * The spiral algorithm will alternate to add/subtract on x/y axis for 
	 * a count on the numMovements, which increments after by 1 after x and y axis has finished
	 * 
	 * @param x starting coord on the x-axis
	 * @param y starting coord on the y-axis
	 */
	public static void searchAlgortihm(int x, int y)
	{
		int dx = x;
		int dy = y;
		int numMovements = 1; // variable to indicate the amount of movements in an axis to go an one time.
		boolean signPositive = false; // alternate sign false is minus, true is positive
		
		//check starting position
		checkLocation(dx,dy, x, y);
		
		int mainCounter = 1;
		boolean cont = true;  // what defines true???? when 5 locations are found || when grid has been seearched...
		int minCoords =  MAX_COORDS+1;
		minCoords *= -1;
		
		// while - will search through all coordinates until it finds 5 events or reaches the edge of the grid size
		while((mainCounter  < (MAX_COORDS*2)+1) || (closestEvents.size() < 5)) 
		{
			
			// This loop does two iterations, first moves x-axis
			// second iteration moves y-axis.  The sign toggles
			// after each loop to control direction
			for(int i=1; i <=2; i++)
			{
				//controls how many movements in any one direction
				for(int j=1; j<=numMovements; j++)
				{
					if(i==1 && !signPositive){ // left movement
						dx-=1;
						if(dx == MAX_COORDS+1 || dx == minCoords)
							dx = (dx *= -1)-1; //reverse sign
					}else if(i==2 && !signPositive){ // down movement
						dy-=1;
						if(dy == MAX_COORDS+1 || dy == minCoords)
							dy = (dy *= -1)-1; //reverse sign
					}else if(i==1 && signPositive){ // right movement
						dx+=1;
						if(dx == MAX_COORDS+1 || dx == minCoords)
							dx = (dx *= -1)+1; //reverse sign
					}else if(i==2 && signPositive){ // up movement
						dy+=1;
						if(dy == MAX_COORDS+1 || dy == minCoords)
							dy = (dy *= -1)+1; //reverse sign
					}
					
					checkLocation(dx,dy, x, y);
					
				}	
			} // End outer for loop
			
			// increment number of moves to go in a direction
			numMovements++;
			//reverse the sign
			signPositive = !signPositive;
			// increment the counter for while loop
			mainCounter ++;
			
			// to cover the final top left search with movements to take it outside the range
			if(numMovements == (MAX_COORDS*2)+1){
				for(int j=1; j<=numMovements-1; j++)
				{
					dx-=1;//left movements
					if(dx == MAX_COORDS+1 || dx == minCoords)
						dx = (dx *= -1)-1; //reverse sign
					checkLocation(dx,dy, x, y);
				}
			}
			
		} // End of while
	}

	/**
	 * Check the 2D array location to see if it has an Event.
	 * @param dx - x coordinate for location to search
	 * @param dy - y coordinate for location to search
	 * @param x - x coordinate of users location
	 * @param y - y coordinate of users location
	 */
	public static void checkLocation(int dx, int dy, int x, int y)
	{
		// if the coordinate has an Event
		if(gridWorld[dx+MAX_COORDS][dy+MAX_COORDS].getEvent() != null){
			// As the search is a spiral then only add events until there is 5
			if(closestEvents.size() < 5)
				closestEvents.add(gridWorld[dx+MAX_COORDS][dy+MAX_COORDS].getEvent());
		}
		
	}
	
	
	/**
	 * Calculate the distance between two points and always return positive integer
	 * @param dx
	 * @param dy
	 * @param x
	 * @param y
	 * @return
	 */
	public static int calculateDistance(int dx, int dy, int x, int y){
		return Math.abs(dx-x) + Math.abs(dy-y);
	}
}
