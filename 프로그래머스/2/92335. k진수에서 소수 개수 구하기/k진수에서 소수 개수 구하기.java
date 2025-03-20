class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String[] splitArr = Integer.toString(n, k).split("0+");
        
        for(String str : splitArr) {
            if(checkOk(Long.valueOf(str))) answer++;
        }
        
        return answer;
    }
    
    public static boolean checkOk(long num) {
        if(num == 1) return false;
        if(num == 2) return true;
        
        int sqrt = (int) Math.sqrt(num);
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) return false;
        }
        
        return true;
    }
}