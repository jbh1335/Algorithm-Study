class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int left = n;
        
        for(int i = 0; i < n; i++) {
            answer[i] = s / left--;
            if(answer[i] == 0) return new int[] {-1};
            s -= answer[i];
        }
        
        return answer;
    }
}