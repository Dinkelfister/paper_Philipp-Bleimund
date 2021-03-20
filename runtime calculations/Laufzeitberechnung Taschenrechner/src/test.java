import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
        String S = scann.nextLine();
        String S2 = scann.nextLine();
        
        String fileName = "result";
        
        for(int i=0;i<Integer.valueOf(S2);i++) {
        	fileName = "result" + String.valueOf(i) + ".txt";
        	GUIV2 gui = new GUIV2(S, fileName);
        	gui = null;
        	System.gc();
        }
        
        
	}
}
