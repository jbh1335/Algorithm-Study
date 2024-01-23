import java.io.*;
import java.util.*;

/*
    땅 X 바다 .
    50년이 지나면 인접한 3 or 4칸에 바다가 있는 땅은 모두 잠겨버림
        -> 상하좌우에서 3 or 4칸이 .이면 잠김 (테두리도 .라 생각)
    50년 후의 지도 모습을 출력 -> 크기는 모든 섬을 포함하는 가장 작은 직사각형 (바다 제거)
 */
public class Main {
    static char[][] map;
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

        int R = Integer.parseInt(st.nextToken()); // 행
        int C = Integer.parseInt(st.nextToken()); // 열
        map = new char[R][C]; // R*C 지도 배열
        visited = new boolean[R][C]; // 방문배열

        // 지도의 모양 저장
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 인접한 바다 찾기
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visited[i][j] && map[i][j] == 'X') bfs(i, j);
            }
        }

        // 가장 작은 땅 만들기
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    minX = Integer.min(minX, i);
                    minY = Integer.min(minY, j);
                    maxX = Integer.max(maxX, i);
                    maxY = Integer.max(maxY, j);
                }
            }
        }

        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    // 인접한 바다 찾기
    public static void bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();
            int count = 0;

            // 상하좌우로 탐색
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
                    if(!visited[nx][ny]) {
                        if(map[nx][ny] == 'X') { // 땅
                            que.offer(new Point(nx, ny));
                            visited[nx][ny] = true;
                        } else { // 바다
                            count++;
                        }
                    }
                } else { // 범위를 벗어나면 바다임
                    count++;
                }
            }

            // 인접한 바다가 3 또는 4개라면 그 땅은 잠겨버림
            if(count >= 3) map[p.x][p.y] = '.';
        }
    }
}
