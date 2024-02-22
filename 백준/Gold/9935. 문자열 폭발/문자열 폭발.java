import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();
        int bombLength = bomb.length();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            if(stack.size() < bombLength) continue;

            // 폭발 문자열의 마지막이 같으면 검사
            if(stack.peek() == bomb.charAt(bombLength-1)) {
                StringBuilder sb = new StringBuilder();
                for(int j = stack.size()-bombLength; j < stack.size(); j++) {
                    sb.append(stack.get(j));
                }

                if(sb.toString().equals(bomb)) {
                    int count = bombLength;
                    while(count-- > 0) {
                        stack.pop();
                    }
                }
            }
        }

        String answer = "FRULA";
        StringBuilder sb = new StringBuilder();
        if(!stack.isEmpty()) {
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            answer = sb.reverse().toString();
        }

        System.out.println(answer);
    }
}
