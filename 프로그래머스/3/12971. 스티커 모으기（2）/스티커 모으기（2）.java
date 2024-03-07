class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        if(sticker.length == 1) return sticker[0];
        
        int[] dp = new int[sticker.length];
        
        // 0번째 스티커 뽑았을 때 -> 마지막번째 스티커는 뽑지 않음
        dp[0] = sticker[0];
        dp[1] = dp[0];
        for(int i = 2; i < sticker.length-1; i++) {
            // 스티커를 뽑았을 경우와 뽑지 않을 경우 중 최댓값 구하기
            // 뽑았을 경우: i-1번째는 뽑지 못하니 i-2번째까지의 최댓값 + 자기자신
            // 뽑지 않을 경우: i-1까지의 최댓값
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        }      
        answer = dp[sticker.length-2];
        
        // 0번째 스티커를 뽑지 않았을 때 -> 마지막 스티커까지 검사
        dp = new int[sticker.length];
        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i = 2; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        }
        
        answer = Math.max(answer, dp[sticker.length-1]);
        return answer;
    }
}