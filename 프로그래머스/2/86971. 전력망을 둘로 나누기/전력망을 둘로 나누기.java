import java.util.*;
class Solution {
    static boolean[][] connect;
    public int solution(int n, int[][] wires) {
        int answer = n;
        connect = new boolean[n+1][n+1];
        
        for(int[] arr : wires) {
            connect[arr[0]][arr[1]] = connect[arr[1]][arr[0]] = true;
        }
        
        for(int[] arr : wires) {
            connect[arr[0]][arr[1]] = connect[arr[1]][arr[0]] = false;
            int count = bfs(1, n);
            answer = Math.min(answer, Math.abs(n - count - count));
            connect[arr[0]][arr[1]] = connect[arr[1]][arr[0]] = true;
        }
        
        return answer;
    }
    
    public static int bfs(int x, int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        boolean[] visited = new boolean[n+1];
        visited[x] = true;
        int count = 1;
        
        while(!que.isEmpty()) {
            int num = que.poll();
            
            for(int i = 1; i <= n; i++) {
                if(!visited[i] && connect[num][i]) {
                    que.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}