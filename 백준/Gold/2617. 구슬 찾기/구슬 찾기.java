import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] heavyList, lightList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heavyList = new ArrayList[N+1];
        lightList = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            heavyList[i] = new ArrayList<>();
            lightList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lightList[a].add(b);
            heavyList[b].add(a);
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(bfs(i, heavyList) > N/2) answer++;
            else if(bfs(i, lightList) > N/2) answer++;
        }
        System.out.println(answer);
    }

    public static int bfs(int num, ArrayList<Integer>[] list) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(num);
        boolean[] visited = new boolean[N+1];
        visited[num] = true;
        int count = 0;

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int i : list[x]) {
                if(!visited[i]) {
                    que.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
