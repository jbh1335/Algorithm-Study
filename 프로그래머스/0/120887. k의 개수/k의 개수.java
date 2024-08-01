class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        
        for(int n = i; n <= j; n++) {
            String str = String.valueOf(n);
            for(int s = 0; s < str.length(); s++) {
                if(str.charAt(s)-'0' == k) answer++;
            }
        }
        
        return answer;
    }
}