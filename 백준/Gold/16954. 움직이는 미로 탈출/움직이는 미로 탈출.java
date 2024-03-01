import java.io.*;
import java.util.*;

/*
    8*8 체스판에서 탈출하기 -> (7, 0)에서 (0, 7)로 이동할 수 있는지 찾기
    1초 동안 욱제가 먼저 이동하고 벽이 이동함 -> 벽이 욱제가 있는 칸으로 이동하면 욱제 사라짐
    욱제는 1초 후 상하좌우, 대각선, 현재 위치로 이동함 (빈칸만 가능)
    1초 후 모든 벽은 아래에 있는 행으로 한 칸씩 내려감 -> 가장 아래는 걍 사라짐
 */
public class Main {
    static boolean exist;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1}; // 상하좌우, 왼위, 오위, 왼아, 오아
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static Queue<Point> que;
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];
        visited = new boolean[8][8];

        for(int i = 0; i < 8; i++) {
            String str = br.readLine();
            for(int j = 0; j < 8; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        que = new LinkedList<>();
        que.offer(new Point(7, 0));
        map[7][0] = 'P'; // 욱제
        int answer = 0;
        while(true) {
            // 목적지에 도착했으면 끝
            if(movePerson()) {
                answer = 1;
                break;
            }

            // 만약 더 이상 갈 곳이 없으면 끝
            if(!exist) break;
            // 벽 내리기
            moveWall();
        }
        System.out.println(answer);
    }

    // 모든 벽이 아래행으로 한 칸씩 이동
    public static void moveWall() {
        for(int j = 0; j < 8; j++) {
            // 마지막 행은 원래 벽이었으면 더이상 내려갈 곳이 없어서 빈칸으로 바꿈
            if(map[7][j] == '#') map[7][j] = '.';
            for(int i = 7; i >= 1; i--) {
                if(map[i-1][j] == '#') {
                    map[i][j] = '#';
                    map[i-1][j] = '.';
                }
            }
        }
    }

    // 욱제 이동
    public static boolean movePerson() {
        int size = que.size();
        exist = false;
        visited = new boolean[8][8];

        for(int s = 0; s < size; s++) {
            Point p = que.poll();
            // 만약 이전에 욱제가 간곳에 벽이 이동했다면 건너뜀
            if(map[p.x][p.y] == '#') continue;
            visited[p.x][p.y] = true;

            for(int d = 0; d < 8; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < 8 && ny < 8) {
                    if(nx == 0 && ny == 7) return true;
                    if(!visited[nx][ny] && map[nx][ny] == '.') {
                        que.offer(new Point(nx, ny));
                        map[nx][ny] = 'P';
                        visited[nx][ny] = true;
                        exist = true;
                    }
                }
            }
            // 현재 위치 추가
            que.offer(new Point(p.x, p.y));
        }
        return false;
    }
}
