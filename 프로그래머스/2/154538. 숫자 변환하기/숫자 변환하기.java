import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = bfs(x, y, n);
        return answer;
    }
    
    public static int bfs(int x, int y, int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        int count = 0;
        boolean[] visited = new boolean[y+1];
        
        while(!que.isEmpty()) {
            int size = que.size();
            for(int s = 0; s < size; s++) {
                int num = que.poll();
                if(num == y) return count;
                
                if((num + n) <= y && !visited[num + n]) {
                    que.offer(num + n);
                    visited[num + n] = true;
                }
                
                if((num * 2) <= y && !visited[num * 2]) {
                    que.offer(num * 2);
                    visited[num * 2] = true;
                }
                
                if((num * 3) <= y && !visited[num * 3]) {
                    que.offer(num * 3);
                    visited[num * 3] = true;
                }
            }
            
            count++;
        }
        
        return -1;
    }
}