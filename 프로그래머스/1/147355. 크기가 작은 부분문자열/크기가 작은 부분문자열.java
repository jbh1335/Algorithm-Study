class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int T = t.length();
        int P = p.length();
        
        for(int i = 0; i <= T-P; i++) {
            long num = Long.parseLong(t.substring(i, i+P));
            if(num <= Long.parseLong(p)) answer++;
        }
        
        return answer;
    }
}