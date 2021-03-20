public class TaschenrechnerV2 {
	
	public String[][] separated;
	String[] operators;
	String[] timesDivide;
	String[] plusMinus;
	public TaschenrechnerV2() {
		timesDivide = new String[] {"*", "/"};
		plusMinus = new String[] {"+","-"};
		operators = new String[] {"*","/","-","+"};
	}
	
	public double work(String[][] input) {
		separated = input;
		return calculate();
	}
	
	public void rearrange(int index) {
		for(int j=index;j<separated.length;j++) {
			if(j+1 == separated.length) {
				separated[j][0] = "";
				separated[j][1] = "";
				return;
			}
			separated[j][0] = separated[j+1][0];
			separated[j][1] = separated[j+1][1];
		}
	}
	
	public double calculate() {
		//Two separated sections with *,/ and +,-. 
		for(int i=0;i<separated.length;i++) {
			for(int j=0;j<timesDivide.length;j++) {
				if(separated[i][0].equals(timesDivide[j])) {
					double result = add(Double.valueOf(separated[i-1][1]),  Double.valueOf(separated[i][1]), timesDivide[j]);
					separated[i-1][1] = String.valueOf(result);
					rearrange(i);
					i=0;
				}
			}
		}
		for(int i=0;i<separated.length;i++) {
			for(int j=0;j<plusMinus.length;j++) {
				if(separated[i][0].equals(plusMinus[j])) {
					double result = add(Double.valueOf(separated[i-1][1]),  Double.valueOf(separated[i][1]), plusMinus[j]);
					separated[i-1][1] = String.valueOf(result);
					rearrange(i);
					i=0;
				}
			}
		}
		double finalResult=0;
		for(int l=0;l<separated.length;l++) {
			if(!separated[l][1].equals("")) {
				finalResult = Double.valueOf(separated[l][1]);
			}
		}
		return finalResult;
	}
	
	public double add(double a, double b, String OP) {
		switch(OP) {
		case "*":
			return a * b;
		case "/":
			return a / b;
		case "-":
			return a - b;
		case "+":
			return a + b;
		default:
			return 0;
		}
	}
}