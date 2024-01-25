import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map, newMap;
    static int[] dx = {1, 0, -1, 0}; // 하우상좌
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 회전 수

        map = new int[N][M]; // N*M 배열
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r = 1; r <= R; r++) {
            rotate();
            makeNewMap();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 배열 회전하기
    public static void rotate() {
        // 사각형(테두리)이 몇개 있는지
        int square = Math.min(N, M) / 2;
        // 만약 N, M의 작은 값이 홀수라면 가운데 사각형은 한줄이 됨
        boolean odd = false;
        if(Math.min(N, M) % 2 == 1) {
            odd = true;
            square++;
        }
        // 회전하면서 새로운 값을 저장할 배열
        newMap = new int[N][M];

        for(int i = 0; i < square; i++) {
            int x = i, y = i; // 첫사각형 (0, 0), 그다음 (1, 1), (2, 2)... 로 시작

            for(int d = 0; d < 4; d++) {
                while(true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        // 자기 사각형 범위를 벗어나면 안됨
                        if(newMap[nx][ny] != 0) {
                            // 홀수는 마지막 사각형이 한줄이기 때문에 회전을 하지 않음 -> 시작하는 값에 마지막값을 넣어줘야함
                            if(odd && i == square-1) newMap[i][i] = map[x][y];
                            break;
                        }
                        newMap[nx][ny] = map[x][y];
                        x = nx;
                        y = ny;
                    } else { // 범위 벗어나면 방향 전환
                        break;
                    }
                }
            }
        }
    }

    // 회전한 배열 저장
    public static void makeNewMap() {
        for(int i = 0; i < N; i++) {
            map[i] = newMap[i].clone();
        }
    }
}
