import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int zero = 0, one = 0;
        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i-1) != str.charAt(i)) {
                if(str.charAt(i-1) == '0') zero++;
                else one++;
            }
        }

        if(str.charAt(str.length()-1) == '0') zero++;
        else one++;

        System.out.println(Math.min(zero, one));
    }
}
