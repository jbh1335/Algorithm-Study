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
        int cheeze = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheeze++;
            }
        }

        int time = 0, count = 0, sum = 0;
        while(true) {
            count = bfs(0, 0);
            time++;
            sum += count;
            if(sum == cheeze) break;
        }
        System.out.println(time);
        System.out.println(count);
    }

    // 치즈 영역 찾기
    public static int bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited = new boolean[N][M];
        visited[x][y] = true;
        int count = 0;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(map[nx][ny] == 1) {
                        count++;
                        map[nx][ny] = 0;
                    } else {
                        que.offer(new Point(nx, ny));
                    }
                    visited[nx][ny] = true;
                }
            }
        }
        return count;
    }
}
