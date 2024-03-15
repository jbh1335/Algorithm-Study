import java.util.*;
class Solution {
    static int[][] map;
    static String[][][] distance;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, dist;
        String result;
        public Point(int x, int y, int dist, String result) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.result = result;
        }
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        map = new int[n][m];
        distance = new String[n][m][k+1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(distance[i][j], "z");
            }
        }
        
        answer = bfs(x-1, y-1, r-1, c-1, k);
        return answer;
    }
    
    public static String bfs(int startX, int startY, int endX, int endY, int k) {
        PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> p1.result.compareTo(p2.result));
        pque.offer(new Point(startX, startY, 0, ""));
        
        while(!pque.isEmpty()) {
            Point p = pque.poll();
            if(p.dist == k && p.x == endX && p.y == endY) return p.result;
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
                    if(checkDistance(nx, ny, endX, endY, k-p.dist-1)) {
                        String dir = p.result + direction(d);
                        if(checkSmall(dir, distance[nx][ny][p.dist+1])) {
                            distance[nx][ny][p.dist+1] = dir;
                            pque.offer(new Point(nx, ny, p.dist+1, dir));
                        }
                    }
                }
            }
        }
        return "impossible";
    }
    
    public static String direction(int d) {
        if(d == 0) return "u";
        else if(d == 1) return "d";
        else if(d == 2) return "l";
        else return "r";
    }
    
    public static boolean checkSmall(String s1, String s2) {
        if(s1.compareTo(s2) < 0) return true;
        return false;
    }
    
    public static boolean checkDistance(int nx, int ny, int endX, int endY, int left) {
        // 목적지까지 가는데 남은 거리
        int rest = Math.abs(nx - endX) + Math.abs(ny - endY);
        // 남은 거리가 사용할 수 있는 거리보다 크면 불가능
        if(rest > left) return false;
        // 남은 거리의 홀짝이 같아야함
        if(rest % 2 != left % 2) return false;
        return true;
    }
}