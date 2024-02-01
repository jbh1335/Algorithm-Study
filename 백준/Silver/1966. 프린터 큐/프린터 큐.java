import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static class Point {
        int num, idx;
        public Point(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solve());
        }


    }

    public static int solve() {
        Queue<Point> que = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            que.offer(new Point(arr[i], i));
        }

        Arrays.sort(arr);
        int arrIdx = arr.length - 1, count = 0;
        int max = arr[arrIdx];

        while(!que.isEmpty()) {
            Point p = que.poll();

            if(p.num < max) {
                que.offer(new Point(p.num, p.idx));
            } else {
                count++;
                if(p.idx == M) return count;
                max = arr[--arrIdx];
            }
        }

        return -1;
    }
}
