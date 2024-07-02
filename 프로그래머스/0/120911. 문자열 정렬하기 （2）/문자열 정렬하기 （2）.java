import java.util.*;
class Solution {
    public String solution(String my_string) {
        char[] sArr = my_string.toLowerCase().toCharArray();
        Arrays.sort(sArr);
        
        String answer = String.valueOf(sArr);
        return answer;
    }
}