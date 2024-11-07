class Solution {
    public long solution(int[] sequence) {
        if(sequence.length == 1) return Math.abs(sequence[0]);
        long answer = 0;
        
        // -1 1 -1
        for(int i = 0; i < sequence.length; i+=2) {
            sequence[i] *= -1;
        }
        
        long[] dp = new long[sequence.length];
        dp[0] = sequence[0];
        for(int i = 1; i < sequence.length; i++) {
            dp[i] = Math.max(sequence[i], dp[i-1]+sequence[i]);
            answer = Math.max(answer, dp[i]);
        }
        
        // 1 -1 1
        for(int i = 0; i < sequence.length; i++) {
            sequence[i] *= -1;
        }
        
        dp = new long[sequence.length];
        dp[0] = sequence[0];
        for(int i = 1; i < sequence.length; i++) {
            dp[i] = Math.max(sequence[i], dp[i-1]+sequence[i]);
            answer = Math.max(answer, dp[i]);
        }
        
        return answer;
    }
}