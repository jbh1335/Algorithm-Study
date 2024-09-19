import java.util.*;
class Solution {
    static int N, M;
    static char[][] map;
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
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        
        int startX = 0, startY = 0, leverX = 0, leverY = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = maps[i].charAt(j);
                // 시작 위치
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
                
                // 레버 위치
                if(map[i][j] == 'L') {
                    leverX = i;
                    leverY = j;
                }
            }
        }
        
        // 레버 찾기
        int answer = bfs(startX, startY, 'L');
        // 레버를 찾았으면 출구 찾기
        if(answer != -1) {
            int exit = bfs(leverX, leverY, 'E');
            answer = exit == -1 ? -1 : answer + exit;
        }
        
        return answer;
    }
    
    public static int bfs(int x, int y, char target) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(map[nx][ny] == target) return p.dist + 1;
                    if(map[nx][ny] != 'X') {
                        que.offer(new Point(nx, ny, p.dist+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        return -1;
    }
}