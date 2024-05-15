import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(answer, map[i][j]);
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }

    public static void dfs(int cnt, int[][] map) {
        if(cnt == 5) return;

        dfs(cnt+1, moveUp(map));
        dfs(cnt+1, moveDown(map));
        dfs(cnt+1, moveLeft(map));
        dfs(cnt+1, moveRight(map));
    }

    // 상
    public static int[][] moveUp(int[][] map) {
        boolean[][] visited = new boolean[N][N];
        int[][] newMap = new int[N][N];

        for(int j = 0; j < N; j++) {
            Queue<Integer> que = new LinkedList<>();
            for(int i = 0; i < N; i++) {
                if(map[i][j] != 0) que.offer(map[i][j]);
            }

            int idx = 0;
            while(!que.isEmpty()) {
                int num = que.poll();
                // 숫자가 같은데 전에 합쳐진 적 없으면 합치기
                if(idx >= 1 && newMap[idx-1][j] == num && !visited[idx-1][j]) {
                    newMap[idx-1][j] *= 2;
                    visited[idx-1][j] = true;
                    answer = Math.max(answer, newMap[idx-1][j]);
                } else { // 다르면 그냥 이동만
                    newMap[idx++][j] = num;
                }
            }
        }
        return newMap;
    }

    // 하
    public static int[][] moveDown(int[][] map) {
        boolean[][] visited = new boolean[N][N];
        int[][] newMap = new int[N][N];

        for(int j = 0; j < N; j++) {
            Queue<Integer> que = new LinkedList<>();
            for(int i = N-1; i >= 0; i--) {
                if(map[i][j] != 0) que.offer(map[i][j]);
            }

            int idx = N-1;
            while(!que.isEmpty()) {
                int num = que.poll();
                // 숫자가 같은데 전에 합쳐진 적 없으면 합치기
                if(idx < N-1 && newMap[idx+1][j] == num && !visited[idx+1][j]) {
                    newMap[idx+1][j] *= 2;
                    visited[idx+1][j] = true;
                    answer = Math.max(answer, newMap[idx+1][j]);
                } else { // 다르면 그냥 이동만
                    newMap[idx--][j] = num;
                }
            }
        }
        return newMap;
    }

    // 좌
    public static int[][] moveLeft(int[][] map) {
        boolean[][] visited = new boolean[N][N];
        int[][] newMap = new int[N][N];

        for(int i = 0; i < N; i++) {
            Queue<Integer> que = new LinkedList<>();
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 0) que.offer(map[i][j]);
            }

            int idx = 0;
            while(!que.isEmpty()) {
                int num = que.poll();
                // 숫자가 같은데 전에 합쳐진 적 없으면 합치기
                if(idx >= 1 && newMap[i][idx-1] == num && !visited[i][idx-1]) {
                    newMap[i][idx-1] *= 2;
                    visited[i][idx-1] = true;
                    answer = Math.max(answer, newMap[i][idx-1]);
                } else { // 다르면 그냥 이동만
                    newMap[i][idx++] = num;
                }
            }
        }
        return newMap;
    }

    // 우
    public static int[][] moveRight(int[][] map) {
        boolean[][] visited = new boolean[N][N];
        int[][] newMap = new int[N][N];

        for(int i = 0; i < N; i++) {
            Queue<Integer> que = new LinkedList<>();
            for(int j = N-1; j >= 0; j--) {
                if(map[i][j] != 0) que.offer(map[i][j]);
            }

            int idx = N-1;
            while(!que.isEmpty()) {
                int num = que.poll();
                // 숫자가 같은데 전에 합쳐진 적 없으면 합치기
                if(idx < N-1 && newMap[i][idx+1] == num && !visited[i][idx+1]) {
                    newMap[i][idx+1] *= 2;
                    visited[i][idx+1] = true;
                    answer = Math.max(answer, newMap[i][idx+1]);
                } else { // 다르면 그냥 이동만
                    newMap[i][idx--] = num;
                }
            }
        }
        return newMap;
    }
}
