import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        HashMap<Character, Double> map = new HashMap<>();
        char order = 'A';
        for(int i = 0; i < N; i++) {
            map.put(order++, Double.valueOf(br.readLine()));
        }

        Stack<Double> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(map.containsKey(ch)) {
                stack.push(map.get(ch));
            } else {
                double num2 = stack.pop();
                double num1 = stack.pop();
                stack.push(calculate(num1, num2, ch));
            }
        }

        System.out.println(String.format("%.2f", stack.pop()));
    }

    public static double calculate(double num1, double num2, char operator) {
        if(operator == '+') {
            return num1 + num2;
        } else if(operator == '-') {
            return num1 - num2;
        } else if(operator == '*') {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
}
