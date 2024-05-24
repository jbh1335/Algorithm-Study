class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            for(int j = 0; j < index; j++) {
                if(++ch > 'z') ch = 'a';
                if(skip.indexOf(ch) != -1) j--;
            }
            
            answer += ch;
        }
        return answer;
    }
}