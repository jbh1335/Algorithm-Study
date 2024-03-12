import java.util.*;
class Solution {
    static int N, M;
    static int[][][] distance;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, dir, cost;
        public Point(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        // 3차원 배열로 현재 위치에 어떤 방향으로 왔는지도 구분
        distance = new int[N][M][4];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }
        
        answer = bfs(board);
        return answer;
    }
    
    public static int bfs(int[][] board) {
        PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> p1.cost - p2.cost);
        distance[0][0][0] = 0;
        // 처음은 방향이 없으므로 오른쪽과 아래 방향은 직선임
        if(board[0][1] == 0) { // 오른쪽
            pque.offer(new Point(0, 1, 3, 100));
            distance[0][1][0] = 100;
        }
        
        if(board[1][0] == 0) { // 아래
            pque.offer(new Point(1, 0, 1, 100));
            distance[1][0][0] = 100;
        }
        
        while(!pque.isEmpty()) {
            Point p = pque.poll();
            if(p.x == N-1 && p.y == M-1) {
                return p.cost;
            }
            
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    int dist = p.cost + findCost(p.dir, d);
                    if(board[nx][ny] == 0 && dist <= distance[nx][ny][d]) {
                        pque.offer(new Point(nx, ny, d, dist));
                        distance[nx][ny][d] = dist;
                    }
                }
            }
        }
        return -1;
    }
    
    // 직선인지 코너인지 확인해서 그에 맞는 비용 반환
    public static int findCost(int before, int now) {
        if(before == 0 || before == 1) { // 상, 하
            if(now == 0 || now == 1) return 100;
            else return 600;
        } else { // 좌, 우
            if(now == 2 || now == 3) return 100;
            else return 600;
        }
    }
}