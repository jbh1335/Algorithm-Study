import java.util.*;
class Solution {
    static ArrayList<Point>[] connectList;
    static class Point {
        int node, dist;
        public Point(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        connectList = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            connectList[i] = new ArrayList<>();
        }
        
        for(int[] arr : fares) {
            connectList[arr[0]].add(new Point(arr[1], arr[2]));
            connectList[arr[1]].add(new Point(arr[0], arr[2]));
        }
        
        // s에서 출발했을 때의 최단 경로
        int[] distS = bfs(s, new int[n+1]);
        // a에서 출발했을 때의 최단 경로
        int[] distA = bfs(a, new int[n+1]);
        // b에서 출발했을 때의 최단 경로
        int[] distB = bfs(b, new int[n+1]);
        
        for(int i = 1; i <= n; i++) {
            // s에서 i까지의 최단 경로 + a에서 i까지의 최단 경로 + b에서 i까지의 최단 경로 
            answer = Math.min(answer, distS[i]+distA[i]+distB[i]);
        }
        return answer;
    }
    
    public static int[] bfs(int start, int[] distance) {
        PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        pque.offer(new Point(start, 0));
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        while(!pque.isEmpty()) {
            Point now = pque.poll();
            
            for(Point p : connectList[now.node]) {
                int dist = now.dist + p.dist;
                if(dist < distance[p.node]) {
                    pque.offer(new Point(p.node, dist));
                    distance[p.node] = dist;
                }
            }
        }
        return distance;
    }
}