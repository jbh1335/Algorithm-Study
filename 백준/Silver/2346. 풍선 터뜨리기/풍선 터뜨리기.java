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

        Deque<Point> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            deque.offer(new Point(Integer.parseInt(st.nextToken()), i+1));
        }

        while(!deque.isEmpty()) {
            Point p = deque.poll();
            System.out.println(p.idx);
            int count = Math.abs(p.num);

            if(deque.isEmpty()) break;
            if(p.num > 0) {
                while(count-- > 1) {
                    deque.offer(deque.poll());
                }
            } else {
                while(count-- > 0) {
                    deque.offerFirst(deque.pollLast());
                }
            }
        }
    }
}
