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

public class Main extends JFrame {
	ArrayList<Token> equationParts = new ArrayList<Token>();
	int[] buttonX = { 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147, 217, 7, 77, 147 }; // coordinates
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

						System.out.println(solve(tokenize(equationDisplay.getText()))); // get
																						// grouping
																						// to
																						// work
					}
				};
			}
			buttons[i].addActionListener(listen);

		}
		this.setTitle("Calculator");
	}

	private double solve(ArrayList<Token> tokens) {

		for (int i = 0; i > tokens.size(); i--) {

			if (tokens.get(i) instanceof OperationToken) {
				OperationToken currentToken = (OperationToken) tokens.get(i);
				if (currentToken.getValue().contains("+")) {
					System.out.println("In Add");
					NumberToken a = (NumberToken) tokens.get(i - 1);
					NumberToken b = (NumberToken) tokens.get(i + 1);
					System.out.println(a.getValue());
					System.out.println(b.getValue());

					tokens.set(i - 1, new NumberToken(Double.toString(a.getValue() + b.getValue())));

					tokens.remove(i);
					tokens.remove(i + 1);

				}

			}

		}
		for (i = 0; i < tokens.size(); i++) {
			Token a = (Token) tokens.get(i);
			if (a instanceof NumberToken) {
				a = (NumberToken) tokens.get(i);
				System.out.print("[" + ((NumberToken) a).getValue() + "] ");
			} else if (a instanceof OperationToken) {
				a = (OperationToken) tokens.get(i);
				System.out.print("[" + ((OperationToken) a).getValue() + "] ");
			} else {
				a = (OperationToken) tokens.get(i);
				System.out.print("[" + ((GroupingToken) a).getValue() + "] ");
			}
		}
		System.out.println();
		NumberToken c = (NumberToken) tokens.get(0);
		return c.getValue();
	}

	private ArrayList<Token> tokenize(String equation) { // go through string
															// equation and make
															// it into tokens
		ArrayList<Token> tokens = new ArrayList<Token>();

		String operands = "+-*/";
		String numbers = "0123456789"; // this might have to include an _ for

		String currentData = "";

		for (int i = 0; i < equation.length(); i++) {
			String current = Character.toString(equation.charAt(i));
			if (numbers.contains(current) || current.equals(".")) {
				currentData += current;
			}
			if (operands.contains(current)) {
				tokens.add(new NumberToken(currentData));
				tokens.add(new OperationToken(current));
				currentData = "";
			}
			if (current.equals("(") || current.equals(")")) {
				tokens.add(new NumberToken(currentData));
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
				a = (OperationToken) tokens.get(i);
				System.out.print("[" + ((GroupingToken) a).getValue() + "] ");
			}
		}
		System.out.println();
		return tokens;
	}

}