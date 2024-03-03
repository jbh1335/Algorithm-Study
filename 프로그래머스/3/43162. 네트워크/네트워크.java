import java.util.*;
class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(i, n, computers);
                answer++;
            }
        }
        return answer;
    }
    
    public static void bfs(int x, int n, int[][] computers) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        visited[x] = true;
        
        while(!que.isEmpty()) {
            int num = que.poll();
            
            for(int i = 0; i < n; i++) {
                if(!visited[i] && computers[num][i] == 1) {
                    que.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}