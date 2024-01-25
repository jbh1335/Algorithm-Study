import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0}; // 하우상좌
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 회전 수

        map = new int[N][M]; // N*M 배열
        visited = new boolean[N][M]; // 방문 배열
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 사각형(테두리)이 몇개 있는지
        int square = Math.min(N, M) / 2;
        for(int i = 0; i < square; i++) {
            rotateOneSquare(i, i);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 사각형 하나를 회전
    public static void rotateOneSquare(int startX, int startY) {
        int x = startX, y = startY;
        // (height-1 + width-1) * 2번을 회전하면 처음으로 돌아옴 -> R번에서 그만큼 나눈 나머지만 화전하면됨
        int height = N - 2*x, width = M - 2*y;
        int newR = R % ((height-1+width-1) * 2);
        // 회전하면서 새로운 값을 저장할 배열
        int[][] newMap = new int[N][M];

        for(int i = 1; i <= newR; i++) {
            for(int d = 0; d < 4; d++) {
                while(true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        // 자기 사각형 범위를 벗어나면 안됨
                        if(visited[nx][ny]) break;
                        newMap[nx][ny] = map[x][y];
                        x = nx;
                        y = ny;
                    } else { // 범위 벗어나면 방향 전환
                        break;
                    }
                }
            }
            makeNewMap(newMap);
        }
        makeVisited(startX, startY);
    }

    // 회전한 배열 저장
    public static void makeNewMap(int[][] newMap) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(newMap[i][j] != 0) map[i][j] = newMap[i][j];
            }
        }
    }

    // 하나의 사각형이 회전이 끝났으면 방문처리
    public static void makeVisited(int x, int y) {
        for(int d = 0; d < 4; d++) {
            while(true) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
            }
        }
    }
}
