import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        int mult = 1, answer = 0;
        boolean flag = true;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(') {
                stack.push(ch);
                mult *= 2;
            } else if(ch == '[') {
                stack.push(ch);
                mult *= 3;
            } else {
                if(stack.isEmpty()) {
                    flag = false;
                    break;
                }

                if(stack.peek() == '(' && ch == ')') {
                    if(str.charAt(i-1) == '(') {
                        answer += mult;
                    }
                    stack.pop();
                    mult /= 2;
                } else if(stack.peek() == '[' && ch == ']'){
                    if(str.charAt(i-1) == '[') {
                        answer += mult;
                    }
                    stack.pop();
                    mult /= 3;
                } else {
                    flag = false;
                    break;
                }
            }
        }

        if(flag && stack.isEmpty()) System.out.println(answer);
        else System.out.println(0);
    }
}
