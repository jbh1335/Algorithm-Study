import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        int num = 1;
        for(int i = 0; i < order.length; i++) {
            if(num <= order[i]) {
                while(num <= order[i]) {
                    stack.push(num++);
                }
            } else {
                if(stack.peek() != order[i]) break;
            }
            
            stack.pop();
            answer++;
        }
        
        return answer;
    }
}