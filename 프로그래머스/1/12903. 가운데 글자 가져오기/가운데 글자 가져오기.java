class Solution {
    public String solution(String s) {
        String answer = "" + s.charAt(s.length()/2);
        if(s.length() % 2 == 0) answer = s.charAt(s.length()/2-1) + answer;
        return answer;
    }
}