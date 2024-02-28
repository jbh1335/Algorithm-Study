import java.io.*;
import java.util.*;

public class Main {
    static int N, M, r, c, startX, startY, finishX, finishY;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> wallList = new ArrayList<>();
    static class Point {
        int x, y, dist;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 격자판 행
        M = Integer.parseInt(st.nextToken()); // 격자판 열

        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) wallList.add(new Point(i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        // 직사각형의 행열
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        // 직사각형이 현재 있는 좌표
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;
        // 직사각형이 도착해야하는 좌표
        finishX = Integer.parseInt(st.nextToken()) - 1;
        finishY = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(checkMove(nx, ny) && !visited[nx][ny]) {
                    if(nx == finishX && ny == finishY) return p.dist+1;
                    que.offer(new Point(nx, ny, p.dist+1));
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }

    // 좌표를 이동하는 것이 가능한지 확인
    public static boolean checkMove(int x, int y) {
        if(x >= 0 && y >= 0 && x+r <= N && y+c <= M) {
            for(Point p : wallList) {
                if(x <= p.x && p.x < x+r && y <= p.y && p.y < y+c) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
