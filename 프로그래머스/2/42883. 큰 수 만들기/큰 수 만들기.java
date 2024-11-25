import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int length = number.length() - k;
        Stack<Integer> stack = new Stack();
        stack.push(number.charAt(0) - '0');
        
        for(int i = 1; i < number.length(); i++) {
            int num = number.charAt(i) - '0';
            
            if(stack.peek() < num) {
                while(!stack.isEmpty()) {
                    if(k == 0 || stack.peek() >= num) break;
                    stack.pop();
                    k--;
                }
            }
            
            stack.push(num);
        }
        
        for(int i = 0; i < length; i++) {
            sb.append(stack.elementAt(i));
        }
        
        return sb.toString();
    }
}