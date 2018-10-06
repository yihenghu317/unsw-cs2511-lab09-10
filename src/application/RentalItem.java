package application;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;

public class RentalItem implements LineItem{
	private Movie movie;
	private String movietitle;
	private SimpleDoubleProperty price;
	private Date date;
	
	public RentalItem(Movie movie, int days, Date date) {
		this.price = new SimpleDoubleProperty(movie.getCharge(days));
		this.movie = movie;
		this.movietitle = movie.getTitle();
		this.date = date;
	}
	
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		double p = price.get();
		if (check_public_holiday()) {
			p = p*1.1;
		}
		if (movie.getPriceCode() instanceof ClassicPrice || movie.getPriceCode() instanceof RegularPrice) {
			if (check_school_holiday()) {
				p = p*0.75;
			}
		}
		return p;
	}
	
	public String getMovietitle() {
		return movietitle;
	}

	//check whether it's New year/easter 
	public boolean check_public_holiday() {
		if (date.getMonth() == 1 && date.getDate() == 1)
			return true;
		if (date.getMonth() == 3 && date.getDate() == 20)
			return true;
		return false;
	}
	
	public boolean check_school_holiday() {
		Date d1 = new Date(date.getYear(),7,7);
		Date d2 = new Date(date.getYear(),7,23);
		if (date.after(d1) && date.before(d2)) return true;
		return false;	
	}
	

}
