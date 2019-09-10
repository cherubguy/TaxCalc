
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import javafx.stage.Stage;

public class TaxMain extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	private Scene s1, s2;
	private GridPane g1, g2;
	private Label l1, l2, l3, l4, l5, bracketNumberLabel, l6, l7, l8, taxOutput, l9;
	private Button createNewBracketScheme, submitBrackets, completeBracket, submitButton1, submitButton2;
	private ChoiceBox<TaxBracketScheme> taxBracketScheme;
	private ChoiceBox<Integer> bracketNumbers;
	private CheckBox topBracket;
	private TextField salaryInput, numOfBrackets, schemeName, bracketStart,
						bracketFinish, bracketPercent;
	private TextArea currentBrackets;
	
	private static TaxBracketScheme defaultTaxBracketScheme;
	private static TaxBracket t1, t2, t3, t4;
	
	private ArrayList<TaxBracket> taxBracketsToReturn;
	private TaxBracketScheme newTBS;
	private TaxBracket placeholder;
	
	private double salary, taxDue;
	
	
	
	public static void main(String[] args) {
		
		t1 = new TaxBracket(0.0, 14000.0, 11.5);
		t2 = new TaxBracket(14000.0, 48000.0, 21.0);
		t3 = new TaxBracket(48000.0, 70000.0, 31.5);
		t4 = new TaxBracket(70000.0, true, 35.5);
		defaultTaxBracketScheme = new TaxBracketScheme("Default tax bracket scheme");
		defaultTaxBracketScheme.addBracket(t1);
		defaultTaxBracketScheme.addBracket(t2);
		defaultTaxBracketScheme.addBracket(t3);
		defaultTaxBracketScheme.addBracket(t4);
		TaxTest test = new TaxTest();
		test.printBrackets(defaultTaxBracketScheme);
		double testSalary = 80000.00;
		double expectedTax = 19230.00;
		double actualTax = defaultTaxBracketScheme.calculateTax(testSalary);
		if (test.assertTaxEqual(expectedTax, actualTax)) {
			System.out.println("Test for salary " + testSalary + " passed: " + expectedTax + " = " + actualTax);
		}
		testSalary = 61000.00;
		expectedTax = 12845.00;
		actualTax = defaultTaxBracketScheme.calculateTax(testSalary);
		if (test.assertTaxEqual(expectedTax, actualTax)) {
			System.out.println("Test for salary " + testSalary + " passed: " + expectedTax + " = " + actualTax);
		}
		
		TaxBracket tb1 = new TaxBracket(0.0, 12000.0, 10);
		TaxBracket tb2 = new TaxBracket(12000.0, 18000.0, 15);
		TaxBracket tb3 = new TaxBracket(18000.0, 27000.0, 20);
		TaxBracket tb4 = new TaxBracket(27000.0, true, 25);
		TaxBracketScheme newTBS = new TaxBracketScheme("New TBS");
		newTBS.addBracket(tb1);
		newTBS.addBracket(tb2);
		newTBS.addBracket(tb3);
		newTBS.addBracket(tb4);
		test.printBrackets(newTBS);
		
		testSalary = 21000.00;
		expectedTax = 2700.00;
		actualTax = newTBS.calculateTax(testSalary);
		if (test.assertTaxEqual(expectedTax, actualTax)) {
			System.out.println("Test for salary " + testSalary + " passed: " + expectedTax + " = " + actualTax);
		}
		testSalary = 35000.00;
		expectedTax = 5900.00;
		actualTax = newTBS.calculateTax(testSalary);
		if (test.assertTaxEqual(expectedTax, actualTax)) {
			System.out.println("Test for salary " + testSalary + " passed: " + expectedTax + " = " + actualTax);
		}
		
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		window = primaryStage;
		window.setTitle("Tax Calculator");
		
		g1 = new GridPane();
		g1.setPadding(new Insets(10, 10, 10, 10));
		g1.setHgap(20);
		g1.setVgap(20);
		
		g2 = new GridPane();
		g2.setPadding(new Insets(10, 10, 10, 10));
		g2.setHgap(20);
		g2.setVgap(20);
		
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		
		col1.setPercentWidth(33.333333);
		col2.setPercentWidth(33.333333);
		col3.setPercentWidth(33.333333);
		
		g1.getColumnConstraints().addAll(col1, col2, col3);
		g2.getColumnConstraints().addAll(col1, col2, col3);
		
		s1 = new Scene(g1, 600, 200);
		s2 = new Scene(g2, 600, 450);
		
		// Grid1 setup below
		
		l1 = new Label("Tax bracket scheme:");
		GridPane.setConstraints(l1, 0, 0);
		
		taxBracketScheme = new ChoiceBox<TaxBracketScheme>();
		taxBracketScheme.getItems().addAll(defaultTaxBracketScheme);
		taxBracketScheme.setValue(defaultTaxBracketScheme);
		GridPane.setConstraints(taxBracketScheme, 1, 0);
		
		createNewBracketScheme = new Button("Create New Bracket Scheme");
		createNewBracketScheme.setOnAction(this);
		GridPane.setConstraints(createNewBracketScheme, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		
		l2 = new Label("Salary:");
		GridPane.setConstraints(l2, 0, 2);
		
		salaryInput = new TextField();
		salaryInput.setOnAction(this);
		GridPane.setConstraints(salaryInput, 1, 2);
		
		submitButton1 = new Button("Submit");
		submitButton1.setOnAction(this);
		GridPane.setConstraints(submitButton1, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		l3 = new Label("Tax due:");
		GridPane.setConstraints(l3, 0, 3, 3, 1, HPos.CENTER, VPos.CENTER);
		
		taxOutput = new Label();
		GridPane.setConstraints(taxOutput, 0, 4, 3, 1, HPos.CENTER, VPos.CENTER);
		
		g1.getChildren().addAll(l1, createNewBracketScheme, taxBracketScheme,
				l2, salaryInput, submitButton1, l3, taxOutput);
		
		
		//Grid 2 setup below
		
		l4 = new Label("Scheme Name:");
		GridPane.setConstraints(l4, 0, 0);
		
		schemeName = new TextField();
		GridPane.setConstraints(schemeName, 1, 0);
		
		l5 = new Label("Enter Number of Brackets:");
		GridPane.setConstraints(l5, 0, 1);
		
		numOfBrackets = new TextField();
		GridPane.setConstraints(numOfBrackets, 1, 1);
		
		submitBrackets = new Button("Submit Structure");
		submitBrackets.setOnAction(this);
		GridPane.setConstraints(submitBrackets, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		
		bracketNumberLabel = new Label("Bracket Number:");
		bracketNumberLabel.setDisable(true);
		GridPane.setConstraints(bracketNumberLabel, 0, 2);
		
		bracketNumbers = new ChoiceBox<Integer>();
		/*bracketNumbers.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observableValue, Integer old, Integer new) {
				if 
			}
		});*/
		//bracketNumbers.getSelectionModel().getSelectedItem().addListener((ObservableValue<? extends String> observable,
		//		String oldValue, String newValue))
		bracketNumbers.setDisable(true);
		GridPane.setConstraints(bracketNumbers, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		completeBracket = new Button("Complete Bracket");
		completeBracket.setOnAction(this);
		completeBracket.setDisable(true);
		GridPane.setConstraints(completeBracket, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		l6 = new Label("Enter bracket start:");
		l6.setDisable(true);
		GridPane.setConstraints(l6, 0, 3);
		
		bracketStart = new TextField();
		bracketStart.setDisable(true);
		GridPane.setConstraints(bracketStart, 1, 3);
		
		l7 = new Label("Enter bracket finish:");
		l7.setDisable(true);
		GridPane.setConstraints(l7, 0, 4);
		
		bracketFinish = new TextField();
		bracketFinish.setDisable(true);
		GridPane.setConstraints(bracketFinish, 1, 4);
		
		topBracket = new CheckBox("No Cap");
		topBracket.setOnAction(this);
		topBracket.setDisable(true);
		GridPane.setConstraints(topBracket, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER);
		
		l8 = new Label("Enter percent:");
		l8.setDisable(true);
		GridPane.setConstraints(l8, 0, 5);
		
		bracketPercent = new TextField();
		bracketPercent.setDisable(true);
		GridPane.setConstraints(bracketPercent, 1, 5);
		
		l9 = new Label("Current Brackets:");
		l9.setDisable(true);
		GridPane.setConstraints(l9, 0, 6);
		
		currentBrackets = new TextArea("Current Brackets:");
		currentBrackets.setDisable(true);
		GridPane.setConstraints(currentBrackets, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER);
		
		submitButton2 = new Button("Submit");
		submitButton2.setOnAction(this);
		submitButton2.setDisable(true);
		GridPane.setConstraints(submitButton2, 0, 7, 3, 1, HPos.CENTER, VPos.CENTER);
		
		
		g2.getChildren().addAll(l4, schemeName, l5, numOfBrackets, submitBrackets, 
				bracketNumberLabel, bracketNumbers, completeBracket, l6, bracketStart, topBracket,
				l7, bracketFinish, l8, bracketPercent, l9, currentBrackets, submitButton2);
		
		
		window.setScene(s1);
		window.show();
		
		
		
		
	}
	
	@Override
	public void handle(ActionEvent e) {
		
		if (e.getSource() == createNewBracketScheme) {
			
			
			window.setScene(s2);
		}
		if (e.getSource() == submitButton1) {
			salary = Double.parseDouble(salaryInput.getText());
			taxDue = taxBracketScheme.getValue().calculateTax(salary);
			taxOutput.setText("Tax payable on salary " + salaryInput.getText()
							  + " amounts to " + taxDue);
			
		}
		if (e.getSource() == topBracket) {
			if (topBracket.isSelected()) {
				bracketFinish.setDisable(true);
			}else {
				bracketFinish.setDisable(false);
			}
		}
		if (e.getSource() == submitButton2) {
			// complete new TBS here
			boolean alert = false;
		
			for (TaxBracket tb : taxBracketsToReturn) {
				
				if (tb.getStart() == tb.getFinish()) {
					alert = true;
				}
			}
			if (alert) {
				AlertBox ab = new AlertBox("Alert", "Please complete all brackets before\nsubmitting.");
			}else {
				newTBS.setBrackets(taxBracketsToReturn);
				taxBracketScheme.getItems().addAll(newTBS);
				taxBracketScheme.setValue(newTBS);
				schemeName.clear();
				numOfBrackets.clear();
				bracketNumbers.getItems().clear();
				bracketStart.clear();
				bracketFinish.clear();
				topBracket.setSelected(false);
				bracketPercent.clear();
				newTBS = new TaxBracketScheme();
				taxBracketsToReturn = new ArrayList<TaxBracket>();
				/*
				 * refresh these two now because the choiceBOx now holds the reference
				 * to the custom taxScheme
				 */
				
				
				window.setScene(s1);
			}
						
		}
		if (e.getSource() == submitBrackets) {
			int bracketsAsInt = Integer.parseInt(numOfBrackets.getText());
			newTBS = new TaxBracketScheme(schemeName.getText());
			placeholder = new TaxBracket(0.0, 0.0, 0.0); // so can replace something in the list
			taxBracketsToReturn = new ArrayList<TaxBracket>(bracketsAsInt);
			for (int i = 0; i < bracketsAsInt; i ++) {
				taxBracketsToReturn.add(placeholder);
				/*
				 * need a placehoder tb here so that the
				 * array actually has a size, for some reason initialising it with a 
				 * size doesnt actually mean it has an actual size??
				 * probably a better way around this but it works for now
				 */
			}
			/*
			 * initialise tbs here, then under completeBracket, get all the brackets
			 * then put them all together under submitButton2
			 */
			
			String toSet = "";
			for (TaxBracket bracket : taxBracketsToReturn) {
				toSet += (bracket + ",\n");
			}
			currentBrackets.setDisable(false); 
			currentBrackets.setText(toSet);
			submitBrackets.setDisable(true);
			numOfBrackets.setDisable(true);
			schemeName.setDisable(true);
			l4.setDisable(true);
			l5.setDisable(true);
			l6.setDisable(false);
			l7.setDisable(false);
			l8.setDisable(false);
			l9.setDisable(false);
			
			completeBracket.setDisable(false);
			bracketStart.setDisable(false);
			bracketFinish.setDisable(false);
			topBracket.setDisable(false);
			bracketPercent.setDisable(false);
			
			for (int i = 0; i < bracketsAsInt; i ++) {
				/*
				 * add one less than the number of brackets needed, the
				 * last bracket can just be the end of the bracket before 
				 * and then onwards to inifity, it is the top bracket
				 */
				bracketNumbers.getItems().addAll(i + 1);
			}
			bracketNumberLabel.setDisable(false);
			bracketNumbers.setDisable(false);
			submitButton2.setDisable(false);
			
		}
		/*if (e.getSource() == bracketNumbers) {
			int bracketsAsInt = Integer.parseInt(numOfBrackets.getText());
			if (bracketNumbers.getValue() == bracketsAsInt) {
				bracketFinish.setDisable(true);
			}else if (bracketNumbers.getValue() != null){
				bracketFinish.setDisable(false);
			}
		}*/
		if (e.getSource() == completeBracket) {
			double start;
			double finish;
			double percent;
			TaxBracket tb;
			try {
				if (topBracket.isSelected() == false) {
					start = Double.parseDouble(bracketStart.getText());
					finish = Double.parseDouble(bracketFinish.getText());
					percent = Double.parseDouble(bracketPercent.getText());
					tb = new TaxBracket(start, finish, percent);
				}else {
					start = Double.parseDouble(bracketStart.getText());
					//finish = Double.parseDouble(bracketFinish.getText());
					percent = Double.parseDouble(bracketPercent.getText());
					tb = new TaxBracket(start, true, percent);
				}
				
				
				taxBracketsToReturn.add(bracketNumbers.getValue() - 1, tb);
				int toRemove = bracketNumbers.getValue();
				taxBracketsToReturn.remove(toRemove);
				String toSet = "";
				for (TaxBracket bracket : taxBracketsToReturn) {
					toSet += (bracket + ",\n");
				}
				currentBrackets.setText(toSet);
				
				
				//System.out.println(taxBracketsToReturn.size()); // hopefully the size stays the same
				System.out.println(taxBracketsToReturn);
			}
			catch(NumberFormatException exc) {
				AlertBox ab = new AlertBox("Alert", "Please complete all fields before\ncompleting a bracket, "
						+ "or check the 'Top Bracket' checkbox.");
			}
			//double start = Double.parseDouble(bracketStart.getText());
			//double finish = Double.parseDouble(bracketFinish.getText());
			//double percent = Double.parseDouble(bracketPercent.getText());
			
			
		}
		
	}

}
