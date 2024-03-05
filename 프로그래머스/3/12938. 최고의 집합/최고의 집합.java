class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int idx = 0;
        while(n > 0) {
            answer[idx] = s / n;
            if(answer[idx] == 0) return new int[] {-1};
            
            s -= answer[idx++];
            n--;
        }
        
        return answer;
    }
}