class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        if(sticker.length == 1) return sticker[0];
        if(sticker.length == 2) return Math.max(sticker[0], sticker[1]);
        
        int[] dp = new int[sticker.length];
        dp[0] = sticker[0];
        dp[1] = sticker[1];
        dp[2] = dp[0] + sticker[2];
        
        answer = Math.max(dp[1], dp[2]);
        
        // 0번째를 뽑았을 때 -> 마지막꺼는 뽑지 않음
        for(int i = 3; i < sticker.length-1; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3]) + sticker[i];
            answer = Math.max(answer, dp[i]);
        }
        
        dp = new int[sticker.length];
        dp[0] = 0;
        dp[1] = sticker[1];
        dp[2] = sticker[2];
        // 0번째를 뽑지 않았을 때 -> 마지막꺼 뽑을 수 있음
        for(int i = 3; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3]) + sticker[i];
            answer = Math.max(answer, dp[i]);
        }
        
        return answer;
    }
}