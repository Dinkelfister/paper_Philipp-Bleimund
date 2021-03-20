import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Philipp Bleimund
 */

public class GUIV2 {
	
	TaschenrechnerV2 calc;
	exceptions exceptionHandle;
	char[] operators;
	String[][] sorted;

    PrintWriter outputStream;
	
    public GUIV2(String S, String S2) {
        
        initOther();
        
        speedTest(Integer.parseInt(S), S2);
        
    }
    
    public void initOther() {
        sorted = new String[1][2];
        sorted[0][0] = "";		//set "" so you can easily add the new Number without getting a exception for trying adding a Number to 'null'
        sorted[0][1] = "";
        operators = new char[] {'*','/','+','-'};
        calc = new TaschenrechnerV2();
        exceptionHandle = new exceptions();
    }
                      
    
    
    private void analyse(String input) {
    	boolean isMinus = true;		//isMinus = true says that the current '-' was a minus sign / is expected to be|| isMinus = false says that it can be anything else 
    								//it is used to prevent that the program uses the sign '-' as a sign and operator at the same time
		char[] rawInput = input.toCharArray();
		for(int i=0;i<rawInput.length;i++) {
			if(isMinus == true) {
				if(rawInput[i] == '-') {
					sorted[sorted.length-1][1] = String.valueOf('-');
				} else
					isMinus = false;
			}
			
			if(isMinus == false) {
				boolean tmp = true;	//tmp is used to write a else condition over }
				for(int j=0;j<operators.length;j++) {
					if(rawInput[i] == operators[j]) {
						expandSorted();	
						sorted[sorted.length-1][0] = String.valueOf(operators[j]);
						isMinus = true;
						tmp = false;
					}
				}
				if(tmp) {
					sorted[sorted.length-1][1] = sorted[sorted.length-1][1] + rawInput[i];
				}
			}
		}
    }
    
    private void expandSorted() {
    	String[][] newArray = new String[sorted.length + 1][sorted[0].length];
    	System.arraycopy(sorted, 0, newArray, 0, sorted.length);
    	newArray[newArray.length-1][0] = "";
    	newArray[newArray.length-1][1] = "";
    	sorted = newArray;
    } 
    
    public boolean searchException(String input) {
    	if(input == "") {
    		return false;
    	}
    	char[] rawInput = input.toCharArray();
    	for(int i=0;i<rawInput.length-1;i++) { //-1 to prevent outOfBounds exception
    		if(rawInput[i] == '.') {	//handle all exceptions that could occur after an '.'
    			if(rawInput[i+1] == '.') {
    				//exception handle when two '.' are standing behind each other
    				return false;
    			}
    			for(int j=0;j<operators.length;j++) {
    				if(rawInput[i+1] == operators[j]) {
    					//exception handling when after an '.' an operator stands
    					return false;
    				}
    			}
    		}
    		for(int k=0;k<operators.length;k++) { // handle all exceptions that could occur after an operator
    			if(rawInput[i] == operators[k]) {
    				if(rawInput[i+1] == '.') {
    					//exception handling when a '.' stands behind an operator
    					return false;
    				}
    				for(int l=0;l<operators.length -1 ;l++) { //-1 to exclude the '-' from the search
    					if(rawInput[i+1] == operators[l]) {
    						//exception handle when two operators are behind each other
    						return false;
    					}
    				}
    				if(operators[k] == '-' && rawInput[i+1] == '-') {
    					//exception handle when two '-' are behind each other
    					return false;
    				}
    			}
    		}
    	}
    	for(int m=0;m<operators.length;m++) {
    		if(rawInput[rawInput.length-1] == operators[m]) {
    			//exception handle if the equation ends on an operator
    			return false;
    		}
    	}
    	if(rawInput[rawInput.length-1] == '.') {
    		//exception handle if the equation ends on a '.'
    		return false;
    	}
    	for(int n=0;n<sorted.length;n++) {
    		int dotCounter = 0;
    		String number = sorted[n][1];
    		char[] numberArr = number.toCharArray();
    		for(int o=0;o<numberArr.length;o++) {
    			if(numberArr[o] == '.') {
    				dotCounter++;
    			}
    			if(dotCounter > 1) {
    				//exception handle if two dots are in the same number
    			}
    		}
    	}
    	return true;
    }
    
    public double round(double input) {
    	input = input * 10;
    	input = Math.round(input);
    	return input / 10;
    }
    

    
    public void speedTest(int runden, String fileName) {
    	//init:
    	String minus[] = new String[] {"", "-"};
    	
		try {
			outputStream = new PrintWriter(fileName);
			outputStream.println("The results of the speed test");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String result[][] = new String[runden][3];
		
		long startTime = System.nanoTime();
		
		for(int i=1;i<runden+1;i++) {
			String s = "1";
			for(int j=0;j<i;j++) {
				double a = round(Math.random() * 9);
				char c = operators[(int)(Math.random()*4)];
				String m = minus[(int)(Math.random()*2)];
				s = s + c + m + a;
			}
			
			System.out.println(i);
			
			long timer1 = System.nanoTime();
			
			analyse(s);
			searchException(s);
			double res = round(calc.work(sorted));
			
			long timer2 = System.nanoTime();
			
			long deltaTime = timer2 - timer1;
			
			result[i-1][0] = String.valueOf(timer1);
			result[i-1][1] = String.valueOf(timer2);
			result[i-1][2] = String.valueOf(deltaTime);
		}
		
		long endTime = System.nanoTime();
		long nano = endTime - startTime;
		long mikro = nano / 1000;
		long milli = mikro / 1000;
		long sek = milli / 1000;
		long min = sek / 60;
		
		System.out.println(nano +"nano");
		System.out.println(mikro +"mikro");
		System.out.println(milli +"milli");
		System.out.println(sek +"sek");
		System.out.println(min +"min");
		//print to document
		for(int k=0;k<3;k++) {
			for(int l=0;l<runden;l++) {
				outputStream.println(result[l][k]);
			}
			outputStream.println("~~~~~~~~~~");
		}
		outputStream.close();
    }                 
}
