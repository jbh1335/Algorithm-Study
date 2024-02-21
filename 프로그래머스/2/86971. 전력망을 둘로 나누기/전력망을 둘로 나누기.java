import java.util.*;
class Solution {
    static int[][] connect;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        int answer = n;
        connect = new int[n+1][n+1];
        
        for(int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            connect[a][b] = connect[b][a] = 1;
        }
        
        // 하나씩 끊어보고 송전탑 개수의 차이 구하기
        for(int i = 0; i < wires.length; i++) {
            visited = new boolean[n+1];
            int a = wires[i][0];
            int b = wires[i][1];
            connect[a][b] = connect[b][a] = 0;
            
            int count1 = bfs(1, n);
            int count2 = n - count1;
            answer = Math.min(answer, Math.abs(count1 - count2));
            connect[a][b] = connect[b][a] = 1;
        }
        return answer;
    }
    
    public static int bfs(int x, int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        visited[x] = true;
        int count = 1;
        
        while(!que.isEmpty()) {
            int num = que.poll();
            
            for(int i = 1; i <= n; i++) {
                if(!visited[i] && connect[num][i] == 1) {
                    que.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}