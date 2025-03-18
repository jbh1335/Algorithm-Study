import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        int before = (100 - progresses[0]) / speeds[0];
        if((100 - progresses[0]) % speeds[0] != 0) before++;
        int count = 1;
        
        for(int i = 1; i < progresses.length; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] != 0) day++;
            
            if(before < day) {
                list.add(count);
                count = 0;
                before = day;
            }
            
            count++;
        }
        
        list.add(count);
        int[] answer = new int[list.size()];
        
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}