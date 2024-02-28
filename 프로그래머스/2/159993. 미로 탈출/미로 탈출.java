import java.util.*;
class Solution {
    static int leverX, leverY;
    static char[][] map;
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
    public int solution(String[] maps) {
        int answer = -1;
        map = new char[maps.length][maps[0].length()];
        
        int startX = 0, startY = 0;
        for(int i = 0; i < maps.length; i++) {
            String str = maps[i];
            for(int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        int goLever = bfs(startX, startY, 'L');
        int goExit = 0;
        if(goLever == -1) return -1;
        else goExit = bfs(leverX, leverY, 'E');
        
        if(goExit != -1) answer = goLever + goExit;
        return answer;
    }
    
    public static int bfs(int x, int y, char target) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        visited = new boolean[map.length][map[0].length];
        visited[x][y] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && !visited[nx][ny]) {
                    if(map[nx][ny] == target) {
                        leverX = nx;
                        leverY = ny;
                        return p.dist+1;
                    } else if(map[nx][ny] != 'X') {
                        que.offer(new Point(nx, ny, p.dist+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}