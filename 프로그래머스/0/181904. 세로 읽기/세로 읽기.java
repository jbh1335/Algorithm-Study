class Solution {
    public String solution(String my_string, int m, int c) {
        String answer = "";
        int idx = c - 1;
        
        while(idx < my_string.length()) {
            answer += my_string.charAt(idx);
            idx += m;
        }
        
        return answer;
    }
}