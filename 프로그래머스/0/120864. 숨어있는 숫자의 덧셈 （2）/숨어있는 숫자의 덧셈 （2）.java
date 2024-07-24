import java.util.*;
class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String[] splitArr = my_string.toLowerCase().split("[a-z]+");
        
        for(String str : splitArr) {
            if(str.equals("")) continue;
            answer += Integer.parseInt(str);
        }
        
        return answer;
    }
}