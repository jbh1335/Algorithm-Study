class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 10; i >= 1; i--) {
            if(fact(i) <= n) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
    
    public static int fact(int n) {
        if(n == 1) return 1;
        return n * fact(n-1);
    }
}