class Solution {
    public long solution(long n) {
        long answer = -1;
        int sqrtN = (int) Math.sqrt(n);
        if(Math.pow(sqrtN, 2) == n) answer = (long) Math.pow(sqrtN+1, 2);
        
        return answer;
    }
}