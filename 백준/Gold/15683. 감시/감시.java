import java.io.*;
import java.util.*;

/*
    K개의 cctv가 설치되어 있음
    cctv 종류는 5가지(1~5)가 있고 감시할 수 있는 방향이 정해져있음
    벽(6)이 있으면 볼 수 없고 다른 cctv는 통과할 수 있음
    cctv는 90도 방향으로 회전할 수 있음
    cctv가 설치되어 있는 곳에서 종류마다 회전하여 가장 많이 감시할 수 있는 경우 찾기 (사각지대의 최소 크기 출력)
 */
public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[] select;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][][] cctvTypeDir = {
            {{0}}, // X
            {{0}, {1}, {2}, {3}}, // 1번
            {{0, 1}, {2, 3}}, // 2번
            {{0, 2}, {0, 3}, {1, 2}, {1, 3}}, // 3번
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번
            {{0, 1, 2, 3}}}; // 5번
    static ArrayList<Point> cctvList;
    static class Point {
        int x, y, type;
        public Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvList = new ArrayList<>(); // cctv 위치 저장하는 list

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0 && map[i][j] != 6) cctvList.add(new Point(i, j, map[i][j]));
            }
        }

        select = new int[cctvList.size()];
        com(0, 0);
        System.out.println(answer);
    }

    // cctv마다 회전할 수 있는 모든 경우를 고려하여 뽑기
    public static void com(int cnt, int start) {
        if(cnt == cctvList.size()) {
            visited = new boolean[N][M];
            for(int i = 0; i < select.length; i++) {
                Point cctv = cctvList.get(i);
                move(cctv.x, cctv.y, cctv.type, select[i]);
            }

            answer = Math.min(answer, countBlindSpot());
            return;
        }

        for(int i = start; i < cctvList.size(); i++) {
            int type = cctvList.get(i).type; // cctv 종류

            // type번 cctv가 회전할 수 있는 경우 중 몇 번째 경우를 선택할지
            for(int d = 0; d < cctvTypeDir[type].length; d++) {
                select[cnt] = d;
                com(cnt+1, i+1);
            }
        }
    }

    // cctv 이동
    public static void move(int x, int y, int type, int mode) {
        for(int d : cctvTypeDir[type][mode]) {
            int nx = x;
            int ny = y;
            // 해당 방향으로 감시 가능한지 탐색
            while(true) {
                nx += dx[d];
                ny += dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(map[nx][ny] != 6) {
                        // 빈칸이면 방문처리하고 계속 이동, 다른 cctv가 있으면 방문처리 X
                        if(map[nx][ny] == 0) visited[nx][ny] = true;
                    } else {
                        // 벽이면 멈춤
                        break;
                    }
                } else { // 범위 벗어나면 멈춤
                    break;
                }
            }
        }
    }

    // 사각지대 개수 세기
    public static int countBlindSpot() {
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] == 0) count++;
            }
        }
        return count;
    }
}
