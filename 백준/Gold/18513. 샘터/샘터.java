import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, dist;
        public Point(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Point> que = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int water = Integer.parseInt(st.nextToken());
            que.offer(new Point(water, 0));
            set.add(water);
        }

        int count = 0;
        long answer = 0; // 거리의 합은 int 범위를 넘을 수 있기 때문에 long으로 해야함
        int[] dx = {-1, 1};
        while(count < K) {
            Point p = que.poll();

            for(int i = 0; i < 2; i++) {
                int nx = p.x + dx[i];

                if(set.add(nx)) {
                    que.offer(new Point(nx, p.dist+1));
                    answer += p.dist+1;
                    if(++count == K) break;
                }
            }
        }
        System.out.println(answer);
    }
}
