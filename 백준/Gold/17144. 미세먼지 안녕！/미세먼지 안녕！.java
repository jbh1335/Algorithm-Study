import java.io.*;
import java.util.*;

/*
    R*C의 격자판에 공기청정기가 있고 (r-1, c-1)칸에는 미세먼지 양(A)이 있음
    공기청정기는 항상 0열에 설치, 크기는 두 행을 차지함
    1초 동안 아래와 같이 진행
    1. 미세먼지가 있는 모든 칸이 동시에 확산함
        1) 인접한 4방향(상하좌우)으로 확산
        2) 인접한 방향에 공기청정기가 있거나 범위 벗어나면 그 방향으로 확산 X
        3) 확산되는 양은 A/5이고 소수점 버림
        4) (r-1, c-1)에 남는 미세먼지 양은 A-(A/5 * 확산된 방향의 개수)
    2. 공기청정기가 작동함
        1) 위쪽 공기청정기의 바람은 반시계 방향으로 순환, 아래쪽은 시계방향으로 순환
        2) 바람이 불면 미세먼지가 바람의 방향대로 한 칸씩 이동
        3) 공기청정기로 들어간 미세먼지는 모두 정화됨
    T초가 지난 후 남아있는 미세먼지의 양을 구해라
 */
public class Main {
    static int R, C, T, airCleanerIdx;
    static int[][] map, newMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행
        C = Integer.parseInt(st.nextToken()); // 열
        T = Integer.parseInt(st.nextToken()); // 초

        map = new int[R][C]; // 초가 끝날때의 배열
        newMap = new int[R][C]; // 1초동안 모든 미세먼지를 계산하는 배열
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아래쪽 공기청정기 행 좌표
                if(map[i][j] == -1) airCleanerIdx = i;
            }
        }

        // T초 동안 확산
        for(int t = 1; t <= T; t++) {
            // 미세먼지 확산
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i][j] > 0) {
                        spreadDust(i, j);
                    }
                }
            }

            // 확산이 끝난 배열 복사
            copyMap();

            // 공기청정기 작동
            airCleaner("up"); // 위쪽
            airCleaner("down"); // 아래쪽
        }

        // 남아있는 미세먼지 양 구하기
        System.out.println(countDust());
    }

    public static int countDust() {
        int sum = 0;
        for(int i= 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0) sum += map[i][j];
            }
        }
        return sum;
    }

    // 공기청정기 작동
    public static void airCleaner(String type) {
        // 이동 방향
        int[] airDx, airDy;
        // 시작 좌표
        int x = 0, y = 0;
        // 행의 시작과 끝 범위
        int start, end;
        if(type.equals("up")) {
            // 위쪽 공기청정기(반시계 방향) -> 반대로 시계방향으로 돌아서 하나씩 이동 (상우하좌)
            airDx = new int[] {-1, 0, 1, 0};
            airDy = new int[] {0, 1, 0, -1};

            x = airCleanerIdx - 1;
            start = 0;
            end = airCleanerIdx;
        } else {
            // 아래쪽 공기청정기(시계 방향) -> 반대로 반시계방향으로 돌아서 하나씩 이동 (하우상좌)
            airDx = new int[] {1, 0, -1, 0};
            airDy = new int[] {0, 1, 0, -1};

            x = airCleanerIdx;
            start = airCleanerIdx;
            end = R;
        }

        for(int d = 0; d < 4; d++) {
            while(true) {
                int nx = x + airDx[d];
                int ny = y + airDy[d];

                if(nx >= start && ny >= 0 && nx < end && ny < C) {
                    // 공기청정기가 있으면 정화되므로 없으면 미세먼지 이동
                    if(map[x][y] != -1) {
                        map[x][y] = map[nx][ny] == -1 ? 0 : map[nx][ny];
                    }
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
            }
        }
    }

    // 인접한 방향으로 미세먼지 확산
    public static void spreadDust(int x, int y) {
        // 확산되는 방향의 개수
        int count = 0;
        // 확산되는 미세먼지 양
        int dust = map[x][y] / 5;
        // 상하좌우 방향
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && ny >= 0 && nx < R && ny < C) {
                // 공기청정기가 없으면
                if(map[nx][ny] != -1) {
                    // 이미 먼지가 있을 수도 있으니 누적
                    newMap[nx][ny] += dust;
                    count++;
                }
            }
        }
        // 현재 칸에 남은 미세먼지 양
        newMap[x][y] += map[x][y] - (dust * count);
    }

    // 1초가 끝나면 미세먼지 상태 저장
    public static void copyMap() {
        for(int i = 0; i < R; i++) {
            map[i] = newMap[i].clone();
        }
        // 공기청정기 위치 저장
        map[airCleanerIdx][0] = map[airCleanerIdx-1][0] = -1;
        newMap = new int[R][C];
    }
}
