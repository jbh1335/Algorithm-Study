class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1; i <= n; i++) {
            while(true) {
                String strNum = String.valueOf(++answer);
                if(!strNum.contains("3") && answer % 3 != 0) break;
            }
        }
        
        return answer;
    }
}