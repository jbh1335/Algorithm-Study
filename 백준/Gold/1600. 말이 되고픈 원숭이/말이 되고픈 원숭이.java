import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] horseDx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] horseDy = {1, 2, 2, 1, -1, -2, -2, -1};
    static class Point {
        int x, y, dist, horseCnt;
        public Point(int x, int y, int dist, int horseCnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.horseCnt = horseCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); // 열
        H = Integer.parseInt(st.nextToken()); // 행
        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();
            if(p.x == H-1 && p.y == W-1) return p.dist;

            // 말처럼 이동
            for(int d = 0; d < 8; d++) {
                int nx = p.x + horseDx[d];
                int ny = p.y + horseDy[d];

                if(checkRange(nx, ny)) {
                    if(p.horseCnt+1 > K) continue;
                    if(!visited[nx][ny][p.horseCnt+1] && map[nx][ny] == 0) {
                        que.offer(new Point(nx, ny, p.dist+1, p.horseCnt+1));
                        visited[nx][ny][p.horseCnt+1] = true;
                    }
                }
            }

            // 원숭이처럼 이동
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(checkRange(nx,ny)) {
                    if(!visited[nx][ny][p.horseCnt] && map[nx][ny] == 0) {
                        que.offer(new Point(nx, ny, p.dist+1, p.horseCnt));
                        visited[nx][ny][p.horseCnt] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < H && ny < W) return true;
        return false;
    }
}
