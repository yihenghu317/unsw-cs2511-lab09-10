package application;

/*
 * Using the Replace Type Code with Strategy/State Pattern
 * Using the Replace conditional logic with Polymorphism
 * Here, as the price of the movie depends upon the classification and is subject to change, we apply the principle
 * "encapsulate what varies" and "composition".  Move the price changing behaviour to a separate class, and then
 * apply strategy/state pattern to achieve polymorphism when calculating charge.
 */

public class Movie {
	private String _title;
	private Price _price;
	
	public Movie(String title, Price priceCode) {
		_title = title;
		_price = priceCode;
	}
	
	public Price getPriceCode() {
		return _price;
	}
	
	public void setPriceCode(Price arg) {
		_price = arg;
	}
	public String getTitle (){
		return _title;
	}

	public String get_type() {
		return _price.get_type();
	}
	
	// Refactor method - refactor getCharge() and getFrequentRenterPoints to class Movie
	double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}
	
	int getFrequentRenterPoints(int daysRented) {
		
		return _price.getFrequentRenterPoints(daysRented);	
	}
	
}