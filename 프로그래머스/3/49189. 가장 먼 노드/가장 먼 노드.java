import java.util.*;
class Solution {
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            list[b].add(a);
        }
        
        answer = bfs(n);
        return answer;
    }
    
    public static int bfs(int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        visited[1] = true;
        int size = 0;
        
        while(!que.isEmpty()) {
            size = que.size();
            
            for(int s = 0; s < size; s++) {
                int x = que.poll();

                for(int num : list[x]) {
                    if(visited[num]) continue;
                    que.offer(num);
                    visited[num] = true;
                }   
            }
        }
        
        return size;
    }
}