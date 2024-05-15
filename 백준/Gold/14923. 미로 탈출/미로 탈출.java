import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, dist, useMagic;
        public Point(int x, int y, int dist, int useMagic) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.useMagic = useMagic;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) - 1;
        int startY = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int endX = Integer.parseInt(st.nextToken()) - 1;
        int endY = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(startX, startY, endX, endY));
    }

    public static int bfs(int startX, int startY, int endX, int endY) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny][p.useMagic]) {
                    // 도착
                    if(nx == endX && ny == endY) return p.dist + 1;

                    if(map[nx][ny] == 0) { // 빈칸
                        que.offer(new Point(nx, ny, p.dist+1, p.useMagic));
                        visited[nx][ny][p.useMagic] = true;
                    } else { // 벽
                        // 마법 지팡이를 사용한적 없으면
                        if(p.useMagic == 0) {
                            que.offer(new Point(nx, ny, p.dist+1, 1));
                            visited[nx][ny][1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
