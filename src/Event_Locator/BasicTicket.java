package Event_Locator;

import java.math.BigDecimal;

public class BasicTicket extends Ticket{
	
	public BasicTicket(BigDecimal price){
		super(price);
	}

	public BigDecimal getPrice()
	{
		return this.price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

}
