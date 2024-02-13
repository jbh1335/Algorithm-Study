import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < prices.length; i++) {
            if(stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            
            while(true) {
                if(stack.isEmpty()) break;
                
                int idx = stack.peek();
                if(prices[idx] > prices[i]) {
                    answer[idx] = i - idx;
                    stack.pop();
                } else {
                    break;
                }
            }
            
            stack.push(i);
        }
        
        int lastIdx = stack.pop();
        answer[lastIdx] = 0;
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = lastIdx - idx;
        }
        return answer;
    }
}