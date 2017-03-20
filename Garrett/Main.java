package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main extends JFrame {
	ArrayList<Token> equationParts = new ArrayList<Token>();
	int[] buttonX = { 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147 };
	int[] buttonY = { 75, 75, 75, 75, 135, 135, 135, 135, 195, 195, 195, 195, 255, 255, 255, 255, 315, 315, 315 };
	int[] buttonWidth = { 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 130 };
	String[] buttonText = { "1", "2", "3", "*", "4", "5", "6", "/", "7", "8", "9", "+", ".", "0", "Del", "-", "(", ")",
			"=", }; // defines button text
	int i;
	JButton buttons[] = new JButton[19];

	public static void main(String[] args) {
		Main w = new Main();
		w.setVisible(true);
	}

	public Main() {
		super();
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					public void actionPerformed(ActionEvent arg0) { // enter key
						List tokens = new ArrayList<Token>();
						tokens = solve(tokenize(equationDisplay.getText()));
						NumberToken t = new NumberToken();
						t = (NumberToken) tokens.get(0);
						System.out.println(t.getValue());
						equationDisplay.setText(t.getValue());
					}
				};
			}
			buttons[i].addActionListener(listen);

		}
		this.setTitle("Calculator");
	}

	private List<Token> solve(List<Token> tokens) {

		boolean hasGrouping = false;

		do {
			hasGrouping = false;
			for (int i = 0; i < tokens.size(); i++) {
				Token t = (Token) tokens.get(i);
				if (t instanceof GroupingToken) {
					hasGrouping = true;
				}
			}

			if (hasGrouping) {
				tokens = handleGrouping(tokens);
			}

		} while (hasGrouping);

		// solves equation need order of operations
		System.out.println("in solve");

		int Operation = 0; // 0 = mult, 1 = divide, 2 = add, 3 = sub

		while (tokens.size() > 1) {

			for (int i = 0; i < tokens.size(); i++) {

				Token T = (Token) tokens.get(i);

				if (T instanceof OperationToken) {
					OperationToken currentToken = (OperationToken) tokens.get(i);

					NumberToken a = (NumberToken) tokens.get(i - 1);
					NumberToken b = (NumberToken) tokens.get(i + 1);
					if (currentToken.getValue().contains("+") && Operation == 2) {
						tokens.set(i - 1, new NumberToken(
								Double.toString(Double.parseDouble(a.getValue()) + Double.parseDouble(b.getValue()))));
						tokens.remove(i);
						tokens.remove(i);
					}

					if (currentToken.getValue().contains("-") && Operation == 3) {
						tokens.set(i - 1, new NumberToken(
								Double.toString(Double.parseDouble(a.getValue()) - Double.parseDouble(b.getValue()))));
						tokens.remove(i);
						tokens.remove(i);
					}
					if (currentToken.getValue().contains("*") && Operation == 0) {
						tokens.set(i - 1, new NumberToken(
								Double.toString(Double.parseDouble(a.getValue()) * Double.parseDouble(b.getValue()))));
						tokens.remove(i);
						tokens.remove(i);
					}
					if (currentToken.getValue().contains("/") && Operation == 1) {// get
																					// divide
						// working
						tokens.set(i - 1, new NumberToken(
								Double.toString(Double.parseDouble(a.getValue()) / Double.parseDouble(b.getValue()))));
						tokens.remove(i);
						tokens.remove(i);
					}
				}
			}
			if (Operation < 3) {
				Operation++;
			} else {
				Operation = 0;
			}

		}
		for (i = 0; i < tokens.size(); i++) {
			Token a = (Token) tokens.get(i);
			if (a instanceof NumberToken) {
				a = (NumberToken) tokens.get(i);
				System.out.print("n[" + ((NumberToken) a).getValue() + "] ");
			} else if (a instanceof OperationToken) {
				a = (OperationToken) tokens.get(i);
				System.out.print("o[" + ((OperationToken) a).getValue() + "] ");
			} else {
				a = (GroupingToken) tokens.get(i);
				System.out.print("g[" + ((GroupingToken) a).getValue() + "] ");
			}
		}
		System.out.println();

		NumberToken c = (NumberToken) tokens.get(0);
		tokens.clear();
		tokens.add(c);
		return tokens;
	}

	private List<Token> handleGrouping(List<Token> tokens) {

		int firstIndex = 0;
		int lastIndex = 0;
		boolean closeIndex = false;

		for (int i = 0; i < tokens.size(); i++) {
			Token t = (Token) tokens.get(i);
			if (t instanceof GroupingToken) {
				t = (GroupingToken) tokens.get(i);
				if (((GroupingToken) t).getValue().equals("(")) {
					firstIndex = i;
				}
			}
		}
		for (int i = firstIndex; i < tokens.size(); i++) {
			Token t = (Token) tokens.get(i);
			if (t instanceof GroupingToken) {
				t = (GroupingToken) tokens.get(i);
				if (((GroupingToken) t).getValue().equals(")")) {
					lastIndex = i;
					break;
				}
			}
		}
		List<Token> first = tokens.subList(0, firstIndex);

		List<Token> toSolve = new ArrayList<>();
		toSolve.addAll(tokens.subList(firstIndex + 1, lastIndex));

		List<Token> solvedToken = solve(toSolve);

		List<Token> last = tokens.subList(lastIndex + 1, tokens.size());

		List<Token> solved = new ArrayList<Token>();

		solved.addAll(first);
		solved.addAll(solvedToken);
		solved.addAll(last);

		// tokens.add(solvedToken);
		// for (i = 0; i < last.size(); i++) {
		// tokens.add(last.get(i));
		// }

		for (i = 0; i < solved.size(); i++) {
			Token a = (Token) solved.get(i);
			if (a instanceof NumberToken) {
				a = (NumberToken) solved.get(i);
				System.out.print("n[" + ((NumberToken) a).getValue() + "] ");
			} else if (a instanceof OperationToken) {
				a = (OperationToken) solved.get(i);
				System.out.print("o[" + ((OperationToken) a).getValue() + "] ");
			} else {
				a = (GroupingToken) solved.get(i);
				System.out.print("g[" + ((GroupingToken) a).getValue() + "] ");
			}
		}

		return solved;
	}

	private List<Token> tokenize(String equation) { // go through string
													// equation and make
													// it into tokens
		ArrayList<Token> tokens = new ArrayList<Token>();

		String operands = "+-*/";
		String numbers = "0123456789_"; // this might have to include an _ for

		String currentData = "";

		for (int i = 0; i < equation.length(); i++) {
			String current = Character.toString(equation.charAt(i));
			if (numbers.contains(current) || current.equals(".")) {
				if (current.equals("_")) {
					currentData += "-";
				} else {
					currentData += current;
				}
			}
			if (operands.contains(current)) {
				if (!currentData.equals("")) {
					tokens.add(new NumberToken(currentData));
				}
				tokens.add(new OperationToken(current));
				currentData = "";
			}
			if ((current.equals("(") || current.equals(")"))) {
				if (!currentData.equals("")) {
					tokens.add(new NumberToken(currentData));
				}
				tokens.add(new GroupingToken(current));
				currentData = "";
			}

			if (!currentData.equals("") && current.equals("(")) {
				tokens.add(new NumberToken(currentData));
				tokens.add(new OperationToken("*"));
				tokens.add(new GroupingToken(current));
				currentData = "";
			}

			// tokenization ^^

		}
		if (currentData != "") {
			tokens.add(new NumberToken(currentData));
		}

		// to display the arrayList
		for (i = 0; i < tokens.size(); i++) {
			Token a = (Token) tokens.get(i);
			if (a instanceof NumberToken) {
				a = (NumberToken) tokens.get(i);
				System.out.print("[" + ((NumberToken) a).getValue() + "] ");
			} else if (a instanceof OperationToken) {
				a = (OperationToken) tokens.get(i);
				System.out.print("[" + ((OperationToken) a).getValue() + "] ");
			} else {
				a = (GroupingToken) tokens.get(i);
				System.out.print("[" + ((GroupingToken) a).getValue() + "] ");
			}
		}
		System.out.println();
		return tokens;
	}

}