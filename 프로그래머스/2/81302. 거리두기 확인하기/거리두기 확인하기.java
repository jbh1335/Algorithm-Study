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
    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};
        
        for(int i = 0; i < 5; i++) {
            loop:
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(places[i][j].charAt(k) == 'P') {
                        if(!bfs(j, k, places[i])) {
                            answer[i] = 0;
                            break loop;
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    public static boolean bfs(int x, int y, String[] map) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !visited[nx][ny]) {
                    if(map[nx].charAt(ny) == 'P' && p.dist + 1 <= 2) return false;
                    if(map[nx].charAt(ny) == 'O') {
                        que.offer(new Point(nx, ny, p.dist+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        return true;
    }
}