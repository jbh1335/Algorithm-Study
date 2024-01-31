import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            if(str.indexOf("push") != -1) {
                String[] splitArr = str.split(" ");
                stack.push(Integer.parseInt(splitArr[1]));
            } else if(str.equals("top")) {
                if(stack.isEmpty()) System.out.println(-1);
                else System.out.println(stack.peek());
            } else if(str.equals("size")) {
                System.out.println(stack.size());
            } else if(str.equals("empty")) {
                if(stack.isEmpty()) System.out.println(1);
                else System.out.println(0);
            } else if(str.equals("pop")) {
                if(stack.isEmpty()) System.out.println(-1);
                else System.out.println(stack.pop());
            }
        }
    }
}
