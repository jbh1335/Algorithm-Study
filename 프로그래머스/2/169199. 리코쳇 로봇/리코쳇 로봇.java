import java.util.*;
class Solution {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, cnt;
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        int startX = 0, startY = 0;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        int answer = bfs(startX, startY);
        return answer;
    }
    
    public static int bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        visited[x][y] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x;
                int ny = p.y;
                boolean flag = false;
                
                while(true) {
                    nx += dx[d];
                    ny += dy[d];
                    
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        if(map[nx][ny] == 'D') {
                            flag = true;
                        }
                    } else {
                        flag = true;
                    }
                    
                    if(flag) {
                        nx -= dx[d];
                        ny -= dy[d];
                        
                        if(map[nx][ny] == 'G') return p.cnt + 1;
                        
                        if(!visited[nx][ny]) {
                            que.offer(new Point(nx, ny, p.cnt+1));
                            visited[nx][ny] = true;
                        }
                        
                        break;
                    }
                }
            }
        }
        
        return -1;
    }
}