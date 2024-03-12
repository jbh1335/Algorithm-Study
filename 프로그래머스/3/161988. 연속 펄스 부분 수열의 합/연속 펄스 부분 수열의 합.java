import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        if(sequence.length == 1) return Math.abs(sequence[0]);
        
        long answer = 0;
        int[] arr = sequence.clone();
        // -1 1 -1
        for(int i = 0; i < sequence.length; i+=2) {
            arr[i] = sequence[i] * -1;
        }
        
        long[] dp = new long[sequence.length];
        dp[0] = arr[0];
        for(int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
            answer = Math.max(answer, dp[i]);
        }
        
        // 1 -1 1
        arr = sequence.clone();
        for(int i = 1; i < sequence.length; i+=2) {
            arr[i] = sequence[i] * -1;
        }
        
        dp = new long[sequence.length];
        dp[0] = arr[0];
        for(int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}