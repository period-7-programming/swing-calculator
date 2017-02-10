package calculator;
//doesn't work with negatives because it takes the negative as an operation and having errors
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame {
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
			}
			buttons[i].addActionListener(listen);
		}
		this.setTitle("Calculator");
	}

	String calculate(String equation) {
		if (equation.contains("(")) {
			String result = calculate(equation.substring(equation.indexOf("(") + 1 , equation.lastIndexOf(")")));
			equation = equation.substring(0, equation.indexOf("("))
					+ result + equation.substring(equation.lastIndexOf(")") + 1);
		}

		if (equation.contains("*") || equation.contains("/")) {
			if (equation.contains("*") && equation.contains("/")) {
				if (equation.indexOf("*") < equation.indexOf("/")) {
					String firstHalf = equation.substring(0, equation.indexOf("*"));
					int startNumOne = 0;
					if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
						startNumOne = firstHalf.indexOf("+");
					}
					if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
						startNumOne = firstHalf.indexOf("-");
					}
					if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
						startNumOne = firstHalf.indexOf("*");
					}
					if (firstHalf.contains("/") && firstHalf.indexOf("/") > startNumOne) {
						startNumOne = firstHalf.indexOf("/");
					}
					String secondHalf = equation.substring(equation.indexOf("*") + 1);
					int endNumTwo = secondHalf.length();
					if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("+");
					}
					if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("-");
					}
					if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("*");
					}
					if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("/");
					}
					if (equation.substring(startNumOne, equation.indexOf("*")).contains("_")) {
						equation.substring(startNumOne, equation.indexOf("*")).replace("_", "-");
					}
					if (equation.substring(equation.indexOf("*") + 1, endNumTwo + 1 + equation.indexOf("*")).contains("_")) {
						equation.substring(equation.indexOf("*") + 1, endNumTwo + 1 + equation.indexOf("*")).replace("_", "-");
					}
					float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("*")))
							* Float.parseFloat(equation.substring(equation.indexOf("*") + 1, endNumTwo + 1 + equation.indexOf("*")));
					String negative = "";
					if (calculation < 0) {
						negative = "_";
						calculation = Math.abs(calculation);
					}
					equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
							+ equation.substring(endNumTwo + 1 + equation.indexOf("*"));
				} else {
					String firstHalf = equation.substring(0, equation.indexOf("/"));
					int startNumOne = 0;
					if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
						startNumOne = firstHalf.indexOf("+");
					}
					if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
						startNumOne = firstHalf.indexOf("-");
					}
					if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
						startNumOne = firstHalf.indexOf("*");
					}
					if (firstHalf.contains("/") && firstHalf.indexOf("/") > startNumOne) {
						startNumOne = firstHalf.indexOf("/");
					}
					String secondHalf = equation.substring(equation.indexOf("/") + 1);
					int endNumTwo = secondHalf.length();
					if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("+");
					}
					if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("-");
					}
					if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("*");
					}
					if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("/");
					}
					if (equation.substring(startNumOne, equation.indexOf("/")).contains("_")) {
						equation.substring(startNumOne, equation.indexOf("/")).replace("_", "-");
					}
					if (equation.substring(equation.indexOf("/") + 1, endNumTwo + 1 + equation.indexOf("/")).contains("_")) {
						equation.substring(equation.indexOf("/") + 1, endNumTwo + 1 + equation.indexOf("/")).replace("_", "-");
					}
					float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("/")))
							/ Float.parseFloat(equation.substring(equation.indexOf("/") + 1, endNumTwo + 1 + equation.indexOf("/")));
					String negative = "";
					if (calculation < 0) {
						negative = "_";
						calculation = Math.abs(calculation);
					}
					equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
							+ equation.substring(endNumTwo + 1 + equation.indexOf("/"));
				}
			} else if (equation.contains("*")) {
				String firstHalf = equation.substring(0, equation.indexOf("*"));
				int startNumOne = 0;
				if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
					startNumOne = firstHalf.indexOf("+");
				}
				if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
					startNumOne = firstHalf.indexOf("-");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("*");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("/");
				}
				String secondHalf = equation.substring(equation.indexOf("*") + 1);
				int endNumTwo = secondHalf.length();
				if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("+");
				}
				if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("-");
				}
				if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("*");
				}
				if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("/");
				}
				if (equation.substring(startNumOne, equation.indexOf("*")).contains("_")) {
					equation.substring(startNumOne, equation.indexOf("*")).replace("_", "-");
				}
				if (equation.substring(equation.indexOf("*") + 1, endNumTwo + 1 + equation.indexOf("*")).contains("_")) {
					equation.substring(equation.indexOf("*") + 1, endNumTwo + 1 + equation.indexOf("*")).replace("_", "-");
				}
				float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("*")))
						* Float.parseFloat(equation.substring(equation.indexOf("*") + 1, endNumTwo + 1 + equation.indexOf("*")));
				String negative = "";
				if (calculation < 0) {
					negative = "_";
					calculation = Math.abs(calculation);
				}
				equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
						+ equation.substring(endNumTwo + 1 + equation.indexOf("*"));
			} else {
				String firstHalf = equation.substring(0, equation.indexOf("/"));
				int startNumOne = 0;
				if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
					startNumOne = firstHalf.indexOf("+");
				}
				if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
					startNumOne = firstHalf.indexOf("-");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("*");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("/");
				}
				String secondHalf = equation.substring(equation.indexOf("/") + 1);
				int endNumTwo = secondHalf.length();
				if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("+");
				}
				if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("-");
				}
				if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("*");
				}
				if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("/");
				}
				if (equation.substring(startNumOne, equation.indexOf("/")).contains("_")) {
					equation.substring(startNumOne, equation.indexOf("/")).replace("_", "-");
				}
				if (equation.substring(equation.indexOf("/") + 1, endNumTwo + 1 + equation.indexOf("/")).contains("_")) {
					equation.substring(equation.indexOf("/") + 1, endNumTwo + 1 + equation.indexOf("/")).replace("_", "-");
				}
				float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("/")))
						/ Float.parseFloat(equation.substring(equation.indexOf("/") + 1, endNumTwo + 1 + equation.indexOf("/")));
				String negative = "";
				if (calculation < 0) {
					negative = "_";
					calculation = Math.abs(calculation);
				}
				equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
						+ equation.substring(endNumTwo + 1 + equation.indexOf("/"));
			}
			equation = calculate(equation);
		} else if (equation.contains("+") || equation.contains("-")) {
			if (equation.contains("+") && equation.contains("-")) {
				if (equation.indexOf("+") < equation.indexOf("-")) {
					String firstHalf = equation.substring(0, equation.indexOf("+"));
					int startNumOne = 0;
					if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
						startNumOne = firstHalf.indexOf("+");
					}
					if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
						startNumOne = firstHalf.indexOf("-");
					}
					String secondHalf = equation.substring(equation.indexOf("+") + 1);
					int endNumTwo = secondHalf.length();
					if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("+");
					}
					if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("-");
					}
					if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("*");
					}
					if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("/");
					}
					if (equation.substring(startNumOne, equation.indexOf("+")).contains("_")) {
						equation.substring(startNumOne, equation.indexOf("+")).replace("_", "-");
					}
					if (equation.substring(equation.indexOf("+") + 1, endNumTwo + 1 + equation.indexOf("+")).contains("_")) {
						equation.substring(equation.indexOf("+") + 1, endNumTwo + 1 + equation.indexOf("+")).replace("_", "-");
					}
					float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("+")))
							+ Float.parseFloat(equation.substring(equation.indexOf("+") + 1, endNumTwo + 1 + equation.indexOf("+")));
					String negative = "";
					if (calculation < 0) {
						negative = "_";
						calculation = Math.abs(calculation);
					}
					equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
							+ equation.substring(endNumTwo + 1 + equation.indexOf("+"));
				} else {
					String firstHalf = equation.substring(0, equation.indexOf("-"));
					int startNumOne = 0;
					if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
						startNumOne = firstHalf.indexOf("+");
					}
					if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
						startNumOne = firstHalf.indexOf("-");
					}
					if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
						startNumOne = firstHalf.indexOf("*");
					}
					if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
						startNumOne = firstHalf.indexOf("/");
					}
					String secondHalf = equation.substring(equation.indexOf("-") + 1);
					int endNumTwo = secondHalf.length();
					if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("+");
					}
					if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("-");
					}
					if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("*");
					}
					if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
						endNumTwo = secondHalf.indexOf("/");
					}
					if (equation.substring(startNumOne, equation.indexOf("-")).contains("_")) {
						equation.substring(startNumOne, equation.indexOf("-")).replace("_", "-");
					}
					if (equation.substring(equation.indexOf("-") + 1, endNumTwo + 1 + equation.indexOf("-")).contains("_")) {
						equation.substring(equation.indexOf("-") + 1, endNumTwo + 1 + equation.indexOf("-")).replace("_", "-");
					}
					float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("-")))
							- Float.parseFloat(equation.substring(equation.indexOf("-") + 1, endNumTwo + 1 + equation.indexOf("-")));
					String negative = "";
					if (calculation < 0) {
						negative = "_";
						calculation = Math.abs(calculation);
					}
					equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
							+ equation.substring(endNumTwo + 1 + equation.indexOf("-"));
				}
			} else if (equation.contains("+")) {
				String firstHalf = equation.substring(0, equation.indexOf("+"));
				int startNumOne = 0;
				if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
					startNumOne = firstHalf.indexOf("+");
				}
				if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
					startNumOne = firstHalf.indexOf("-");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("*");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("/");
				}
				String secondHalf = equation.substring(equation.indexOf("+") + 1);
				int endNumTwo = secondHalf.length();
				if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("+");
				}
				if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("-");
				}
				if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("*");
				}
				if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("/");
				}
				if (equation.substring(startNumOne, equation.indexOf("+")).contains("_")) {
					equation.substring(startNumOne, equation.indexOf("+")).replace("_", "-");
				}
				if (equation.substring(equation.indexOf("+") + 1, endNumTwo + 1 + equation.indexOf("+")).contains("_")) {
					equation.substring(equation.indexOf("+") + 1, endNumTwo + 1 + equation.indexOf("+")).replace("_", "-");
				}
				float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("+")))
						+ Float.parseFloat(equation.substring(equation.indexOf("+") + 1, endNumTwo + 1 + equation.indexOf("+")));
				String negative = "";
				if (calculation < 0) {
					negative = "_";
					calculation = Math.abs(calculation);
				}
				equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
						+ equation.substring(endNumTwo + 1 + equation.indexOf("+"));
			} else {
				String firstHalf = equation.substring(0, equation.indexOf("-"));
				int startNumOne = 0;
				if (firstHalf.contains("+") && firstHalf.indexOf("+") > startNumOne) {
					startNumOne = firstHalf.indexOf("+");
				}
				if (firstHalf.contains("-") && firstHalf.indexOf("-") > startNumOne) {
					startNumOne = firstHalf.indexOf("-");
				}
				if (firstHalf.contains("*") && firstHalf.indexOf("*") > startNumOne) {
					startNumOne = firstHalf.indexOf("*");
				}
				if (firstHalf.contains("/") && firstHalf.indexOf("/") > startNumOne) {
					startNumOne = firstHalf.indexOf("/");
				}
				String secondHalf = equation.substring(equation.indexOf("-") + 1);
				int endNumTwo = secondHalf.length();
				if (secondHalf.contains("+") && secondHalf.indexOf("+") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("+");
				}
				if (secondHalf.contains("-") && secondHalf.indexOf("-") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("-");
				}
				if (secondHalf.contains("*") && secondHalf.indexOf("*") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("*");
				}
				if (secondHalf.contains("/") && secondHalf.indexOf("/") < endNumTwo) {
					endNumTwo = secondHalf.indexOf("/");
				}
				if (equation.substring(startNumOne, equation.indexOf("-")).contains("_")) {
					equation.substring(startNumOne, equation.indexOf("-")).replace("_", "-");
				}
				if (equation.substring(equation.indexOf("-") + 1, endNumTwo + 1 + equation.indexOf("-")).contains("_")) {
					equation.substring(equation.indexOf("-") + 1, endNumTwo + 1 + equation.indexOf("-")).replace("_", "-");
				}
				float calculation = Float.parseFloat(equation.substring(startNumOne, equation.indexOf("-")))
						- Float.parseFloat(equation.substring(equation.indexOf("-") + 1, endNumTwo + 1 + equation.indexOf("-")));
				String negative = "";
				if (calculation < 0) {
					negative = "_";
					calculation = Math.abs(calculation);
				}
				equation = equation.substring(0, startNumOne) + negative + Float.toString(calculation)
						+ equation.substring(endNumTwo + 1 + equation.indexOf("-"));
			}
			equation = calculate(equation);
		}
		return equation;
	}
}