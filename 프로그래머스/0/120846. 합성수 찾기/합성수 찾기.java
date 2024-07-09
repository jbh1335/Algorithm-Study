class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1; i <= n; i++) {
            if(checkOk(i)) answer++;
        }
        
        return answer;
    }
    
    public static boolean checkOk(int num) {
        int sqrt = (int) Math.sqrt(num);
        
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) return true;
        }
        
        return false;
    }
}