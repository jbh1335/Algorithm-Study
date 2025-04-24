class Solution {
    public int[] solution(int n) {
        int maxNum = (n * (n+1)) / 2;
        int[] answer = new int[maxNum];
        int[][] arr = new int[n][n];
        arr[0][0] = 1;
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        
        int x = 0, y = 0, num = 1;
        while(num < maxNum) {
            for(int d = 0; d < 3; d++) {
                while(true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] == 0) {
                        arr[nx][ny] = ++num;
                        x = nx;
                        y = ny;
                    } else {
                        break;
                    }
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
}