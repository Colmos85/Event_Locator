package Event_Locator;

import java.math.BigDecimal;

public abstract class Ticket {
	
	// use big decimal to avoid the floating point issue
	protected BigDecimal price;

	public Ticket(BigDecimal price){
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
