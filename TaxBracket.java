
public class TaxBracket {
	private double start, finish;
	private double percentage;
	private boolean topBracket;
	
	public TaxBracket() {
		
	}
	public TaxBracket(double start, double finish, double percentage) {
		this.start = start;
		this.finish = finish;
		this.percentage = percentage;
	}
	public TaxBracket(double start, boolean topBracket, double percentage) {
		this.start = start;
		this.topBracket = topBracket;
		this.percentage = percentage;
	}
	public double getFinish() {
		return this.finish;
	}
	public double getStart() {
		return this.start;
	}
	public double getPercentage(){
		return this.percentage;
	}
	public boolean getTopBracket() {
		return this.topBracket;
	}
	public void setStart(double s) {
		this.start = s;
	}
	public void setFinish(double f) {
		this.finish = f;
	}
	public void setPercent(double p) {
		this.percentage = p;
	}
	public void setTopBracket(boolean t) {
		this.topBracket = t;
	}
	@Override
	public String toString() {
		if (this.start == this.finish) {
			return "Placeholder Bracket";
		}else {
			return ("Bracket: " + this.start + " - " + this.finish);
		}
		
	}
}
