import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int length = number.length(), count = 0;
        
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < length; i++) {
            int num = number.charAt(i) - '0';
            
            while(!stack.isEmpty()) {
                if(stack.peek() < num && count < k) {
                    stack.pop();
                    count++;
                } else {
                    break;
                }
            }
            
            stack.push(num);
        }
        
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        String answer = sb.reverse().toString();
        if(answer.length() > length - k) answer = answer.substring(0, length - k);
        
        return answer;
    }
}