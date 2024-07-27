class Solution {
    public String solution(String s) {
        String answer = "";
        int[] alpha = new int[26];
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            alpha[ch-'a']++;
        }
        
        for(int i = 0; i < 26; i++) {
            if(alpha[i] == 1) answer += (char) (i + 'a');
        }
        
        return answer;
    }
}