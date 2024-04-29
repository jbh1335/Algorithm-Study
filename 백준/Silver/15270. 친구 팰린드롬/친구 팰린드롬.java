import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static boolean[] visited;
    static ArrayList<Integer>[] connectList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        connectList = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            connectList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connectList[a].add(b);
        }

        dfs(1, 0);
        if(answer < N) answer++;
        System.out.println(answer);
    }

    public static void dfs(int idx, int pair) {
        if(idx > N) {
            answer = Math.max(answer, pair*2);
            return;
        }

        if(visited[idx]) {
            dfs(idx+1, pair);
        } else {
            visited[idx] = true;
            for(int friend : connectList[idx]) {
                if(visited[friend]) continue;

                visited[friend] = true;
                dfs(idx+1, pair+1);
                visited[friend] = false;
            }

            visited[idx] = false;
            dfs(idx+1, pair);
        }
    }
}
