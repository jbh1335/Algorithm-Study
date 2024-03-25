import java.util.*;
class Solution {
    static int N, M;
    static boolean[][][] visited;
    static class Point {
        int x, y, dist, type;
        public Point(int x, int y, int dist, int type) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.type = type;
        }
    }
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        visited = new boolean[N][M][2]; // 가로 모양으로 왔는지 세로 모양으로 왔는지 구분
        
        answer = bfs(board);
        return answer;
    }
    
    public static int bfs(int[][] board) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 1, 0, 0));
        visited[0][1][0] = true;
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            if(p.x == N-1 && p.y == M-1) return p.dist;
            
            if(p.type == 0) { // 가로 모양
                // 오른쪽으로 이동
                if(p.y+1 < M) {
                    if(board[p.x][p.y+1] == 0 && !visited[p.x][p.y+1][0]) {
                        visited[p.x][p.y+1][0] = true;
                        que.offer(new Point(p.x, p.y+1, p.dist+1, 0));
                    }
                }
                // 왼쪽으로 이동
                if(p.y-2 >= 0) {
                    if(board[p.x][p.y-2] == 0 && !visited[p.x][p.y-1][0]) {
                        visited[p.x][p.y-1][0] = true;
                        que.offer(new Point(p.x, p.y-1, p.dist+1, 0));
                    }
                }
                // 아래로 이동
                if(p.x+1 < N) {
                    if(board[p.x+1][p.y] == 0 && board[p.x+1][p.y-1] == 0) {
                        // 가로 모양 그대로 이동
                        if(!visited[p.x+1][p.y][0]) {
                            visited[p.x+1][p.y][0] = true;
                            que.offer(new Point(p.x+1, p.y, p.dist+1, 0));
                        }
                        
                        // 오른쪽 기준으로 회전
                        if(!visited[p.x+1][p.y][1]) {
                            visited[p.x+1][p.y][1] = true;
                            que.offer(new Point(p.x+1, p.y, p.dist+1, 1));
                        }
                        
                        // 왼쪽 기준으로 회전
                        if(!visited[p.x+1][p.y-1][1]) {
                            visited[p.x+1][p.y-1][1] = true;
                            que.offer(new Point(p.x+1, p.y-1, p.dist+1, 1));
                        }
                    }
                }
                // 위로 이동
                if(p.x-1 >= 0) {
                    if(board[p.x-1][p.y] == 0 && board[p.x-1][p.y-1] == 0) {
                        // 가로 모양 그대로 이동
                        if(!visited[p.x-1][p.y][0]) {
                            visited[p.x-1][p.y][0] = true;
                            que.offer(new Point(p.x-1, p.y, p.dist+1, 0));
                        }
                        
                        // 오른쪽 기준으로 회전
                        if(!visited[p.x][p.y][1]) {
                            visited[p.x][p.y][1] = true;
                            que.offer(new Point(p.x, p.y, p.dist+1, 1));
                        }
                        
                        // 왼쪽 기준으로 회전
                        if(!visited[p.x][p.y-1][1]) {
                            visited[p.x][p.y-1][1] = true;
                            que.offer(new Point(p.x, p.y-1, p.dist+1, 1));
                        }
                    }
                }
            } else { // 세로 모양
                // 아래로 이동
                if(p.x+1 < N) {
                    if(board[p.x+1][p.y] == 0 && !visited[p.x+1][p.y][1]) {
                        visited[p.x+1][p.y][1] = true;
                        que.offer(new Point(p.x+1, p.y, p.dist+1, 1));
                    }
                }
                // 위로 이동
                if(p.x-2 >= 0) {
                    if(board[p.x-2][p.y] == 0 && !visited[p.x-1][p.y][1]) {
                        visited[p.x-1][p.y][1] = true;
                        que.offer(new Point(p.x-1, p.y, p.dist+1, 1));
                    }
                }
                // 오른쪽으로 이동
                if(p.y+1 < M) {
                    if(board[p.x][p.y+1] == 0 && board[p.x-1][p.y+1] == 0) {
                        // 세로 모양 그대로 이동
                        if(!visited[p.x][p.y+1][1]) {
                            visited[p.x][p.y+1][1] = true;
                            que.offer(new Point(p.x, p.y+1, p.dist+1, 1));
                        }
                        
                        // 아래쪽 기준으로 회전
                        if(!visited[p.x][p.y+1][0]) {
                            visited[p.x][p.y+1][0] = true;
                            que.offer(new Point(p.x, p.y+1, p.dist+1, 0));
                        }
                        
                        // 위쪽 기준으로 회전
                        if(!visited[p.x-1][p.y+1][0]) {
                            visited[p.x-1][p.y+1][0] = true;
                            que.offer(new Point(p.x-1, p.y+1, p.dist+1, 0));
                        }
                    }
                }
                // 왼쪽으로 이동
                if(p.y-1 >= 0) {
                    if(board[p.x][p.y-1] == 0 && board[p.x-1][p.y-1] == 0) {
                        // 세로 모양 그대로 이동
                        if(!visited[p.x][p.y-1][1]) {
                            visited[p.x][p.y-1][1] = true;
                            que.offer(new Point(p.x, p.y-1, p.dist+1, 1));
                        }
                        
                        // 아래쪽 기준으로 회전
                        if(!visited[p.x][p.y][0]) {
                            visited[p.x][p.y][0] = true;
                            que.offer(new Point(p.x, p.y, p.dist+1, 0));
                        }
                        
                        // 위쪽 기준으로 회전
                        if(!visited[p.x-1][p.y][0]) {
                            visited[p.x-1][p.y][0] = true;
                            que.offer(new Point(p.x-1, p.y, p.dist+1, 0));
                        }
                    }
                }
            }
        }
        return -1;
    }
} 