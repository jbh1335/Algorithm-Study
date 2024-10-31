import java.util.*;
class Solution {
    static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int solution(int[][] maps) {
        int answer = bfs(maps);
        return answer;
    }
    
    public static int bfs(int[][] maps) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0, 1));
        maps[0][0] = -1;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < maps.length && ny < maps[0].length) {
                    if(nx == maps.length-1 && ny == maps[0].length-1) return p.dist + 1;
                    if(maps[nx][ny] == 1) {
                        que.offer(new Point(nx, ny, p.dist+1));
                        maps[nx][ny] = -1;
                    }
                }
            }
        }
        
        return -1;
    }
}