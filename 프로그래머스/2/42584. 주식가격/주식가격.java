import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty()) {
                int before = stack.peek();
                
                if(prices[before] > prices[i]) {
                    answer[before] = i - before;
                    stack.pop();
                } else {
                    break;
                }
            }
            
            stack.push(i);
        }
        
        int last = stack.pop();
        while(!stack.isEmpty()) {
            answer[stack.peek()] = last - stack.pop();
        }
        
        return answer;
    }
}