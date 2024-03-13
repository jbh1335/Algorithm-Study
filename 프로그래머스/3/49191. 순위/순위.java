class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] arr = new int[n+1][n+1];
        for(int i = 0; i < results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            arr[a][b] = 1; // 승리
            arr[b][a] = -1; // 패배
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                        arr[j][i] = -1;
                    } else if(arr[i][k] == -1 && arr[k][j] == -1) {
                        arr[i][j] = -1;
                        arr[j][i] = 1;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            boolean isOk = true;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                if(arr[i][j] == 0) {
                    isOk = false;
                    break;
                }
            }
            
            if(isOk) answer++;
        }
        return answer;
    }
}