
public class TaxTest {
	
	public TaxTest() {
		
	}
	
	public boolean assertTaxEqual(double expected, double actual) {
		if (expected == actual) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean assertTaxNotEqual(double expected, double actual) {
		if (expected == actual) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean assertSalaryInBracket(double salary, TaxBracket tb) {
		if (tb.getFinish() > salary) {
			return true;
		}else {
			return false;
		}
	}
	public void printBrackets(TaxBracketScheme tbs) {
		System.out.println("===========================");
		System.out.println("Bracket Scheme = " + tbs);
		for (int i = 0; i < tbs.getBrackets().size(); i ++) {
			if (tbs.getBrackets().get(i).getTopBracket()) {
				System.out.println("Bracket number " + i + ": ");
				System.out.println("Start = " + tbs.getBrackets().get(i).getStart() +
						", Finish = N/A");
			}else {
				System.out.println("Bracket number " + i + ": ");
				System.out.println("Start = " + tbs.getBrackets().get(i).getStart() +
						", Finish = " + tbs.getBrackets().get(i).getFinish());
			}
		}
		System.out.println();
	}
}
