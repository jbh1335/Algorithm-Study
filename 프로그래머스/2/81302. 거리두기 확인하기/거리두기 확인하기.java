import java.util.*;
class Solution {
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, dist;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        map = new char[5][5];
        
        for(int i = 0; i < 5; i++) {
            // 사람이 있는 곳의 좌표를 저장
            ArrayList<Point> list = new ArrayList<>();
            
            for(int j = 0; j < 5; j++) {
                String str = places[i][j];
                for(int k = 0; k < 5; k++) {
                    map[j][k] = str.charAt(k);
                    if(map[j][k] == 'P') list.add(new Point(j, k));        
                }
            }
            
            boolean flag = true;
            for(Point p : list) {
                if(!checkOk(p.x, p.y)) {
                    flag = false;
                    break;
                }
            }
            answer[i] = flag ? 1 : 0;
        }
        
        return answer;
    }
    
    public static boolean checkOk(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y, 0));
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !visited[nx][ny]) {
                    if(map[nx][ny] == 'P') {
                        if(p.dist+1 <= 2) return false;
                    } else if(map[nx][ny] == 'O') {
                        que.offer(new Point(nx, ny, p.dist+1));
                    }
                    visited[nx][ny] = true;
                }
            }
        }
        
        return true;
    }
}