import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, answer = -1;
    static boolean isFound;
    static Point[] select;
    static int[][] map;
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

        N = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 가로선 개수
        H = Integer.parseInt(st.nextToken()); // 행

        // 현재 있는 가로선 위치
        map = new int[H][N-1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a-1][b-1] = 1;
        }

        for(int i = 0; i <= 3; i++) {
            if(isFound) break;
            select = new Point[i];
            com(0, 0, i);
        }
        System.out.println(answer);
    }

    // 가로선을 조합으로 num개 뽑기
    public static void com(int cnt, int start, int num) {
        if(isFound) return;
        if(cnt == num) {
            // 뽑은 위치로 가로선 만들고
            for(Point p : select) {
                map[p.x][p.y] = 1;
            }

            // 조건에 맞게 이동되는지 확인
            if(moveLadder()) {
                isFound = true;
                answer = num;
            }

            // 끝났으면 돌려놓기
            for(Point p : select) {
                map[p.x][p.y] = 0;
            }
            return;
        }

        for(int i = start; i < H*(N-1); i++) {
            if(isFound) return;
            int x = i / (N-1);
            int y = i % (N-1);

            // 이미 가로선 있으면 건너뜀
            if(map[x][y] == 1) continue;
            select[cnt] = new Point(x, y);
            com(cnt+1, i+1, num);
        }
    }

    // 사다리 이동
    public static boolean moveLadder() {
        for(int j = 0; j < N; j++) {
            int idx = j;
            for(int i = 0; i < H; i++) {
                // 오른쪽으로 연결
                if(idx+1 < N && map[i][idx] == 1) idx++;
                // 왼쪽으로 연결
                else if(idx-1 >= 0 && map[i][idx-1] == 1) idx--;
            }
            // 도착했는데 자신의 위치에 있지 않다면 실패
            if(idx != j) return false;
        }

        return true;
    }
}
