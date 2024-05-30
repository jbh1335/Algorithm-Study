import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static boolean isFound;
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];
        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int answer = 0;
        for(int i = 0; i < R; i++) {
            isFound = false;
            visited[i][0] = true;
            dfs(i, 0);
            
            if(isFound) answer++;
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y) {
        if(y == C-1) {
            isFound = true;
            return;
        }

        for(int d = 0; d < 3; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && ny >= 0 && nx < R && ny < C) {
                if(!visited[nx][ny] && map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    dfs(nx, ny);
                    if(isFound) return;
                }
            }
        }
    }
}
