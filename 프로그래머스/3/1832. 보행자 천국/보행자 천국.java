class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] dist = new int[m][n][2]; // 왼쪽(0)에서 왔는지 위(1)에서 왔는지 구분
        // 0열
        for(int i = 1; i < m; i++) {
            // 한번 막히면 그 이후는 못감
            if(cityMap[i][0] == 1) break;
            dist[i][0][1] = 1;
        }
        // 0행
        for(int j = 1; j < n; j++) {
            if(cityMap[0][j] == 1) break;
            dist[0][j][0] = 1;
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(cityMap[i][j] == 1) continue;
                int left = cityMap[i][j-1];
                int top = cityMap[i-1][j];
                
                int leftNum = 0, topNum = 0;
                if(left == 2) {
                    leftNum = dist[i][j-1][0];
                } else if(left == 0) {
                    leftNum = (dist[i][j-1][0] + dist[i][j-1][1]) % 20170805;
                }
                
                if(top == 2) {
                    topNum = dist[i-1][j][1];
                } else if(top == 0) {
                    topNum = (dist[i-1][j][0] + dist[i-1][j][1]) % 20170805;
                }
                
                dist[i][j][0] = leftNum;
                dist[i][j][1] = topNum;
            }
        }
        
        answer = (dist[m-1][n-1][0] + dist[m-1][n-1][1]) % 20170805;
        return answer;
    }
}