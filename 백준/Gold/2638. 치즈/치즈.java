import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cheese;
    static int[][] map;
    static int[][] visited;
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
        visited = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese++;
            }
        }

        int answer = 0;
        while(cheese > 0) {
            bfs();
            deleteCheese();
            answer++;
        }
        System.out.println(answer);
    }

    public static void deleteCheese() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j] >= 2) {
                    map[i][j] = 0;
                    cheese--;
                }
                visited[i][j] = 0;
            }
        }
    }

    public static void bfs() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0));
        visited[0][0] = -1;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] != -1) {
                    if(map[nx][ny] == 1) {
                        visited[nx][ny]++;
                    } else {
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = -1;
                    }
                }
            }
        }
    }
}
