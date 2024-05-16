import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') count++;
            else count--;
        }

        int open = 0, close = 0, answer = 0;
        if(count < 0) { // )가 더 많은 경우
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '(') {
                    open++;
                    if(answer > 0) break;
                } else {
                    close++;
                }
                if(open < close) answer = Math.max(answer, close);
            }
        } else { // (가 더 많은 경우
            for(int i = str.length()-1; i >= 0; i--) {
                if(str.charAt(i) == '(') {
                    open++;
                } else {
                    close++;
                    if(answer > 0) break;
                }
                if(open > close) answer = Math.max(answer, open);
            }
        }
        System.out.println(answer);
    }
}
