import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < numbers.length; i++) {
            while(!stack.isEmpty()) {
                if(numbers[stack.peek()] < numbers[i]) {
                    answer[stack.peek()] = numbers[i];
                    stack.pop();
                } else {
                    break;
                }
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}