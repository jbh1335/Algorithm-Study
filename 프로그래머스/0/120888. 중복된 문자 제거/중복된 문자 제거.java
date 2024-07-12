import java.util.*;
class Solution {
    public String solution(String my_string) {
        String answer = "";
        HashSet<Character> set = new HashSet<>();
        
        for(int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            if(set.add(ch)) answer += ch;
        }
        
        return answer;
    }
}