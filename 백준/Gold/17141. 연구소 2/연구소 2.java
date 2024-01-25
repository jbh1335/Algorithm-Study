import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static Point[] select;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> virusList = new ArrayList<>();
    static Queue<Point> que;
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

        N = Integer.parseInt(st.nextToken()); // N*N
        M = Integer.parseInt(st.nextToken()); // 바이러스 개수

        map = new int[N][N]; // N*N 배열
        select = new Point[M]; // 빈칸(2)에서 M곳을 뽑아서 좌표 저장하는 배열

        // 빈칸 0, 벽 1, 바이러스를 놓을 수 있는 빈칸 2
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 바이러스를 놓을 수 있는 곳 저장
                if(map[i][j] == 2) virusList.add(new Point(i, j));
            }
        }

        com(0, 0);
        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    // 조합으로 빈칸 M곳을 뽑기
    public static void com(int cnt, int start) {
        if(cnt == M) {
            que = new LinkedList<>();
            visited = new boolean[N][N];

            // 뽑은 곳을 바이러스를 저장하는 que에다 넣음
            for(int i = 0; i < M; i++) {
                Point p = select[i];
                que.offer(new Point(p.x, p.y));
                visited[p.x][p.y] = true;
            }

            // 바이러스 퍼지기
            virus();
            return;
        }

        for(int i = start; i < virusList.size(); i++) {
            int x = virusList.get(i).x;
            int y = virusList.get(i).y;

            if(map[x][y] == 2) {
                select[cnt] = new Point(x, y);
                com(cnt+1, i+1);
            }
        }
    }

    public static void virus() {
        int time = -1;
        while(!que.isEmpty()) {
            int size = que.size();
            for(int s = 0; s < size; s++) {
                Point p = que.poll();

                for(int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                        // 빈칸이면 바이러스 퍼짐
                        if(map[nx][ny] != 1) {
                            que.offer(new Point(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
            time++;
        }

        if(checkAllVirus()) answer = Math.min(answer, time);
    }

    // 모든 칸에 바이러스를 퍼졌는지 확인
    public static boolean checkAllVirus() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // 방문한적 없는데 그곳이 빈칸이라면 바이러스가 다 못퍼짐
                if(!visited[i][j] && map[i][j] != 1) return false;
            }
        }
        return true;
    }
}
