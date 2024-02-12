class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String changedNum = Integer.toString(n, k);
        String[] strArr = changedNum.split("0+");
        
        for(String s : strArr) {
            if(checkOk(Long.valueOf(s))) answer++;
        }
        
        return answer;
    }
    
    public static boolean checkOk(long num) {
        if(num == 1) return false;
        int sqrt = (int) Math.sqrt(num);
        
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}