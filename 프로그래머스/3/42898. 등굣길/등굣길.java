class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n][m];
        for(int i = 0; i < puddles.length; i++) {
            int x = puddles[i][1] - 1;
            int y = puddles[i][0] - 1;
            dp[x][y] = -1;
        }
        
        dp[0][0] = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                
                if(i-1 >= 0) dp[i][j] = dp[i-1][j] % 1000000007;
                if(j-1 >= 0) dp[i][j] += dp[i][j-1] % 1000000007;
            }
        }
        
        answer = dp[n-1][m-1] % 1000000007;
        return answer;
    }
}