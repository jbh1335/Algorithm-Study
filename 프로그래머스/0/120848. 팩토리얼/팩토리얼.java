class Solution {
    public int solution(int n) {
        int answer = 0;
        int fact = 1, num = 0;
        
        while(true) {
            fact *= ++num;
            if(fact <= n) answer = num;
            else break;
        }
        
        return answer;
    }
    
    public static int fact(int n) {
        if(n == 1) return 1;
        return n * fact(n-1);
    }
}