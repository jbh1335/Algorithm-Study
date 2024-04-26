class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        long[] dp = new long[n+1];
        dp[0] = 1;
        
        for(int i = 0; i < money.length; i++) {
            int cost = money[i];
            for(int j = cost; j <= n; j++) {
                dp[j] = ((long) dp[j] + dp[j-cost]) % 1000000007;
            }
        }
        
        answer = (int) dp[n];
        return answer;
    }
}