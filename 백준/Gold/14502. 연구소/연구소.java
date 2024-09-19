import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> emptyList;
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

        // 벽을 세울 수 있는 모든 빈칸 저장
        emptyList = new ArrayList<>();
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) emptyList.add(new Point(i, j));
            }
        }

        com(0, 0);
        System.out.println(answer);
    }

    public static void com(int cnt, int start) {
        if(cnt == 3) {
            visited = new boolean[N][M];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == 2 && !visited[i][j]) {
                        virus(i, j);
                    }
                }
            }

            answer = Math.max(answer, countEmpty());
            return;
        }

        for(int i = start; i < emptyList.size(); i++) {
            Point p = emptyList.get(i);
            map[p.x][p.y] = 1;
            com(cnt+1, i+1);
            map[p.x][p.y] = 0;
        }
    }

    public static void virus(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] != 1) {
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    public static int countEmpty() {
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] == 0) count++;
            }
        }

        return count;
    }
}
