import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, newMap;
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
        newMap = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = newMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while(true) {
            // 바닷물(0)에 접하는 수 만큼 높이 감소
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] > 0) {
                        int count = 0;
                        // 상하좌우로 주변에 바다있는지 확인
                        for(int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if(map[nx][ny] == 0) count++;
                        }

                        // 높이 감소
                        newMap[i][j] -= count;
                        if(newMap[i][j] < 0) newMap[i][j] = 0;
                    }
                }
            }

            // 1년 후 감소된 높이 적용
            copyMap();
            answer++;

            // 두 덩어리 이상으로 분리되었는지 확인
            visited = new boolean[N][M];
            int count = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(!visited[i][j] && map[i][j] > 0) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            if(count >= 2) break;
            if(count == 0) {
                answer = 0;
                break;
            }
        }

        System.out.println(answer);
    }

    public static void bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] > 0) {
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    public static void copyMap() {
        for(int i = 0; i < N; i++) {
            map[i] = newMap[i].clone();
        }
    }
}
