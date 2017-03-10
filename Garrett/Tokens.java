package calculator;

interface Token { 
}

class NumberToken implements Token {
	private double value;

	public NumberToken(String i) {
		this.value = Double.parseDouble(i);
	}

	public NumberToken() {

	}

	public double getValue() {
		return this.value;
	}
}

class GroupingToken implements Token {
	String a;

	public GroupingToken(String i) {
		this.a = i;
	}

	public String getValue() {
		return this.a;
	}

}

class OperationToken implements Token {
	private String value;

	public OperationToken(String i) {
		this.value = i;
	}

	public String getValue() {
		return this.value;
	}

}
