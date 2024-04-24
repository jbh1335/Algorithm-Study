import java.io.*;
import java.util.*;

/*
    4*4 크기의 공간에 각 칸에 물고기 한마리가 방향과 함께 존재함
    번호는 1~16이고 방향은 8가지(상하좌우, 대각선)
    상어는 (0, 0)에서 시작 -> 해당 칸에 있는 물고기를 먹고 그 방향으로 적용
    <물고기 이동>
    1. 번호가 작은 물고기부터 순서대로 이동한다.
    2. 물고기는 해당 방향으로 한 칸 이동하고, 빈칸과 다른 물고기가 있는 칸으로 이동할 수 있다. -> 상어 있거나 경계 넘으면 불가능
    3. 이동을 하지 못하면 가능할 때까지 45도 반시계 회전한다. -> 이동할 수 있는 칸이 아예 없으면 이동 X
    4. 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾼다.
    <상어 이동>
    1. 해당 방향에 있는 칸으로 모두 이동 가능 (이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않음)
    2. 물고기가 없는 칸으로는 이동할 수 없다. (바로 멈추는게 아니라 그 다음 칸 검사해야함!)
    3. 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고 해당 방향을 가지게 된다.
    4. 상어가 이동할 수 있는 칸이 없을 때까지 과정을 반복한다.
    상어가 먹을 수 있는 물고기 번호 합의 최댓값 구하기
 */
public class Main {
    static int max;
    static boolean[] visited;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static class Point {
        int num, dir;
        public Point(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Point[][] map = new Point[4][4];
        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                // 물고기: 1 ~ 16, 상어: -1, 빈칸(물고기 없음): 0
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = new Point(num, dir);
            }
        }

        // 물고기를 방문해서 먹었는지 저장하는 배열
        visited = new boolean[17];
        // 상어는 처음에 (0, 0)에서 시작
        int eatFist = map[0][0].num;
        int dir = map[0][0].dir;
        visited[eatFist] = true;
        map[0][0].num = -1;

        // 물고기 이동 후 상어 이동
        moveFish(map);
        moveShark(0, 0, dir, eatFist, map);

        System.out.println(max);
    }

    // 상어 이동
    public static void moveShark(int x, int y, int dir, int eatFish, Point[][] map) {
        int nx = x, ny = y;
        while(true) {
            nx += dx[dir];
            ny += dy[dir];

            // 경계를 벗어나지 않으면
            if(checkRange(nx, ny)) {
                if(map[nx][ny].num > 0) { // 물고기가 있을 때만 이동
                    // 물고기 먹음
                    int fishNum = map[nx][ny].num;
                    eatFish += fishNum;
                    visited[fishNum] = true;
                    // 기존에 있던 상어 제거 -> 0 (빈칸)
                    map[x][y].num = 0;

                    // 상어가 이동한 위치에 따라 물고기 배열이 다르므로 복사
                    Point[][] newMap = copyMap(map);

                    // 먹은 곳에 상어가 위치함
                    newMap[nx][ny].num = -1;
                    // 물고기 이동
                    moveFish(newMap);

                    // 상어 이동
                    moveShark(nx, ny, map[nx][ny].dir, eatFish, newMap);
                    // 먹은 물고기 되돌리기
                    eatFish -= fishNum;
                    visited[fishNum] = false;
                }
            } else {
                // 더 이상 이동할 곳 없으면 최댓값 구하기
                max = Math.max(max, eatFish);
                return;
            }
        }
    }

    // 물고기 이동
    public static void moveFish(Point[][] newMap) {
        // 번호가 작은 물고기부터 순서대로 이동한다.
        for(int fishNum = 1; fishNum <= 16; fishNum++) {
            // 물고기가 상어에게 먹혀서 없으면 건너뛰기
            if(visited[fishNum]) continue;
            // 물고기의 위치 찾기
            int[] arr = findFish(fishNum, newMap);
            // 물고기의 방향
            int dir = newMap[arr[0]][arr[1]].dir;
            int nx = 0, ny = 0;
            boolean isAble = false;
            // 물고기가 이동할 수 있는지 확인 (8번 회전해서 원래 방향으로 오면 이동 X)
            for(int i = 0; i < 8; i++) {
                nx = arr[0] + dx[dir];
                ny = arr[1] + dy[dir];
                // 경계 넘지 않고 상어가 없으면 이동 가능
                if(checkRange(nx, ny) && newMap[nx][ny].num != -1) {
                    isAble = true;
                    break;
                }
                // 반시계 방향으로 45도 회전
                if(++dir == 8) dir = 0;
            }

            // 이동 불가능하면 건너뛰기
            if(!isAble) continue;
            // 물고기 이동
            if(newMap[nx][ny].num == 0) { // 빈칸이면 그냥 이동
                newMap[nx][ny] = new Point(fishNum, dir);
                newMap[arr[0]][arr[1]] = new Point(0, 0);
            } else { // 다른 물고기가 있으면 위치 변경
                int tmpFish = newMap[nx][ny].num;
                int tmpDir = newMap[nx][ny].dir;
                newMap[nx][ny] = new Point(fishNum, dir);
                newMap[arr[0]][arr[1]] = new Point(tmpFish, tmpDir);
            }
        }
    }

    // 물고기 번호의 위치 찾기
    public static int[] findFish(int num, Point[][] newMap) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(newMap[i][j].num == num) return new int[] {i, j};
            }
        }
        return new int[] {-1, -1};
    }

    // 범위 확인
    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < 4 && ny < 4) return true;
        return false;
    }

    // 이차원 배열 복사
    public static Point[][] copyMap(Point[][] map) {
        Point[][] newMap = new Point[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                newMap[i][j] = new Point(map[i][j].num, map[i][j].dir);
            }
        }
        return newMap;
    }
}
