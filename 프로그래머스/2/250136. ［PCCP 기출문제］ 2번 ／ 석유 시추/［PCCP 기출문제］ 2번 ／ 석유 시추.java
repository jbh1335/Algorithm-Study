import java.util.*;
class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int solution(int[][] land) {
        int answer = 0;
        int num = 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // 같은 영역의 땅은 num으로 구분
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land[0].length; j++) {
                if(land[i][j] == 1) {
                    map.put(num, bfs(i, j, num, land));
                    num++;
                }
            }
        }
        
        // 세로에 해당하는 가장 큰 영역 찾기
        boolean[] visited;
        for(int j = 0; j < land[0].length; j++) {
            int sum = 0;
            visited = new boolean[map.size()+2];
            for(int i = 0; i < land.length; i++) {
                if(land[i][j] != 0) {
                    int type = land[i][j];
                    if(!visited[type]) {
                        sum += map.get(type);
                        visited[type] = true;
                    }
                }
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }
    
    public static int bfs(int x, int y, int num, int[][] land) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        land[x][y] = num;
        int count = 1;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < land.length && ny < land[0].length) {
                    if(land[nx][ny] == 1) {
                        que.offer(new Point(nx, ny));
                        // 같은 땅은 num으로 바꿈
                        land[nx][ny] = num;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}