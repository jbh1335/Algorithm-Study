import java.util.*;
class Solution {
    static int[][] map;
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
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        map = new int[101][101];
        
        // 직사각형 만들기
        for(int i = 0; i < rectangle.length; i++) {
            // 좌표를 2배 시키기 -> 겹치는 부분을 테두리로 인식하여 이동할 수 있음 -> (1,0) (1,1)
            int x1 = rectangle[i][1] * 2;
            int y1 = rectangle[i][0] * 2;
            int x2 = rectangle[i][3] * 2;
            int y2 = rectangle[i][2] * 2;
            
            for(int a = x1; a <= x2; a++) {
                for(int b = y1; b <= y2; b++) {
                    if(a == x1 || a == x2 || b == y1 || b == y2) {
                        // 테두리만 2로 표시
                        if(map[a][b] == 0) map[a][b] = 2;
                    } else {
                        // 직사각형은 1로 표시
                        map[a][b] = 1;
                    }
                }
            }
        }
        
        answer = bfs(characterY*2, characterX*2, itemY*2, itemX*2) / 2;
        return answer;
    }
    
    // 테두리 탐색
    public static int bfs(int startX, int startY, int endX, int endY) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(startX, startY, 0));
        // 방문한 곳은 -1로 변경
        map[startX][startY] = -1;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
                    if(nx == endX && ny == endY) return p.dist+1;
                    if(map[nx][ny] == 2) { // 테두리
                        que.offer(new Point(nx, ny, p.dist+1));
                        map[nx][ny] = -1;
                    }
                }
            }
        }
        return -1;
    }
}