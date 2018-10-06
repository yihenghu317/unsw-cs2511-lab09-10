package application;

public class Surcharge extends Additional{
	private LineItem lineitem;
	private double percent;
	
	public Surcharge(LineItem litem, double percent) {
		this.lineitem = litem;
		this.percent = percent;
	}
	
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		double result = lineitem.getPrice() * (1+ percent);
		return result;
	}
	

}
