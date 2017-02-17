package calculator;

//doesn't work with negatives because it takes the negative as an operation and having errors
import javax.swing.*;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
						if (true) { // will later check if it contains any
									// unexpected characters

						} else {
							equationDisplay.setText(
									Double.toString(calculate(handleGrouping(tokenize(equationDisplay.getText())))));
						}
					}
				};
			}
			buttons[i].addActionListener(listen);
		}
		this.setTitle("Calculator");
	}

	private ArrayList<Token> tokenize(String equation) {
		ArrayList<Token> tokens = null;

		Token currentToken = null;
		String currentData = null;

		for (int i = 0; i < equation.length(); i++) {
			char current = equation.charAt(i);
			if (Character.isDigit(current) || current == '.') {
				if (currentToken instanceof OperationToken || currentToken instanceof GroupingToken) {
					currentToken.valueFromString(currentData);
					tokens.add(currentToken);
					currentToken = new NumberToken();
					currentData = "";
				}
				currentData += Character.toString(current);
			} else if (Character.toString(current) != "(" || Character.toString(current) != ")") {
				if (currentToken instanceof OperationToken) {
					if (Character.toString(current).equals("-")) {
						currentToken.valueFromString(currentData);
						tokens.add(currentToken);
						currentToken = new NumberToken();
						currentData = "";
					} else {
						// if it gets to here that means theres an error due to
						// something like '3+*4'
					}
				} else {
					currentToken.valueFromString(currentData);
					tokens.add(currentToken);
					currentToken = new OperationToken();
					currentData = "";
				}
				currentData += Character.toString(current);
			} else {
				currentToken.valueFromString(currentData); // doesn't need to
															// check what its
															// currently working
															// on, as grouping
															// tokens will
															// always be
															// separated into
															// two if it is
															// something like ((
															// or if its working
															// on a different
															// type
				tokens.add(currentToken);
				currentToken = new GroupingToken();
				currentData = "";
				currentData += Character.toString(current);
			}
		}

		return tokens;

	}

	private Equation handleGrouping(ArrayList<Token> tokens) {
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
	private ArrayList<Token> tokens;

	// Initialize at some point.

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	@Override
	public void valueFromString(String currentData) {
		
	}
}

// Represents a simple "parentheses"
class GroupingToken implements Token {

	@Override
	public void valueFromString(String currentData) {

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