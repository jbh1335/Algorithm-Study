import java.util.*;
class Solution {
    public String solution(String my_string) {
        my_string = my_string.toLowerCase();
        
        char[] sArr = new char[my_string.length()];
        for(int i = 0; i < my_string.length(); i++) {
            sArr[i] = my_string.charAt(i);
        }
        
        Arrays.sort(sArr);
        String answer = "";
        for(char ch : sArr) {
            answer += ch;
        }
        return answer;
    }
}