package application;

public abstract class Price {
	private String type;
	private int borrowperiod;
	private double normalcharge;
	private double dailycharge;
	
	public Price(String type, int borrowperiod,double normal,double daily) {
		this.type = type;
		this.borrowperiod = borrowperiod;
		this.normalcharge = normal;
		this.dailycharge = daily;
	}
	
	public String get_type() {
		return type;
	}
	
	
	
	public int getBorrowperiod() {
		return borrowperiod;
	}

	public void setBorrowperiod(int borrowperiod) {
		this.borrowperiod = borrowperiod;
	}

	public double getNormalcharge() {
		return normalcharge;
	}

	public void setNormalcharge(double normalcharge) {
		this.normalcharge = normalcharge;
	}

	public double getDailycharge() {
		return dailycharge;
	}

	public void setDailycharge(double dailycharge) {
		this.dailycharge = dailycharge;
	}

	public double getCharge(int daysRented) {
		double result = normalcharge;
		if (daysRented > borrowperiod) {
			result += (daysRented - borrowperiod)*dailycharge;
		}
		return result;
	}
	

	
	int getFrequentRenterPoints(int daysRented){
		return 1;
	}
	
}
