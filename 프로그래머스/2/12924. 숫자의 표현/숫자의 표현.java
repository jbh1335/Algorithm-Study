class Solution {
    public int solution(int n) {
        int answer = 1;
        
        for(int i = 1; i <= n/2; i++) {
            int sum = 0, num = i;
            while(true) {
                sum += num++;
                if(sum >= n) {
                    if(sum == n) answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}