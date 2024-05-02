import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'L') {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }
        System.out.println(answer);
    }

    public static int bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        // (x, y)에서 시작해서 그 땅의 끝지점까지 가는데 가장 긴 거리
        int maxDist = 0;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] == 'L') {
                        que.offer(new Point(nx, ny, p.dist+1));
                        visited[nx][ny] = true;
                        maxDist = Math.max(maxDist, p.dist+1);
                    }
                }
            }
        }

        return maxDist;
    }
}
