import java.io.*;
import java.util.*;

/*
    토네이도를 시전하면 가운데 칸부터 이동하고 한번에 한칸씩 이동한다.
    토네이도가 한 칸 이동할 떄마다 모래는 정해진 일정한 비율로 흩날리게 된다.
    비율은 소수점 아래는 버리고, a는 이동하지 않고 남은 모래의 양만큼 이동한다.
    토네이도가 이동하는 방향에 따라 정해진 비율은 그에 맞게 회전하여 이동한다.
    토네이도가 (0, 0)까지 이동하면 격자의 밖으로 나간 모래의 양을 구해보자.
 */
public class Main {
    static int N, answer;
    static int[][] map;
    static double[][] sandRate = {
                                {0, 0, 0.02, 0, 0},
                                {0, 0.1, 0.07, 0.01, 0},
                                {0.05, 100, 0, 0, 0},
                                {0, 0.1, 0.07, 0.01, 0},
                                {0, 0, 0.02, 0, 0}};
    static int[] dx = {0, 1, 0, -1}; // 좌하우상
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N*N 격자판
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도 이동
        // 1 1 2 2 3 3 4 4 .. N-1 N-1 N N N 칸씩 이동하면 (0, 0) 도착
        int dir = 0, count = 1, num = 0;
        int x = N / 2, y = N / 2;
        while(true) {
            // 몇칸 이동하는지는 2번씩 반복함 (마지막 제외)
            for(int i = 0; i < 2; i++) {
                // 같은 방향으로 count만큼 이동
                for(int j = 0; j < count; j++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // 이동
                    move(nx, ny);
                    x = nx;
                    y = ny;
                }
                // 방향 전환
                if(++dir == 4) dir = 0;
                rotate();
            }
            if(++count == N) break;
        }

        // 마지막은 그 방향으로 한번더
        for(int j = 0; j < count-1; j++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 이동
            move(nx, ny);
            x = nx;
            y = ny;
        }
        System.out.println(answer);
    }

    // 모래 이동
    public static void move(int x, int y) {
        // 모래 비율의 위치
        int sandX = 0, sandY = -1;
        // 이동해야하는 모래 양
        int sand = map[x][y];
        // 비율만큼 이동한 모래 양
        int movedSand = 0;
        // a의 위치
        int aX = 0, aY = 0;

        for(int i = x-2; i <= x+2; i++) {
            for(int j = y-2; j <= y+2; j++) {
                sandY++;
                // a의 위치
                if(sandRate[sandX][sandY] == 100) {
                    aX = i;
                    aY = j;
                    continue;
                }

                if(i >= 0 && j >= 0 && i < N && j < N) { // 범위 안에 들어오면
                    // 비율 없는 곳은 이동 X
                    if(sandRate[sandX][sandY] == 0) continue;
                    // 해당 비율만큼 이동되는 모래
                    int newSand = (int) (sand * sandRate[sandX][sandY]);
                    // 그 위치에 원래있던 모래 + 새로운 모래
                    map[i][j] += newSand;
                    // 이동되는 모래 양 누적
                    movedSand += newSand;
                } else { // 범위 벗어나면
                    int newSand = (int) (sand * sandRate[sandX][sandY]);
                    movedSand += newSand;
                    // 격자판 벗어난 모래 저장
                    if(newSand > 0) answer += newSand;
                }
            }
            sandX++;
            sandY = -1;
        }

        // a는 이동하지 않고 남은 모래의 양
        int leftSand = sand - movedSand;
        if(aX >= 0 && aY >= 0 && aX < N && aY < N) {
            map[aX][aY] += leftSand;
        } else {
            answer += leftSand;
        }

        // 모래가 모두 이동했으니 0
        map[x][y] = 0;
    }

    // 모래 비율판 회전
    public static void rotate() {
        // 방향이 바뀔 때마다 왼쪽으로 90도 회전
        double[][] newSandRate = new double[5][5];
        int idxX = 0, idxY = 0;
        for(int j = 0; j < 5; j++) {
            for(int i = 4; i >= 0; i--) {
                newSandRate[i][j] = sandRate[idxX][idxY++];
            }
            idxX++;
            idxY = 0;
        }

        for(int i = 0; i < 5; i++) {
            sandRate[i] = newSandRate[i].clone();
        }
    }
}
