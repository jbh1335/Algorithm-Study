import java.util.*;
class Solution {
    static int N, M;
    static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int solution(String[] maps) {
        int answer = -1;
        N = maps.length;
        M = maps[0].length();
        
        int startX = 0, startY = 0, leverX = 0, leverY = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(maps[i].charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                }
                
                if(maps[i].charAt(j) == 'L') {
                    leverX = i;
                    leverY = j;
                }
            }
        }
        
        // 레버 찾으러 가는 거리
        int leverDist = bfs(startX, startY, 'L', maps);
        
        // 출구 가는 거리
        if(leverDist != -1) {
            int exitDist = bfs(leverX, leverY, 'E', maps);
            if(exitDist != -1) answer = leverDist + exitDist;
        }
        
        return answer;
    }
    
    public static int bfs(int x, int y, char target, String[] maps) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(maps[nx].charAt(ny) == target) return p.dist + 1;
                    
                    if(!visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                        que.offer(new Point(nx, ny, p.dist+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        return -1;
    }
}