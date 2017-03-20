package calculator;

interface Token { 
	public String getValue();
}

class NumberToken implements Token {
	private String value;

	public NumberToken(String i) {
		this.value = i;
	}

	public NumberToken() {

	}

	public String getValue() {
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
