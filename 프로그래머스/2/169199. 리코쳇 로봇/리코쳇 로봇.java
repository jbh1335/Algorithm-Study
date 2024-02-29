import java.util.*;
class Solution {
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
    public int solution(String[] board) {
        int answer = 0;
        map = new char[board.length][board[0].length()];
        visited = new boolean[board.length][board[0].length()];
        
        int startX = 0, startY = 0;
        for(int i = 0; i < board.length; i++) {
            String str = board[i];
            for(int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        answer = bfs(startX, startY);
        return answer;
    }
    
    public static int bfs(int startX, int startY) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int x = p.x;
                int y = p.y;
                while(true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
                        if(map[nx][ny] == 'D') {
                            break;
                        } else {
                            x = nx;
                            y = ny;
                        }
                    } else {
                        break;
                    }
                }
                
                if(map[x][y] == 'G') return p.dist+1;
                if(!visited[x][y]) {
                    que.offer(new Point(x, y, p.dist+1));
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }
}