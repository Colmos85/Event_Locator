package Event_Locator;

import java.util.ArrayList;

public class Event {
	
	private Coordinate location;
	private String name;
	private ArrayList<Ticket> tickets;
	
	public Event(Coordinate location, String name){
		this.location = location;
		this.name = name;
	}
	
	
	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	

}
