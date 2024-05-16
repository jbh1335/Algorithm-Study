import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[][] egg;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        egg = new int[N][2];
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            egg[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            egg[i][1] = Integer.parseInt(st.nextToken()); // 무게
        }

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int cnt) {
        if(cnt == N) {
            int count = 0;
            for(boolean flag : visited) {
                if(flag) count++;
            }

            answer = Math.max(answer, count);
            return;
        }

        boolean isAble = false;
        for(int i = 0; i < N; i++) {
            // 자기 자신이거나 깨졌으면 넘어감
            if(i == cnt || visited[i] || visited[cnt]) continue;

            // cnt의 계란이 깨짐
            egg[cnt][0] -= egg[i][1];
            if(egg[cnt][0] <= 0) visited[cnt] = true;

            // i의 계란이 깨짐
            egg[i][0] -= egg[cnt][1];
            if(egg[i][0] <= 0) visited[i] = true;

            isAble = true;
            dfs(cnt+1);

            // 복구
            visited[cnt] = visited[i] = false;
            egg[cnt][0] += egg[i][1];
            egg[i][0] += egg[cnt][1];
        }

        // 이미 cnt가 깨졌거나 깨지지 않은 다른 계란이 없으면 그냥 넘어감
        if(!isAble) dfs(cnt+1);
    }
}
