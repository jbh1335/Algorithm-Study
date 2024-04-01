import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    static int[] dx = {1, 0}; // 하우
    static int[] dy = {0, 1};
    static class Point {
        int x, y, cnt;
        boolean making;
        public Point(int x, int y, int cnt, boolean making) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.making = making;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            if(bfs(0, i, 0)) answer++; // 아래
            if(bfs(i, 0, 1)) answer++; // 오른쪽
        }

        System.out.println(answer);
    }

    public static boolean bfs(int x, int y, int dir) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 1, false));

        while(!que.isEmpty()) {
            Point p = que.poll();

            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];

            if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if(map[p.x][p.y] == map[nx][ny]) { // 전이랑 높이가 같음
                    if(p.making) { // 경사로 만드는 중이라면
                        if(p.cnt+1 == L) que.offer(new Point(nx, ny, 0, false)); // 길이가 L인 경사로 만들었음
                        else que.offer(new Point(nx, ny, p.cnt+1, true)); // 경사로를 만들 수 있는지 계속 확인
                    } else { // 경사로 만드는 중이 아니면 같은 크기의 개수 세기
                        que.offer(new Point(nx, ny, p.cnt+1, false));
                    }
                } else if(map[p.x][p.y] == map[nx][ny]+1) { // 아래로 내려감
                    if(p.making) return false; // 만드는 중이었다면 실패
                    else {
                        if(L == 1) que.offer(new Point(nx, ny, 0, false)); // 길이가 L인 경사로 만들었음
                        else que.offer(new Point(nx, ny, 1, true)); // 경사로 만들기
                    }
                } else if(map[p.x][p.y]+1 == map[nx][ny]) { // 위로 올라감
                    if(p.making) return false; // 만드는 중이었다면 실패
                    else {
                        // 그 전에 같은 크기의 개수가 L개 이상이면 경사로 만들기 가능
                        if(p.cnt >= L) que.offer(new Point(nx, ny, 1, false));
                        else return false; // 아니면 실패
                    }
                } else { // 높이가 차이가 2 이상이면 실패
                    return false;
                }
            } else {
                // 경사로를 만들고 있는데 끝나버리면 실패
                if(p.making) return false;
            }
        }
        return true;
    }
}
