import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();

            boolean isVPS = true;
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '(') stack.push('(');
                else {
                    if(stack.isEmpty()) {
                        isVPS = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if(isVPS && stack.isEmpty()) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
