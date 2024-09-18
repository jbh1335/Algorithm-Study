class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        answer[0][0] = 1;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0, num = 2, dir = 0;
        
        while(num <= n*n) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx >= 0 && ny >= 0 && nx < n && ny < n && answer[nx][ny] == 0) {
                answer[nx][ny] = num++;
                x = nx;
                y = ny;
            } else {
                if(++dir == 4) dir = 0;
            }
        }
        
        return answer;
    }
}