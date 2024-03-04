import java.io.*;
import java.util.*;

/*
    N*M 지도에 주사위가 있음
    지도의 각 칸에는 정수가 있는데 주사위를 굴렸을 때, 이동한 칸에 있는 수에 따라 변함
    1. 이동한 칸이 0인 경우: 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
    2. 이동한 칸이 0이 아닌 경우: 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
    주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 구하자
    만약 주사위가 지도 바깥으로 이동하면 명령 무시하고 출력 X
        2
      5 1 6
        4
        3    <- 주사위: 1을 바닥 3을 하늘로 두고 접는다고 생각
 */
public class Main {
    static int N, M, K;
    static int[] dice;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행열
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 시작위치
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        // 명령의 개수
        K = Integer.parseInt(st.nextToken());

        // 지도에 값 저장
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령 수행
        dice = new int[7]; // 주사위
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(checkRange(nx, ny)) {
                move(nx, ny, dir);
                x = nx;
                y = ny;
                System.out.println(dice[3]);
            }
        }
    }

    public static void move(int x, int y, int dir) {
        int movedNum = map[x][y];

        if(dir == 0) { // 우
            // 주사위 이동
            int tmp = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[3];
            dice[3] = dice[5];
            dice[5] = tmp;
        } else if(dir == 1) { // 좌
            int tmp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[6];
            dice[6] = tmp;
        } else if(dir == 2) { // 상
            int tmp = dice[1];
            for(int i = 1; i <= 3; i++) {
                dice[i] = dice[i+1];
            }
            dice[4] = tmp;
        } else { // 하
            int tmp = dice[4];
            for(int i = 4; i >= 2; i--) {
                dice[i] = dice[i-1];
            }
            dice[1] = tmp;
        }

        // 이동한 칸이 0이면
        if(movedNum == 0) {
            // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사됨
            map[x][y] = dice[1];
        } else { // 이동한 칸이 0이 아니면
            // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되고
            dice[1] = movedNum;
            // 칸의 숫자는 0이 됨
            map[x][y] = 0;
        }
    }

    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < N && ny < M) return true;
        return false;
    }
}
