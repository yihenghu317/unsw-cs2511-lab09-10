package application;

public class Discount extends Additional {
	private LineItem lineitem;
	private double percent;
	
	public Discount(LineItem litem, double percent) {
		this.lineitem = litem;
		this.percent = percent;
	}
	
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		double result = lineitem.getPrice()*(1 - percent);
		return result;
	}

}
