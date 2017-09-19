package Event_Locator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public class Test {
	
	static final int MAX_COORDS = 10;
	
	static Coordinate[][] gridWorld = new Coordinate[(MAX_COORDS*2)+1][(MAX_COORDS*2)+1];
	
	static ArrayList<Event> closestEvents = new ArrayList<Event>();
	
	static int eventIDCounter;
	static Random rand = new Random(); // random object
	
	public static void main(String []args){
     
		System.out.println("***************************************");
		System.out.println("*            Event Locator            *");
		System.out.println("***************************************");
		
		generateSeedData();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nPlease enter coordinates (x,y):");
		String coords = scanner.nextLine();
		
		while(!coords.matches("([-+]?[0-9]|[-+]?10),([-+]?[0-9]|[-+]?10)"))
		{
			System.out.println("\nInput error - coordinates must be in the range of -10 to 10 and comma seperated! e.g. 5,5"); 
			System.out.print("Please re-enter coordinates:");
			coords = scanner.nextLine();
		}
		
		int commaIndex = coords.indexOf(",");
		int xCoord = Integer.parseInt(coords.substring(0, commaIndex));
		int yCoord = Integer.parseInt(coords.substring(commaIndex+1));
		
		//perform a search in spiral pattern
		searchAlgortihm(xCoord, yCoord);

		System.out.println("\nClosest Events to ("+xCoord+","+yCoord+")");
		for(Event event:closestEvents)
		{
			System.out.println("\n" + event.toString() + " - $" + event.getCheapestTicket() + ", Distance " + calculateDistance(event.getLocation().getX(), event.getLocation().getY(), xCoord, yCoord));
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
		
		// generate the grid world
		for(int i = 0; i <= (MAX_COORDS*2); i++)
		{
			for(int j = 0; j <= (MAX_COORDS*2); j++)
			{
				gridWorld[i][j] = new Coordinate(i-MAX_COORDS,j-MAX_COORDS);
			}
		}
		
		// Assumption that there will be 1 event to every 10 coordinates
		// grid of 21*21=441, 441/10=44
		int numOfEvents = ((MAX_COORDS*2)+1)*((MAX_COORDS*2)+1)/10;
		Event newEvent;
		int newXCoord;
		int newYCoord;
		Ticket ticket;
		ArrayList<Ticket> tickets;
		
		for(int i = 1; i <= numOfEvents; i++)
		{
			// create random coordinates for new event
			newXCoord = rand.nextInt(MAX_COORDS + 1 +MAX_COORDS) -MAX_COORDS;
			newYCoord = rand.nextInt(MAX_COORDS + 1 +MAX_COORDS) -MAX_COORDS;
			
			newEvent = new Event(i, gridWorld[newXCoord+MAX_COORDS][newYCoord+MAX_COORDS], "Event "+String.format("%03d", i)); 
			
			//create a random number of tickets between 0 and 5
			tickets = new ArrayList<Ticket>(); // clear all tickets in the arraylist
			for(int j = 0; j < 4; j++)
			{
				int num = rand.nextInt(5000 - 1000) +1000; // create price in cents, from $10 to $50 
				double price = num/100.0; // convert to double
				ticket = new BasicTicket((new BigDecimal(price)).setScale(2, RoundingMode.CEILING));
				tickets.add(ticket);
			}
			newEvent.setTickets(tickets);
				
			// set the grid coordinate to have this event
			gridWorld[newXCoord+MAX_COORDS][newYCoord+MAX_COORDS].setEvent(newEvent);
			
		}
		
		
		
		// Add a couple of events hard coded first for testing
		/*Event newEvent1 = new Event(gridWorld[18][20], "Event 001"); //distance of 18 from 0,0
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
		gridWorld[0][0].setEvent(newEvent8);*/
		
		
		
	}
	
	/**
	 * This method will perform a spiral diamond search and add the first 5 events found to the closestEvent array
	 * 
	 * This algorithm performs a search of all coordinates at a given number of movements from the user, starting with 1
	 * and incrementing after each coordinate has been checked.
	 * 
	 * @param x starting coord on the x-axis
	 * @param y starting coord on the y-axis
	 */
	public static void searchAlgortihm(int x, int y)
	{
		int dx = x;
		int dy = y;
		int numMovements = 1; // variable to indicate the distance from the user
		
		//check starting position
		checkLocation(dx,dy, x, y);

		int minCoords =  MAX_COORDS;
		minCoords *= -1; // max negative value of the grid

		// while - will search through all coordinates until it finds 5 events 
		while(numMovements < (MAX_COORDS*2)+2 && (closestEvents.size() < 5))
		{
			//first loop - 
			for(int i = 1; i <= 4; i++)
			{
				if(i == 1){ // // moving south-west
					for(int j = 0; j < numMovements; j++){
						dx = j-x;
						dx *= -1; // reverse sign
						dy = (numMovements-j)+y;
						if((dx >= minCoords) && (dy <= MAX_COORDS)) // only check the coordinates if they are on the grid
							checkLocation(dx, dy, x, y);
						/*else
							System.out.println("1 out of bounds - " + dx + "," + dy);*/
					}
				}else if(i == 2){ // moving south-east
					for(int j = 0; j < numMovements; j++){
						dx = (numMovements-j)-x;
						dx  *= -1; // change sign
						dy = j-y; 
						dy *= -1; // change sign
						if((dx >= minCoords) && (dy >= minCoords))
							checkLocation(dx, dy, x, y);
						/*else
							System.out.println("2 out of bounds - " + dx + "," + dy);*/
					}
				}else if(i == 3){ // moving north-east
					for(int j = 0; j < numMovements; j++){
						dx = j+x;
						dy = (numMovements-j)-y;
						dy *= -1;
						if((dx <= MAX_COORDS) && (dy >= minCoords))
							checkLocation(dx, dy, x, y);
						/*else
							System.out.println("3 out of bounds - " + dx + "," + dy);*/
					}
				}else if(i == 4){ // moving north-west
					for(int j = 0; j < numMovements; j++){
						dx = (numMovements-j)+x;
						dy = j+y;
						if((dx <= MAX_COORDS) && (dy <= MAX_COORDS))
							checkLocation(dx, dy, x, y);
						/*else
							System.out.println("4 out of bounds - " + dx + "," + dy);*/
					}
				}
				
			}

			// increment the number of movements, from user coordinate
			numMovements++;

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
	 * This is the Manhatan Distance
	 * @param dx - coordinate of event
	 * @param dy - coordinate of event
	 * @param x - user coordinates
	 * @param y - user coordinates
	 * @return
	 */
	public static int calculateDistance(int dx, int dy, int x, int y){
		return Math.abs(dx-x) + Math.abs(dy-y);
	}
}
