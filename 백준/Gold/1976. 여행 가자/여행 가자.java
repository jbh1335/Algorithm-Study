import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int connect = Integer.parseInt(st.nextToken());
                // 연결된 곳은 합치기
                if(connect == 1) union(i, j);
            }
        }

        // 여행 계획 순서
        String answer = "YES";
        st = new StringTokenizer(br.readLine());
        int city = parents[Integer.parseInt(st.nextToken()) - 1];
        for(int i = 1; i < M; i++) {
            int nextCity = Integer.parseInt(st.nextToken()) - 1;
            if(city != parents[nextCity]) answer = "NO";
        }

        System.out.println(answer);
    }

    public static void union(int i, int j) {
        int pi = findParent(i);
        int pj = findParent(j);

        if(pi == pj) return;

        if(pi < pj) parents[pj] = pi;
        else parents[pi] = pj;
    }

    public static int findParent(int x) {
        if(parents[x] == x) return x;

        parents[x] = findParent(parents[x]);
        return parents[x];
    }
}
