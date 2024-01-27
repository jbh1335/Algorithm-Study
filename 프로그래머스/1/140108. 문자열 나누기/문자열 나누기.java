class Solution {
    public int solution(String s) {
        int answer = 0;
        int same = 1, diff = 0;
        char target = s.charAt(0);
        
        for(int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(same == 0) {
                same++;
                target = ch;
                continue;
            }
            
            if(target == ch) same++;
            else diff++;
            
            if(same == diff) {
                answer++;
                same = 0;
                diff = 0;
            }
        }
        
        if(same != diff) answer++;
        return answer;
    }
}