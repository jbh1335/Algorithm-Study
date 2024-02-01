import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        int answer = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(') {
                stack.push(ch);
            } else {
                stack.pop();
                // 레이저 쏘기
                if(str.charAt(i-1) == '(') answer += stack.size();
                // 쇠막대기가 끝남
                else answer++;
            }
        }

        System.out.println(answer);
    }
}
