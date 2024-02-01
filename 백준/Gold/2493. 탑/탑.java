import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int num, idx;
        public Point(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Point> stack = new Stack<>();
        int[] answer = new int[N+1];
        for(int i = 1; i <= N; i++) {
            if(stack.isEmpty()) {
                stack.push(new Point(arr[i], i));
                continue;
            }

            if(stack.peek().num > arr[i]) {
                answer[i] = stack.peek().idx;
            } else {
                while(!stack.isEmpty()) {
                    Point p = stack.peek();
                    if(p.num > arr[i]) {
                        answer[i] = p.idx;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            stack.push(new Point(arr[i], i));
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
