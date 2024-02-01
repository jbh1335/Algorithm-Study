import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int num = 1, idx = 0;
        boolean flag = true;
        while(idx < N) {
            if(stack.isEmpty() || stack.peek() < arr[idx]) {
                stack.push(num++);
                sb.append("+ \n");
            } else if(stack.peek() == arr[idx]) {
                stack.pop();
                sb.append("- \n");
                idx++;
            } else {
                flag = false;
                break;
            }
        }

        if(flag) System.out.println(sb);
        else System.out.println("NO");
    }
}
