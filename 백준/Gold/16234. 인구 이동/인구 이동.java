import java.io.*;
import java.util.*;

/*
    N*N 크기의 땅이 있는데 인구 이동이 며칠 동안 발생하는지 구하기
    인접한 땅의 인구 차이가 L이상 R이하라면 국경선 열기
    인구수 = 연합의 인구수 / 땅의 수 (소수점 버림) 로 새롭게 인구 이동함
 */
public class Main {
    static int N, L, R;
    static int[][] map, newMap;
    static boolean[][] visited;
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

        N = Integer.parseInt(st.nextToken()); // 땅의 크기
        L = Integer.parseInt(st.nextToken()); // L이상
        R = Integer.parseInt(st.nextToken()); // R이하

        map = new int[N][N]; // N*N 크기의 땅
        newMap = new int[N][N]; // 인구 이동이 일어나는 땅
        visited = new boolean[N][N]; // 방문배열

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = newMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while(true) {
            boolean flag = false;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        if(bfs(i, j)) flag = true;
                    }
                }
            }

            // 더 이상 인구 이동이 일어나지 않으면 끝
            if(flag) {
                answer++;
                visited = new boolean[N][N];
                makeNewMap();
            } else break;
        }

        System.out.println(answer);
    }

    // 인접한 땅 인구 차이 구하기
    public static boolean bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;
        int peopleSum = map[x][y], landNum = 1;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int minus = Math.abs(map[p.x][p.y] - map[nx][ny]); // 인구수 차이
                    if(L <= minus && minus <= R) {
                        peopleSum += map[nx][ny]; // 전체 인구의 합
                        landNum++; // 땅의 수
                        newMap[nx][ny] = -1; // 인구 이동을 해야하는 땅을 -1로 함
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        // 조건에 충족하는 땅이 2개 이상이면 인구 이동
        if(landNum >= 2) {
            newMap[x][y] = -1;
            movePeople(peopleSum, landNum);
            return true;
        }
        return false;
    }

    // 인구 이동하기
    public static void movePeople(int peopleSum, int landNum) {
        int newPeopleNum = peopleSum / landNum;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(newMap[i][j] == -1) newMap[i][j] = newPeopleNum;
            }
        }
    }

    // 인구 이동이 끝난 땅을 map에 저장
    public static void makeNewMap() {
        for(int i = 0; i < N; i++) {
            map[i] = newMap[i].clone();
        }
    }
}
