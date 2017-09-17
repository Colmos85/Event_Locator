package Event_Locator;

import java.util.ArrayList;

/**
 * This class represents a location on the grid.
 * 
 * @author Colmos
 *
 */

public class Coordinate {
	
	private int x; // x co-ordinate
	private int y; // y co-ordinate
	
	
	Event event; // assuming this location has just one event
	ArrayList<Event> events; // Data structure for events

	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(int x, int y, Event event){
		this.x = x;
		this.y = y;
		this.event = event;
	}
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	
	public String toString(){
		return "Coordinates: "+ this.getX()+","+this.getY();
	}
	
	public void print(){
		System.out.println(this.toString());
	}
	

}
