import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new int[N][N];
            for(int i = 0; i < N; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
            }

            bfs();
            System.out.println("Problem " + tc++ + ": " + visited[N-1][N-1]);
        }
    }

    public static void bfs() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0, map[0][0]));
        visited[0][0] = map[0][0];

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    int distance = p.dist + map[nx][ny];
                    if(distance < visited[nx][ny]) {
                        que.offer(new Point(nx, ny, distance));
                        visited[nx][ny] = distance;
                    }
                }
            }
        }
    }
}
