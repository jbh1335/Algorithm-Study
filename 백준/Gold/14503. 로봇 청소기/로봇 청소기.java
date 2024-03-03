import java.io.*;
import java.util.*;

/*
    처음에 빈칸은 전부 청소 X
    1. 현재 칸이 아직 청소되지 않은 경우 청소함
    2. 현재 칸의 주변 4칸(상하좌우) 중 청소되지 않은 빈칸이 없는 경우
        1) 현재 방향을 유지하고 한칸 후진할 수 있으면 후진후, 1번으로 돌아감
        2) 뒤쪽 칸이 벽이라 후진 못하면 작동 멈춤
    3. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는 경우
        1) 반시계 방향으로 90도 회전함
        2) 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸이면 한칸 전진함
        3) 1번으로 돌아감
    로봇 청소기가 작동을 시작하고 멈출 때까지 청소하는 칸의 개수 구하기
 */
public class Main {
    static int N, M;
    static int[][] map;
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

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        st = new StringTokenizer(br.readLine());
        // 시작 위치
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        // 현재 바라보는 방향 -> 0: 상, 1: 우, 2: 하, 3: 좌
        int dir = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while(true) {
            // 1. 현재 칸이 청소되지 않으면 청소함
            if(map[x][y] == 0) {
                map[x][y] = -1;
                answer++;
            }

            // 2. 주변이 모두 청소되어 있으면
            if(checkClean(x, y)) {
                // 현재 방향을 유지하고 한칸 후진할 수 있는지 확인
                int[] arr = moveBack(x, y, dir);
                // 이동할 수 없으면 멈춤
                if(arr[0] == -1) break;
                else { // 이동할 수 있으면
                    // 이동하고 1번으로 돌아감
                    x = arr[0];
                    y = arr[1];
                }
            } else { // 3. 주변이 청소되어있지 않으면
                // 반시계 방향으로 회전
                if(dir == 0) dir = 3;
                else dir--;

                // 앞쪽 칸이 청소되어있지 않으면 전진
                int[] arr = moveFront(x, y, dir);
                if(arr[0] != -1) {
                    x = arr[0];
                    y = arr[1];
                }
            }
        }
        System.out.println(answer);
    }

    // 전진
    public static int[] moveFront(int x, int y, int dir) {
        int[] arr = new int[2];

        if(dir == 0) { // 상
            x--;
        } else if(dir == 1) { // 우
            y++;
        } else if(dir == 2) { // 하
            x++;
        } else { // 좌
            y--;
        }

        if(checkRange(x, y) && map[x][y] == 0) {
            return new int[] {x, y};
        }
        return new int[] {-1, -1};
    }

    // 후진
    public static int[] moveBack(int x, int y, int dir) {
        // 0: 상, 1: 우, 2: 하, 3: 좌
        int[] arr = new int[2];

        if(dir == 0) { // 상
            x++;
        } else if(dir == 1) { // 우
            y--;
        } else if(dir == 2) { // 하
            x--;
        } else { // 좌
            y++;
        }

        // 범위 괜찮고 벽이 아니라 이동할 수 있으면
        if(checkRange(x, y) && map[x][y] != 1) {
            return new int[] {x, y};
        }
        return new int[] {-1, -1};
    }

    // 주변에 청소되지 않은 곳이 있는지 확인
    public static boolean checkClean(int x, int y) {
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(checkRange(nx, ny)) {
                // 청소되지 않은 곳 있으면 false
                if(map[nx][ny] == 0) return false;
            }
        }
        // 모두 청소되어있으면 true
        return true;
    }

    // 범위 체크
    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < N && ny < M) return true;
        return false;
    }
}
