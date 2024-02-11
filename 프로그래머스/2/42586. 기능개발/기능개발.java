import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for(int i = 0; i < progresses.length; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] != 0) day++;
            
            if(stack.isEmpty()) {
                stack.push(day);
                count++;
                continue;
            }
            
            if(day <= stack.peek()) count++;
            else {
                stack.pop();
                stack.push(day);
                list.add(count);
                count = 1;
            }
        }
        
        list.add(count);
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}