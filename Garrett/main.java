// COMMENT: Before submitting "ctrl-shift-f" will format the code so it looks nice...

package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//doesn't work with negatives because it takes the negative as an operation and having errors
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class main extends JFrame {
	ArrayList<Token> equationParts = new ArrayList<Token>();
	int[] buttonX = { 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147 }; // coordinates
	int[] buttonY = { 75, 75, 75, 75, 135, 135, 135, 135, 195, 195, 195, 195, 255, 255, 255, 255, 315, 315, 315 };
	int[] buttonWidth = { 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 130 };
	String[] buttonText = { "1", "2", "3", "*", "4", "5", "6", "/", "7", "8", "9", "+", ".", "0", "\u232b", "-", "(",
			")", "=", }; // defines button text
	int i;
	JButton buttons[] = new JButton[19];

	public static void main(String[] args) {
		main w = new main();
		w.setVisible(true);
	}

	public main() {
		super();
		this.setSize(300, 400);
		this.getContentPane().setLayout(null);
		JTextField equationDisplay = new JTextField();
		equationDisplay.setBounds(7, 7, 270, 61);
		this.add(equationDisplay);
		for (i = 0; i < buttonX.length; i++) {
			JButton jButton = new javax.swing.JButton();
			jButton.setBounds(buttonX[i], buttonY[i], buttonWidth[i], 40);
			jButton.setText(buttonText[i]);
			buttons[i] = jButton;
			this.add(buttons[i]);
			ActionListener listen;
			if (i < 18 && i != 14) {
				listen = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						equationDisplay.setText(equationDisplay.getText() + ((JButton) arg0.getSource()).getText());
					}
				};
			} else if (i == 14) {
				listen = new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						equationDisplay.setText(
								equationDisplay.getText().substring(0, equationDisplay.getText().length() - 1));
					}
				};
			} else {
				listen = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// equationDisplay.setText(calculate(equationDisplay.getText()));

						// calculate(handleGrouping(tokenize(equationDisplay.getText())));
						System.out.println(tokenize(equationDisplay.getText()));
					}
				};
			}
			buttons[i].addActionListener(listen);

		}
		this.setTitle("Calculator");
	}

	private ArrayList<Token> tokenize(String equation) { // go through string
															// equation and make
															// it into tokens
		ArrayList<Token> tokens = null;

		String operands = "+-*/";
		String numbers = "0123456789"; // this might have to include an _ for
										// negitive numbers
		String lastToken = ""; // use for negiive check later
		String currentToken = "";

		for (int i = 0; i < equation.length() - 1; i++) {
			String currentIndex = Character.toString(equation.charAt(i));
			if (operands.contains(currentIndex)) {
				tokens.add(new NumberToken(Float.parseFloat(currentToken)));
				tokens.add(new OperationToken(currentIndex));
			}
			if (numbers.contains(currentIndex) || currentIndex == (".")) {
				currentToken += currentIndex;
			}
		}
		return tokens;
	}

	/*
	 * // This is the basic idea. private List<Token> tokenize(String equation)
	 * List<Token> tokens = null;
	 * 
	 * Token currentToken = null; String currentData = null;
	 * 
	 * for (int i = 0; i < equation.length(); i++) { char current =
	 * equation.charAt(i);
	 * 
	 * // The concept is thus:
	 * 
	 * // 1. The current character is different than what the current token //
	 * should be. // EX: currentToken is a Number // EX: and currentData =
	 * 1.93234 // if the new 'current' char is '+' we should assume the number
	 * // token is done.
	 * 
	 * // 2. Then we create the token and put it in a list of tokens.
	 * 
	 * if (Character.isDigit(current) || current == '.') { if (currentToken
	 * instanceof OperationToken || currentToken instanceof GroupingToken) {
	 * currentToken.valueFromString(currentData); tokens.add(currentToken);
	 * currentToken = new NumberToken(); currentData = ""; } currentData +=
	 * Character.toString(current); }
	 * 
	 * if (true) { // Current digit is an operator // Last token was an operator
	 * if (tokens.get(tokens.size() - 1) instanceof OperationToken) { // Handle
	 * minus sign here. } else if (currentToken instanceof NumberToken ||
	 * currentToken instanceof GroupingToken) {
	 * currentToken.valueFromString(currentData); tokens.add(currentToken);
	 * currentToken = new OperationToken(); currentData = ""; } currentData +=
	 * Character.toString(current); }
	 * 
	 * if (true) { // Current digit is grouping token if (currentToken
	 * instanceof NumberToken || currentToken instanceof OperationToken) {
	 * currentToken.valueFromString(currentData); tokens.add(currentToken);
	 * currentToken = new GroupingToken(); currentData = ""; } currentData +=
	 * Character.toString(current); } }
	 * 
	 * return tokens;
	 * 
	 * }
	 */

	private Equation handleGrouping(List<Token> tokens) {
		// Find which tokens go together....
		// Add an "equation token" which groups together other tokens.
		return null;
	}

	private double calculate(Equation equation) {
		// Go through all the tokens in the equation.
		return 0; // answer;
	}
}

interface Token {
	public void valueFromString(String currentData);
}

class NumberToken implements Token {
	private double value;

	public NumberToken(double i) {
		this.value = i;
	}

	public NumberToken() {

	}

	public double getValue() {
		return this.value;
	}

	@Override
	public void valueFromString(String currentData) {
		// Get number from string of data.

	}
}

// Represents everything inside a parentheses
class Equation implements Token {
	private List<Token> tokens;

	// Initialize at some point.

	public List<Token> getTokens() {
		return tokens;
	}

	@Override
	public void valueFromString(String currentData) {
		// TODO Auto-generated method stub

	}
}

// Represents a simple "parentheses"
class GroupingToken implements Token {

	@Override
	public void valueFromString(String currentData) {
		// TODO Auto-generated method stub

	}
}

class OperationToken implements Token {
	private String value;

	public OperationToken(String i) {
		this.value = i;
	}

	public OperationToken() {

	}

	public String getValue() {
		return this.value;
	}

	@Override
	public void valueFromString(String currentData) {
		// TODO Auto-generated method stub

	}
}