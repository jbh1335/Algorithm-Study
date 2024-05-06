import java.io.*;
import java.util.*;

public class Main {
    static int L, R, C;
    static char[][][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] dh = {-1, 1};
    static class Point {
        int h, x, y, dist;
        public Point(int h, int x, int y, int dist) {
            this.h = h;
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken()); // 층
            R = Integer.parseInt(st.nextToken()); // 행
            C = Integer.parseInt(st.nextToken()); // 열

            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            int startH = 0, startX = 0, startY = 0;
            for(int l = 0; l < L; l++) {
                for(int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for(int c = 0; c < C; c++) {
                        map[l][r][c] = str.charAt(c);
                        if(map[l][r][c] == 'S') {
                            startH = l;
                            startX = r;
                            startY = c;
                        }
                    }
                }
                br.readLine();
            }

            int answer = bfs(startH, startX, startY);
            if(answer != -1) System.out.println("Escaped in " + answer + " minute(s).");
            else System.out.println("Trapped!");
        }
    }

    public static int bfs(int h, int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(h, x, y, 0));
        boolean[][][] visited = new boolean[L][R][C];
        visited[h][x][y] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();

            // 같은 층의 동서남북 이동
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[p.h][nx][ny]) {
                    // 목적지 도달
                    if(map[p.h][nx][ny] == 'E') return p.dist+1;
                    // 이동 가능하면
                    if(map[p.h][nx][ny] == '.') {
                        que.offer(new Point(p.h, nx, ny, p.dist+1));
                        visited[p.h][nx][ny] = true;
                    }
                }
            }

            // 다른 층의 상하 이동
            for(int d = 0; d < 2; d++) {
                int nh = p.h + dh[d];

                if(nh >= 0 && nh < L && !visited[nh][p.x][p.y]) {
                    if(map[nh][p.x][p.y] == 'E') return p.dist+1;
                    if(map[nh][p.x][p.y] == '.') {
                        que.offer(new Point(nh, p.x, p.y, p.dist+1));
                        visited[nh][p.x][p.y] = true;
                    }
                }
            }
        }
        return -1;
    }
}
