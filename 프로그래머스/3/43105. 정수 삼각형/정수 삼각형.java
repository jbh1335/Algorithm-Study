class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        
        for(int i = 0; i < triangle.length-1; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]+triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]+triangle[i+1][j+1]);
                
                answer = Math.max(answer, Math.max(dp[i+1][j], dp[i+1][j+1]));
            }
        }
        
        return answer;
    }
}