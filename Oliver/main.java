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
						equationDisplay.setText(calculate(equationDisplay.getText()));
					}
				};
			}buttons[i].addActionListener(listen);

	}this.setTitle("Calculator");}

	String calculate(String equation) {
		String numbers = "0123456789.";
		String operations = "+-/*";
		// check if there's other characters HERE
		if (equation.contains("(")) {
			String result = calculate(equation.substring(equation.indexOf("(") + 1, equation.lastIndexOf(")")));
			equation = equation.substring(0, equation.indexOf("(")) + result
					+ equation.substring(equation.lastIndexOf(")") + 1);
		}
		String currentToken = "";
		boolean lastTokenOperation = false;
		for (int i = 0; i < equation.length(); i++) {
			if (numbers.contains(Character.toString(equation.charAt(i)))) {
				currentToken = currentToken + equation.charAt(i);
			} else {
				if (true)// if char is - and last was operation
				{
					Token number = new NumberToken(Double.parseDouble((currentToken)));
					equationParts.add(number);
					currentToken = Character.toString(equation.charAt(i));
				} else {
				}
			}
			return equation;
		}
	}

	interface Token {
	}

	class NumberToken implements Token {
		double value;

		public NumberToken(double d) {
			this.value = d;
		}
	}

class OperationToken implements Token {
	String value;

	public OperationToken(String i) {
		this.value = i;
	}
}