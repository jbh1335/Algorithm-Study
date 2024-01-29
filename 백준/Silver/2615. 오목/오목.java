import java.io.*;
import java.util.*;

public class Main {
    static int answer, leftX, leftY;
    static int[][] map;
    static int[] dx = {0, 1, 1, -1}; // 좌 하 오아 오위
    static int[] dy = {1, 0, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[19][19];
        for(int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                if(answer > 0) break;
                if(map[i][j] != 0) {
                    for(int d = 0; d < 4; d++) {
                        playGame(1, i, j, map[i][j], d);
                    }
                }
            }
        }

        if(answer == 0) System.out.println(0);
        else {
            System.out.println(answer);
            System.out.println((leftX+1) + " " + (leftY+1));
        }
    }

    static public void playGame(int cnt, int x, int y, int color, int dir) {
        // 같은 색 5개 찾음
        if(cnt == 5) {
            boolean isSix = false;
            // 그 다음이 같은색인지 확인 (6개이면 안되므로)
            int a = x + dx[dir];
            int b = y + dy[dir];
            if(rangeOk(a, b)) {
                if(map[a][b] == color) isSix = true;
            }

            // 처음 시작할때 하나 전도 같은 색인지 확인 (6개이면 안되므로)
            a = x - 5*dx[dir];
            b = y - 5*dy[dir];
            if(!isSix && rangeOk(a, b)) {
                if(map[a][b] == color) isSix = true;
            }

            // 5개이므로 승리
            if(!isSix) {
                answer = color;
                // 가장 왼쪽에 있는 인덱스 찾기
                if(dir == 0) {
                    leftX = x;
                    leftY = y - 4;
                } else if(dir == 1) {
                    leftX = x - 4;
                    leftY = y;
                } else if(dir == 2) {
                    leftX = x - 4;
                    leftY = y - 4;
                } else if(dir == 3) {
                    leftX = x + 4;
                    leftY = y - 4;
                }
            }
            return;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(rangeOk(nx, ny) && map[nx][ny] == color) {
            playGame(cnt+1, nx, ny, color, dir);
        }
    }

    static public boolean rangeOk(int x, int y) {
        if(x >= 0 && y >= 0 && x < 19 && y < 19) return true;
        return false;
    }
}
