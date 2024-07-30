import java.util.*;
class Solution {
    public String[] solution(String myStr) {
        String[] answer = new String[] {"EMPTY"};
        String[] splitArr = myStr.split("[abc]+");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(splitArr));
        
        if(list.size() > 0) {
            if(list.get(0).equals("")) list.remove(0);
            answer = new String[list.size()];
            
            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
        }
        
        return answer;
    }
}