class Solution {
    public String solution(int q, int r, String code) {
        String answer = "";
        
        while(r < code.length()) {
            answer += code.charAt(r);
            r += q;
        }
        
        return answer;
    }
}