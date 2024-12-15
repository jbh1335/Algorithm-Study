class Solution {
    public String solution(String n_str) {
        String answer = n_str;
        
        while(answer.startsWith("0")) {
            answer = answer.substring(1, answer.length());
        }
        
        return answer;
    }
}