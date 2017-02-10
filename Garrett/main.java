package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class main extends JFrame {

	int[] buttonX = { 10, 80, 150, 220, 10, 80, 150, 220, 10, 80, 150, 220, 10, 80, 150, 220, 10, 80, 150, 220, 10 };
	int[] buttonY = { 100, 100, 100, 100, 150, 150, 150, 150, 200, 200, 200, 200, 250, 250, 250, 250, 300, 300, 300,
			300 };
	static String[] Labels = { "(", ")", "Del", "*", "1", "2", "3", "/", "4", "5", "6", "+", "7", "8", "9", "-", ".",
			"0", "Enter" };

	String equation = "";

	JButton[] a = new JButton[19];

	public static void main(String[] args) {
		main w = new main();
		w.setVisible(true);
	}

	int i;
	JTextField labelDisplay = new JTextField();

	public main() {
		super();
		this.setSize(306, 400);
		this.getContentPane().setLayout(null);
		this.setTitle("Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelDisplay.setBounds(10, 10, 270, 80);
		this.add(labelDisplay);
		for (i = 0; i <= a.length - 1; i++) {
			JButton j = new JButton();
			if (i < a.length - 1) {
				j.setBounds(buttonX[i], buttonY[i], 60, 40);
			} else {
				j.setBounds(buttonX[i], buttonY[i], 130, 40);
			}
			j.setText(Labels[i]);
			a[i] = j;
			this.add(j);

			String label = Labels[i];

			ActionListener l = null;

			if (i < a.length && i != 2 && i != a.length - 1) {
				l = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						equation += label;
						labelDisplay.setText(equation);
					}
				};
			}

			if (i == 2) {
				l = new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						equation = equation.substring(0, equation.length() - 1);
						labelDisplay.setText(equation);
					}
				};
			}

			if (i == a.length - 1) {
				l = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						equation = labelDisplay.getText();
						solve();
						// equation = "";
					}
				};
			}

			a[i].addActionListener(l);
		}
	}

	String equation2;
	String result1;
	String tempdone;
	boolean done = false;

	public void solve() {
		done = false;
		while (!done) {
			if (equation.contains("(") && equation.contains(")")) {
				equation2 = equation.substring(equation.indexOf('(') + 1, equation.indexOf(')'));
				System.out.println("para check sending" + equation2);
				if (equation2.contains("*") == false && equation2.contains("/") == false
						&& equation2.contains("+") == false && equation2.contains("-") == false) {
					String temp5 = equation.substring(equation.indexOf('('), equation.indexOf(')') + 1);
					equation = equation.replace(temp5, equation2);
				} else {
					if (equation2.contains("*") == false && equation2.contains("/") == false) {
						add(equation2);
						subtract(equation2);
					} else {
						mult(equation);
						divide(equation);
					}
				}
			} else {
				done = true;
				System.out.println("done with para check");
				System.out.println(equation);
			}

		}

		done = false;
		while (!done) {

			if (equation.contains("*") == false && equation.contains("/") == false) {
				add(equation);
				subtract(equation);
				isDone();
				System.out.println(equation);
			} else {
				mult(equation);
				divide(equation);
				isDone();
				System.out.println(equation);
			}
			labelDisplay.setText(equation);
		}

	}

	void isDone() {
		System.out.println("done check");
		if (equation.contains("-")) {
			tempdone = equation.substring(2, equation.length());
		} else {
			tempdone = equation;
		}
		if (equation.contains("+") == false && equation.contains("*") == false && equation.contains("/") == false
				&& tempdone.contains("-") == false) {
			done = true;
						
			if (equation.contains(".") && (equation.lastIndexOf("0") == equation.length()-1)) {
				equation = equation.substring(0, equation.length() - 2);
			}
			System.out.println("done!!!!!! " + equation);
			equation = "";
		}

	}

	void add(String input) {
		if (input.contains("+")) {

			int index = input.indexOf("+");
			String temp1 = input.substring(0, index);
			String temp2 = input.substring(index + 1, input.length());
			int startOfNUM1 = 0;
			int endOfNUM2 = temp2.length() - 1;
			System.out.println(temp1 + "temp one");
			System.out.println(temp2 + "temp two");
			if (temp1.contains("+") && temp1.indexOf("+") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("+") + 1;
			}
			if (temp1.contains("-") && temp1.indexOf("-") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("-") + 1;
			}
			if (temp1.contains("*") && temp1.indexOf("*") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("*") + 1;
			}
			if (temp1.contains("/") && temp1.indexOf("/") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("/") + 1;
			}
			if (temp1.contains("(") && temp1.indexOf("(") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("(") + 1;
			}

			if (temp2.contains("+") && temp2.indexOf("+") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("+") - 1;
			}
			if (temp2.contains("-") && temp2.indexOf("-") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("-") - 1;
			}
			if (temp2.contains("*") && temp2.indexOf("*") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("*") - 1;
			}
			if (temp2.contains("/") && temp2.indexOf("/") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("/") - 1;
			}
			if (temp2.contains(")") && temp2.indexOf(")") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf(")") - 1;
			}

			String numberOne = temp1.substring(startOfNUM1, temp1.length());
			String numberTwo = temp2.substring(0, endOfNUM2 + 1);

			System.out.println(numberOne + " number one");
			System.out.println(numberTwo + " number two");

			if (numberOne.contains("_")) {
				String temp8;
				temp8 = numberOne.substring(1, numberOne.length());
				numberOne = "-" + temp8;
			}

			if (numberTwo.contains("_")) {
				String temp9;
				temp9 = numberTwo.substring(1, numberTwo.length());
				numberTwo = "-" + temp9;
			}

			System.out.println(numberOne + " number one");
			System.out.println(numberTwo + " number two");

			float result = Float.parseFloat(numberOne) + Float.parseFloat(numberTwo);

			if (Float.toString(result).contains("-")) {
				Float.toString(result).replace("-", "_");
			}
			System.out.println(result);
			System.out.println(numberOne + "num one");
			System.out.println(numberTwo + "num two");
			if (input == equation) {
				if (input.contains("_")) {

					equation = equation.replace(numberOne + "+" + numberTwo.replace("-", "_"), Float.toString(result));
					System.out.println(equation + "final equation");
				} else {
					equation = equation.replace(numberOne + "+" + numberTwo, Float.toString(result));
					System.out.println(equation + "final equation");
				}
			} else {
				equation2 = equation2.replace(numberOne + "+" + numberTwo, Float.toString(result));
				result1 = Float.toString(result);
				System.out.println(result1 + "result1");
				equation = equation.substring(0, equation.indexOf('(')) + result1
						+ equation.substring(equation.indexOf(')') + 1, equation.length());
				System.out.println(equation + "final equation");
			}
		}
	}

	void subtract(String input) {
		if (input.contains("-")) {

			int index = input.indexOf("-");
			String temp1 = input.substring(0, index);
			String temp2 = input.substring(index + 1, input.length());
			int startOfNUM1 = 0;
			int endOfNUM2 = temp2.length() - 1;
			System.out.println(temp1 + "temp one");
			System.out.println(temp2 + "temp two");
			if (temp1.contains("+") && temp1.indexOf("+") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("+") + 1;
			}
			if (temp1.contains("-") && temp1.indexOf("-") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("-") + 1;
			}
			if (temp1.contains("*") && temp1.indexOf("*") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("*") + 1;
			}
			if (temp1.contains("/") && temp1.indexOf("/") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("/") + 1;
			}
			if (temp1.contains("(") && temp1.indexOf("(") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("(") + 1;
			}

			if (temp2.contains("+") && temp2.indexOf("+") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("+") - 1;
			}
			if (temp2.contains("-") && temp2.indexOf("-") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("-") - 1;
			}
			if (temp2.contains("*") && temp2.indexOf("*") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("*") - 1;
			}
			if (temp2.contains("/") && temp2.indexOf("/") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("/") - 1;
			}
			if (temp2.contains(")") && temp2.indexOf(")") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf(")") - 1;
			}

			System.out.println(endOfNUM2 + "end of num 2");
			String numberOne = temp1.substring(startOfNUM1, temp1.length());
			String numberTwo = temp2.substring(0, endOfNUM2 + 1);

			if (numberOne.contains("_")) {
				String temp8;
				temp8 = numberOne.substring(1, numberOne.length());
				numberOne = "-" + temp8;
			}

			if (numberTwo.contains("_")) {
				String temp9;
				temp9 = numberTwo.substring(1, numberTwo.length());
				numberTwo = "-" + temp9;
			}

			float result = Float.parseFloat(numberOne) - Float.parseFloat(numberTwo);

			System.out.println(numberOne + "num one");
			System.out.println(numberTwo + "num two");
			if (input == equation) {
				if (input.contains("_")) {
					equation = equation.replace(numberOne + "-" + numberTwo.replace("-", "_"), Float.toString(result));
				} else {
					equation = equation.replace(numberOne + "-" + numberTwo, Float.toString(result));
				}
			} else {
				equation2 = equation2.replace(numberOne + "-" + numberTwo, Float.toString(result));
				result1 = Float.toString(result);
				System.out.println(result1 + "result1");
				equation = equation.substring(0, equation.indexOf('(')) + result1
						+ equation.substring(equation.indexOf(')') + 1, equation.length());
			}
			System.out.println(equation + " final answer");
		}
	}

	void mult(String input) {
		if (input.contains("*")) {

			int index = input.indexOf("*");
			String temp1 = input.substring(0, index);
			String temp2 = input.substring(index + 1, input.length());
			int startOfNUM1 = 0;
			int endOfNUM2 = temp2.length() - 1;
			System.out.println(temp1 + "temp one");
			System.out.println(temp2 + "temp two");
			if (temp1.contains("+") && temp1.indexOf("+") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("+") + 1;
			}
			if (temp1.contains("-") && temp1.indexOf("-") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("-") + 1;
			}
			if (temp1.contains("*") && temp1.indexOf("*") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("*") + 1;
			}
			if (temp1.contains("/") && temp1.indexOf("/") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("/") + 1;
			}
			if (temp1.contains("(") && temp1.indexOf("(") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("(") + 1;
			}

			if (temp2.contains("+") && temp2.indexOf("+") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("+") - 1;
			}
			if (temp2.contains("-") && temp2.indexOf("-") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("-") - 1;
			}
			if (temp2.contains("*") && temp2.indexOf("*") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("*") - 1;
			}
			if (temp2.contains("/") && temp2.indexOf("/") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("/") - 1;
			}
			if (temp2.contains(")") && temp2.indexOf(")") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf(")") - 1;
			}

			System.out.println(endOfNUM2 + "end of num 2");
			String numberOne = temp1.substring(startOfNUM1, temp1.length());
			String numberTwo = temp2.substring(0, endOfNUM2 + 1);

			if (numberOne.contains("_")) {
				String temp8;
				temp8 = numberOne.substring(1, numberOne.length());
				numberOne = "-" + temp8;
			}

			if (numberTwo.contains("_")) {
				String temp9;
				temp9 = numberTwo.substring(1, numberTwo.length());
				numberTwo = "-" + temp9;
			}

			float result = Float.parseFloat(numberOne) * Float.parseFloat(numberTwo);

			System.out.println(numberOne + "num one");
			System.out.println(numberTwo + "num two");
			if (input == equation) {
				if (input.contains("_")) {

					equation = equation.replace(numberOne + "*" + numberTwo.replace("-", "_"), Float.toString(result));
					System.out.println(equation + "final equation");
				} else {
					equation = equation.replace(numberOne + "*" + numberTwo, Float.toString(result));
					System.out.println(equation + "final equation");
				}
			} else {
				equation2 = equation2.replace(numberOne + "*" + numberTwo, Float.toString(result));
				result1 = Float.toString(result);
				System.out.println(result1 + "result1");
				equation = equation.substring(0, equation.indexOf('(')) + result1
						+ equation.substring(equation.indexOf(')') + 1, equation.length());
				System.out.println(equation + "final equation");
			}
		}
	}

	void divide(String input) {
		if (input.contains("/")) {

			int index = input.indexOf("/");
			String temp1 = input.substring(0, index);
			String temp2 = input.substring(index + 1, input.length());
			int startOfNUM1 = 0;
			int endOfNUM2 = temp2.length() - 1;
			System.out.println(temp1 + "temp one");
			System.out.println(temp2 + "temp two");
			if (temp1.contains("+") && temp1.indexOf("+") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("+") + 1;
			}
			if (temp1.contains("-") && temp1.indexOf("-") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("-") + 1;
			}
			if (temp1.contains("*") && temp1.indexOf("*") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("*") + 1;
			}
			if (temp1.contains("/") && temp1.indexOf("/") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("/") + 1;
			}
			if (temp1.contains("(") && temp1.indexOf("(") > startOfNUM1) {
				startOfNUM1 = temp1.indexOf("(") + 1;
			}

			if (temp2.contains("+") && temp2.indexOf("+") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("+") - 1;
			}
			if (temp2.contains("-") && temp2.indexOf("-") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("-") - 1;
			}
			if (temp2.contains("*") && temp2.indexOf("*") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("*") - 1;
			}
			if (temp2.contains("/") && temp2.indexOf("/") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf("/") - 1;
			}
			if (temp2.contains(")") && temp2.indexOf(")") < endOfNUM2) {
				endOfNUM2 = temp2.indexOf(")") - 1;
			}

			System.out.println(endOfNUM2 + "end of num 2");
			String numberOne = temp1.substring(startOfNUM1, temp1.length());
			String numberTwo = temp2.substring(0, endOfNUM2 + 1);

			if (numberOne.contains("_")) {
				String temp8;
				temp8 = numberOne.substring(1, numberOne.length());
				numberOne = "-" + temp8;
			}

			if (numberTwo.contains("_")) {
				String temp9;
				temp9 = numberTwo.substring(1, numberTwo.length());
				numberTwo = "-" + temp9;
			}

			float result = Float.parseFloat(numberOne) / Float.parseFloat(numberTwo);

			System.out.println(numberOne + "num one");
			System.out.println(numberTwo + "num two");
			if (input == equation) {
				if (input.contains("_")) {

					equation = equation.replace(numberOne + "/" + numberTwo.replace("-", "_"), Float.toString(result));
					System.out.println(equation + "final equation");
				} else {
					equation = equation.replace(numberOne + "/" + numberTwo, Float.toString(result));
					System.out.println(equation + "final equation");
				}
			} else {
				equation2 = equation2.replace(numberOne + "/" + numberTwo, Float.toString(result));
				result1 = Float.toString(result);
				System.out.println(result1 + "result1");
				equation = equation.substring(0, equation.indexOf('(')) + result1
						+ equation.substring(equation.indexOf(')') + 1, equation.length());
				System.out.println(equation + "final equation");
			}
		}
	}
}