package Event_Locator;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * The event class is associated with 1 Coordinate.
 * 
 * @author Colmos
 *
 */

public class Event {
	
	private int eventID;
	private Coordinate location;
	private String name;
	private ArrayList<Ticket> tickets;
	
	public Event(int eventID, Coordinate location, String name){
		this.eventID = eventID;
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
	
	public BigDecimal getCheapestTicket() {
		Ticket cheapestTicket = null;
		for(Ticket ticket: tickets){
			if(cheapestTicket == null)
				cheapestTicket = ticket;
			if(ticket.getPrice().compareTo(cheapestTicket.getPrice()) == -1){ //second value is bigger
				cheapestTicket = ticket;
			}
		}
		return cheapestTicket.getPrice();
	}

	
	public String toString(){
		return this.getName();
	}
	
	public void print(){
		System.out.println(this.toString());
	}

}
