import java.util.*;
class Solution {
    public String[] solution(String my_string) {
        String[] answer = new String[my_string.length()];
        int length = my_string.length();
        
        for(int i = 0; i < length; i++) {
            answer[i] = my_string.substring(i, length);
        }
        
        Arrays.sort(answer);
        return answer;
    }
}