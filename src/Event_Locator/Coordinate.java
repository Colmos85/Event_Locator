package Event_Locator;

import java.util.ArrayList;

/**
 * This class represents a location on the grid.
 * 
 * @author Colmos
 *
 */

public class Location {
	
	private int x; // x co-ordinate
	private int y; // y co-ordinate
	
	
	Event event; // assuming this location has just one event
	ArrayList<Event> events; // Data structure for events
	
	
	public Location(int x, int y, Event event){
		this.x = x;
		this.y = y;
		this.event = event;
		
	}
	
	

}
