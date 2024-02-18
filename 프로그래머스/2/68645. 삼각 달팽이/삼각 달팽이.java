class Solution {
    public int[] solution(int n) {
        int[] answer = new int[n*(n+1)/2];
        int[][] arr = new int[n][n];
        arr[0][0] = 1;
        int x = 0, y = 0, num = 1;
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        while(num < answer.length) {
            int dir = 0;
            
            while(dir < 3) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                if(rangeOk(nx, ny, n) && arr[nx][ny] == 0) {
                    arr[nx][ny] = ++num;
                    x = nx;
                    y = ny;
                } else {
                    dir++;
                }
            }
        }
        
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] != 0) answer[idx++] = arr[i][j];
            }
        }
        return answer;
    }
    
    public static boolean rangeOk(int nx, int ny, int n) {
        if(nx >= 0 && ny >= 0 && nx < n && ny < n) return true;
        return false;
    }
}