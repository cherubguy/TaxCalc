import java.util.*;

public class TaxBracketScheme {
	private ArrayList<TaxBracket> brackets;
	private String name;
	
	public TaxBracketScheme() {
		
	}
	public TaxBracketScheme(String name) {
		this.brackets = new ArrayList<TaxBracket>();
		this.name = name;
	}
	
	public void addBracket(TaxBracket b) {
		brackets.add(b);
	}
	public ArrayList<TaxBracket> getBrackets() {
		return this.brackets;
	}
	public void setBrackets(ArrayList<TaxBracket> tb) {
		this.brackets = tb;
	}
	public void setName(String n) {
		this.name = n;
	}
	@Override
	public String toString() {
		return name;
	}
	public double calculateTax(double salary) {
		double totalTax = 0.0;
		for (int i = 0; i < brackets.size(); i ++) {
			double percent = brackets.get(i).getPercentage() * 0.01;
			double start = brackets.get(i).getStart();
			double finish = brackets.get(i).getFinish();
			boolean topBracket = brackets.get(i).getTopBracket();
			double diff;
			
			if (topBracket && salary > start) {
				diff = salary - start;
				//System.out.println("Top bracket");
				//System.out.println(salary);
				//System.out.println(start);
				//System.out.println("diff: " + diff);
			/*
			 * if your salary is in the top tax bracket, subtract
			 * the max from salary
			 */
			}else if(salary < finish){
				diff = salary - start;
				//System.out.println("diff: " + diff);
				
			/*
			 * if salary is in a lower bracket sutract start of lower
			 * bracket from salary
			 */
			}else {
				diff = finish - start;
				//System.out.println("diff: " + diff);
			/*
			 * if salary is bigger than the end of the bracket, subtract
			 * start from end and use whole bracket
			 */
			}
			
			
			if (salary > start) {
				totalTax += diff * percent;
				//System.out.println(totalTax);
			}
		}
		return totalTax;
	}
}
