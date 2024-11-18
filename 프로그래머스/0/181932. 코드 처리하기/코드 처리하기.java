class Solution {
    public String solution(String code) {
        String answer = "";
        int mode = 0;
        
        for(int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            
            if(mode == 0) {
                if(ch == '1') {
                    mode = 1;
                } else {
                    if(i % 2 == 0) answer += ch;
                }
            } else {
                if(ch == '1') {
                    mode = 0;
                } else {
                    if(i % 2 == 1) answer += ch;
                }
            }
        }
        
        if(answer.equals("")) answer = "EMPTY";
        return answer;
    }
}