package calculator;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class main extends JFrame {
	List<Token> equationParts = new ArrayList<Token>();
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
						if (true) { // will later check if it only contains
									// valid characters
							equationDisplay.setText(
									(((NumberToken) (calculate(handleGrouping(tokenize(equationDisplay.getText()))))
											.get(0)).getValue()));
						} else {

						}
					}
				};
			}
			buttons[i].addActionListener(listen);
		}
		this.setTitle("Calculator");
	}

	private ArrayList<Token> tokenize(String equation) {
		ArrayList<Token> tokens = new ArrayList<Token>();

		Token currentToken = null;
		String currentData = "";

		for (int i = 0; i < equation.length(); i++) {
			char current = equation.charAt(i);
			if (Character.isDigit(current) || current == '.') {
				if (currentToken instanceof NumberToken == false) {
					if (currentToken != null) {
						if (currentToken instanceof OperationToken) {
							tokens.add(new OperationToken(currentData));
						} else {
							tokens.add(new GroupingToken(currentData));
						}
						currentToken = new NumberToken();
						currentData = "";
					} else {
						currentToken = new NumberToken();
						currentData = "";
					}
				}
				currentData += Character.toString(current);
			} else if (Character.toString(current).equals("(") == false
					&& Character.toString(current).equals(")") == false) {
				if (currentToken instanceof OperationToken) {
					if (Character.toString(current).equals("-")) {
						tokens.add(new OperationToken(currentData));
						currentToken = new NumberToken();
						currentData = "";
					} else {
						// if it gets to here that means theres an error due to
						// something like '3+*4'
					}
				} else if (currentToken == null) {
					if (Character.isDigit(current) || current == '.') {
						currentToken = new NumberToken();
					} else if (current == ')' || current == '(') {
						currentToken = new GroupingToken();
					} else {
						// Error: starts with operation or other
					}
				} else if (currentToken instanceof GroupingToken) { 
					tokens.add(new GroupingToken(currentData));
					currentToken = new OperationToken();
					currentData = "";
				} else {
					tokens.add(new NumberToken(Double.parseDouble(currentData)));
					currentToken = new OperationToken();
					currentData = "";
				}
				currentData += Character.toString(current);
			} else {
				if (currentToken instanceof GroupingToken) {
					tokens.add(new GroupingToken(currentData));
				} else if (currentToken instanceof OperationToken) {
					tokens.add(new OperationToken(currentData));
				} else if (currentToken instanceof NumberToken) {
					tokens.add(new NumberToken(Double.parseDouble(currentData)));
				}
				currentToken = new GroupingToken();
				currentData = "";
				currentData += Character.toString(current);
			}
		}
		currentToken.valueFromString(currentData);
		tokens.add(currentToken);
		return tokens;

	}

	private Equation handleGrouping(ArrayList<Token> list) {
		if (hasGroupings(list) == true) {
			ArrayList<Token> contents = new ArrayList<Token>();
			ArrayList<String> listAsStrings = new ArrayList<String>();
			for (int e = 0; e < list.size(); e++) {
				listAsStrings.add(list.get(e).getValue());
			}
			for (int i = 1; i < listAsStrings.lastIndexOf(")")-listAsStrings.indexOf("("); i++) {
				contents.add(list.get(listAsStrings.indexOf("(") + i));
			}
			for (int i = listAsStrings.indexOf("("); i <= listAsStrings.lastIndexOf(""); i++) {
				contents.remove(i);
			}
			contents = handleGrouping(contents).getTokens();
			Equation item = new Equation(contents);
			list.add(item);
		}
		return new Equation(list);
	}

	private boolean hasGroupings(ArrayList<Token> list) {
		boolean answer = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof GroupingToken) {
				answer = true;
			}
		}
		return answer;
	}

	private ArrayList<Token> calculate(Equation equation) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens = equation.getTokens();
		int doFirst = 0; // 0 none, 1 add/sub, 2 mult/div, 3 equation
		int locOfNext = 0;
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i) instanceof Equation && doFirst != 3) {
				doFirst = 3;
				System.out.println("here");
				locOfNext = i;
			} else if (tokens.get(i) instanceof OperationToken) {
				if ((((OperationToken) tokens.get(i)).getValue().equals("*")
						|| ((OperationToken) tokens.get(i)).getValue().equals("/")) && doFirst < 2) {
					if (doFirst < 2) {
						doFirst = 2;
						locOfNext = i;
					}
				} else if (doFirst == 0) {
					if (doFirst < 1) {
						doFirst = 1;
						locOfNext = i;
					}
				}
			}
		}
		if (doFirst != 3 && doFirst != 0) {
			tokens.set(locOfNext - 1,
					new NumberToken(
							preformOperation(Double.parseDouble(((NumberToken) tokens.get(locOfNext - 1)).getValue()),
									Double.parseDouble(((NumberToken) tokens.get(locOfNext + 1)).getValue()),
									((OperationToken) tokens.get(locOfNext)).getValue())));
			tokens.remove(locOfNext);
			tokens.remove(locOfNext);
			
			ArrayList<Token> temp = calculate(new Equation(tokens));
			tokens = temp;
		} else if (doFirst == 3) {
			tokens.set(locOfNext, calculate((Equation) tokens.get(locOfNext)).get(0));
			tokens = calculate(handleGrouping(tokens))//; creating infinite loop 
		}
		return tokens;
	}

	private double preformOperation(double a, double b, String operation) {
		if (operation.equals("*")) {
			return a * b;
		} else if (operation.equals("/")) {
			return a / b;
		} else if (operation.equals("+")) {
			return a + b;
		} else if (operation.equals("-")) {
			return a - b;
		} else {
			System.out.println("Error @ preformOperation");
			return 0;
		}
	}
}

interface Token {
	public void valueFromString(String currentData);

	public String getValue();
}

class NumberToken implements Token {
	private double value;

	public NumberToken(double i) {
		this.value = i;
	}

	public NumberToken() {

	}

	@Override
	public String getValue() {
		return Double.toString(this.value);
	}

	@Override
	public void valueFromString(String currentData) {
		this.value = Double.parseDouble(currentData);

	}
}

class Equation implements Token {
	private List<Token> tokens = new ArrayList<Token>();

	public Equation(ArrayList<Token> temp) {
		tokens = temp; // I think I remember about this not working as I intend
		// but I may be wrong. Something about changes to temp
		// affecting tokens as it isn't making a copy but just
		// points to the old one. If so I'll fix it later
		// EDIT: I think it should actually work but maybe not
	}

	public ArrayList<Token> getTokens() {
		return (ArrayList<Token>) tokens;
	}

	@Override
	public void valueFromString(String currentData) {
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}

class GroupingToken implements Token {
	private String value;

	public GroupingToken(String i) {
		this.value = i;
	}

	public GroupingToken() {

	}

	@Override
	public void valueFromString(String currentData) {
		this.value = currentData;
	}

	@Override
	public String getValue() {
		return this.value;
	}
}

class OperationToken implements Token {
	private String value;

	public OperationToken(String i) {
		this.value = i;
	}

	public OperationToken() {

	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public void valueFromString(String currentData) {
		this.value = currentData;
	}
}