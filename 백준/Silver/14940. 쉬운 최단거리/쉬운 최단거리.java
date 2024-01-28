import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        int startX = 0, startY = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] == 1) System.out.print("-1 ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;
        map[x][y] = 0;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(map[nx][ny] == 1) {
                        que.offer(new Point(nx, ny));
                        map[nx][ny] = map[p.x][p.y] + 1;
                    }
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
