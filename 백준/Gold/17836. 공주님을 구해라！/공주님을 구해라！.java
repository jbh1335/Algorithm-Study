import java.io.*;
import java.util.*;

/*
    용사는 (0, 0), 공주는 (N, M) 에 위치함
    빈공간 0, 벽 1, 그람 2
    T시간 이내에 공주를 구하러 가야하는데 최단 시간 출력 -> 불가능하면 Fail
    그람이 있는 곳에 도착하면 벽을 부수고 갈 수 있음 (개수 제한 X)
 */
public class Main {
    static int N, M, T;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, time;
        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        T = Integer.parseInt(st.nextToken()); // 공주에게 걸린 제한 시간

        map = new int[N][M]; // 성 배열
        int gramX = 0, gramY = 0; // 그람이 있는 좌표
        
        // 성 정보 저장
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    gramX = i;
                    gramY = j;
                }
            }
        }

        // 그람 없이 공주 구하기
        int answer = bfsWithNoGram(N-1, M-1);
        // 그람 먼저 찾고
        int findGramTime = bfsWithNoGram(gramX, gramY);
        // 그람까지 갈 수 있으면 거기서 공주 구하러 가기
        if(findGramTime != N*M) {
            answer = Math.min(answer, bfsWithGram(gramX, gramY, findGramTime));
        }
        
        if(answer != N*M) System.out.println(answer);
        else System.out.println("Fail");
    }

    // 그람 없이 공주 구하러 가기
    public static int bfsWithNoGram(int endX, int endY) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0, 0));
        visited = new boolean[N][M];
        visited[0][0] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();
            // 만약 제한 시간이 초과되었으면 불가능
            if(p.time >= T) continue;

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(nx == endX && ny == endY) { // 도착했으면
                        return p.time+1;
                    } else if(map[nx][ny] == 0){ // 빈칸만 이동 가능
                        que.offer(new Point(nx, ny, p.time+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return N*M;
    }

    // 그람 찾고 공주 구하기
    public static int bfsWithGram(int x, int y, int time) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, time));
        visited = new boolean[N][M];
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Point p = que.poll();
            // 만약 제한 시간이 초과되었으면 불가능
            if(p.time >= T) continue;

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(nx == N-1 && ny == M-1) { // 공주에게 도착했으면
                        return p.time+1;
                    } else { // 그람이 있으므로 어느 곳이든 이동 가능
                        que.offer(new Point(nx, ny, p.time+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return N*M;
    }
}
