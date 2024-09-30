class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 1000000};
        int start = 0, end = 0, sum = sequence[0];
        
        while(end < sequence.length) {
            if(sum <= k) {
                if(sum == k) {
                    if(end-start < answer[1]-answer[0]) {
                        answer[0] = start;
                        answer[1] = end;
                    }
                }
                
                if(++end == sequence.length) break;
                sum += sequence[end];
            } else {
                sum -= sequence[start++];
            }
        }
        
        return answer;
    }
}