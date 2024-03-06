import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int i = 0;
		
		while(true) {
			int five = (N-i) / 5;
			int three = (N - (5 * five)) / 3;
			int left = (N - (5 * five)) % 3;
			
			if(left != 0) i++;
			else {
				System.out.println(five + three);
				break;
			} 
			if(N-i < 0) {
				System.out.println("-1");
				break;
			}
		}
		
	}

}