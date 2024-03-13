import java.util.*;
class Solution {
    static ArrayList<Integer>[] list;
    static class Point {
        int x, dist;
        public Point(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        list = new ArrayList[n+1];
        
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            
            list[a].add(b);
            list[b].add(a);
        }
        
        for(int i = 0; i < sources.length; i++) {
            answer[i] = bfs(sources[i], destination, n);
        }
        return answer;
    }
    
    public static int bfs(int start, int end, int n) {
        if(start == end) return 0;
        
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(start, 0));
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int i : list[p.x]) {
                if(visited[i]) continue;
                if(i == end) return p.dist+1;
                
                que.offer(new Point(i, p.dist+1));
                visited[i] = true;
            }
        }
        return -1;
    }
}