import java.util.*;
class Solution {
    static class Point {
        int num, cnt;
        public Point(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    public int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }
    
    public static int bfs(int x, int y, int n) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, 0));
        boolean[] visited = new boolean[y+1];
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            if(p.num == y) return p.cnt;
            
            if(p.num+n <= y && !visited[p.num+n]) {
                que.offer(new Point(p.num+n, p.cnt+1));
                visited[p.num+n] = true;
            }
            
            if(p.num*2 <= y && !visited[p.num*2]) {
                que.offer(new Point(p.num*2, p.cnt+1));
                visited[p.num*2] = true;
            }
            
            if(p.num*3 <= y && !visited[p.num*3]) {
                que.offer(new Point(p.num*3, p.cnt+1));
                visited[p.num*3] = true;
            }
        }
        
        return -1;
    }
}