import java.util.*;
class Solution {
    static int N, M;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        return bfs(maps);
    }
    
    public static int bfs(int[][] maps) {
        Queue<Point> que = new LinkedList<>();
        visited[0][0] = true;
        que.offer(new Point(0, 0, 1));
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(nx == N-1 && ny == M-1) { // 도착
                        return p.dist+1;
                    }
                    
                    if(maps[nx][ny] == 1) { // 빈칸
                        visited[nx][ny] = true;
                        que.offer(new Point(nx, ny, p.dist+1));
                    }
                }
            }
        }
        return -1;
    }
}