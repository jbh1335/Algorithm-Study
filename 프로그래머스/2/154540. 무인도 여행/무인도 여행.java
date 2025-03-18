import java.util.*;
class Solution {
    static int N, M;
    static int[][] map;
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int[] solution(String[] maps) {
        ArrayList<Integer> list = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                char ch = maps[i].charAt(j);
                map[i][j] = ch == 'X' ? 0 : ch - '0';
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] > 0) {
                    list.add(bfs(i, j));
                }
            }
        }
        
        if(list.size() == 0) return new int[] {-1};
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    public static int bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        int dist = map[x][y];
        map[x][y] = 0;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(map[nx][ny] != 0) {
                        que.offer(new Point(nx, ny));
                        dist += map[nx][ny];
                        map[nx][ny] = 0;
                    }
                }
            }
        }
        
        return dist;
    }
}