package calculator;

class Expression{
	
	public double getValue() {
		
		return 0;
	}
}

class Number extends Expression {
	double n;
	
	public Number(double a){
		this.n = a; 
	}
	
	public double getValue(){
		return n;
	}
	
}
class Add extends Expression{
	
	Expression a;
	Expression b;
	
	public Add(Expression c, Expression d){
		this.a = c;
		this.b = d;
	}
	
	public double getValue(){	
		return a.getValue() + b.getValue();
	}
	
}