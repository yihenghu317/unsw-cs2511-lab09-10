package application;

import java.util.Date;
import java.util.ArrayList;

public class RentalBasket implements LineItem{
	private ArrayList<LineItem> LineItems;
	private Date date;
	
	public RentalBasket() {
		LineItems = new ArrayList<LineItem>();
		date = new Date(0,0,0);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date rentaldate) {
		this.date = rentaldate;
	}

	public RentalBasket(Date rentaldate) {
		LineItems = new ArrayList<LineItem>();
		date = rentaldate;
	}
	

	
	@Override
	public double getPrice() {
		double sum = 0;
		for (LineItem item:LineItems) {
			sum += item.getPrice();	
		}
		
		if (LineItems.size() > 4) {
			sum = sum*0.9;
		}
		
		return sum;
	}
	

	
	
	public void add_Lineitem(LineItem litem) {
		LineItems.add(litem);
	}

	public void remove_Lineitem(LineItem litem) {
		LineItems.remove(litem);
	}
	
	
	
}
