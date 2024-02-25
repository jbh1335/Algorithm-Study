import java.util.*;
class Solution {
    static int[][] map;
    static int[] visited;
    static class Point {
        int town, dist;
        public Point(int town, int dist) {
            this.town = town;
            this.dist = dist;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        map = new int[N+1][N+1];
        visited = new int[N+1];
        
        for(int i = 0; i <= N; i++) {
            Arrays.fill(map[i], 10001);
        }
        
        Arrays.fill(visited, 500001);
        
        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            map[a][b] = map[b][a] = Math.min(map[a][b], c);
        }

        bfs(N, K);
        for(int i = 1; i <= N; i++) {
            if(visited[i] != 500001) answer++;
        }
        return answer;
    }
    
    public static void bfs(int N, int K) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(1, 0));
        visited[1] = 0;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int i = 1; i <= N; i++) {
                if(p.town == i) continue;
                
                if(map[p.town][i] != 10001) {
                    int dist = map[p.town][i] + p.dist;
                    if(dist <= K && dist < visited[i]) {
                        que.offer(new Point(i, dist));
                        visited[i] = dist;
                    }
                }
            }
        }
    }
}