import java.io.*;
import java.util.*;

public class Main {
    static int N, M, noWallCnt, answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Point[] select;
    static ArrayList<Point> virusList;
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

        map = new int[N][N];
        virusList = new ArrayList<>(); // 바이러스를 놓을 수 있는 곳 저장
        select = new Point[M]; // 바이러스를 M개 놓은 위치

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virusList.add(new Point(i, j));
                if(map[i][j] != 1) noWallCnt++;
            }
        }

        com(0, 0);
        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    public static void com(int cnt, int start) {
        if(cnt == M) {
            virus();
            return;
        }

        for(int i = start; i < virusList.size(); i++) {
            select[cnt] = virusList.get(i);
            com(cnt+1, i+1);
        }
    }

    public static void virus() {
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(Point p : select) {
            que.offer(new Point(p.x, p.y));
            visited[p.x][p.y] = true;
        }

        int count = M, time = -1;
        while(!que.isEmpty()) {
            int size = que.size();

            for(int s = 0; s < size; s++) {
                Point p = que.poll();

                for(int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        if(!visited[nx][ny] && map[nx][ny] != 1) {
                            que.offer(new Point(nx, ny));
                            visited[nx][ny] = true;
                            count++;
                        }
                    }
                }
            }

            time++;
        }

        if(noWallCnt == count) answer = Math.min(answer, time);
    }
}
