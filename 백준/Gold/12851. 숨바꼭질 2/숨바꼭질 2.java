import java.io.*;
import java.util.*;

public class Main {
    static int N, K, time, count;
    static boolean isFound;
    static int[] distance;
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

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N == K) {
            count = 1;
        } else if(N > K) {
            time = N - K;
            count = 1;
        } else {
            distance = new int[K*2];
            Arrays.fill(distance, Integer.MAX_VALUE);
            bfs();
        }
        System.out.println(time);
        System.out.println(count);
    }

    public static void bfs() {
        PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        pque.offer(new Point(N, 0));
        distance[N] = 0;

        while(!pque.isEmpty()) {
            Point p = pque.poll();
            if(time > 0) {
                if(p.dist == time) {
                    if(p.x == K) {
                        count++;
                    }
                    continue;
                } else if(p.dist > time) {
                    return;
                }
            }

            int[] dx = {-1, 1, p.x};
            for(int d = 0; d < 3; d++) {
                int nx = p.x + dx[d];

                if(nx >= 0 && nx < 2*K) {
                    int dist = p.dist + 1;
                    if(nx == K) {
                        time = dist;
                        count++;
                    } else if(dist <= distance[nx]) {
                        distance[nx] = dist;
                        pque.offer(new Point(nx, dist));
                    }
                }
            }
        }
    }
}
