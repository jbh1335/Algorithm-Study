import java.util.*;
class Solution {
    static int[] distance;
    static ArrayList<int[]>[] list;
    static class Point {
        int x, dist;
        public Point(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        distance = new int[N+1];
        Arrays.fill(distance, K+1);
        
        list = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] arr : road) {
            list[arr[0]].add(new int[] {arr[1], arr[2]});
            list[arr[1]].add(new int[] {arr[0], arr[2]});
        }
        
        bfs(K);
        
        for(int i = 1; i <= N; i++) {
            if(distance[i] <= K) answer++;
        }
        
        return answer;
    }
    
    public static void bfs(int K) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(1, 0));
        distance[1] = 0;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int[] arr : list[p.x]) {
                int dist = p.dist + arr[1];
                if(dist > K) continue;
                if(dist < distance[arr[0]]) {
                    distance[arr[0]] = dist;
                    que.offer(new Point(arr[0], dist));
                }
            }
        }
    }
}