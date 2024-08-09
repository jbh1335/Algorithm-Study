import java.util.*;
class Solution {
    public String[] solution(String[] str_list) {
        int start = 0, end = 0;
        
        for(int i = 0; i < str_list.length; i++) {
            if(str_list[i].equals("l")) {
                start = 0;
                end = i;
                break;
            } else if(str_list[i].equals("r")) {
                start = i + 1;
                end = str_list.length;
                break;
            }
        }
        
        String[] answer = Arrays.copyOfRange(str_list, start, end);
        return answer;
    }
}