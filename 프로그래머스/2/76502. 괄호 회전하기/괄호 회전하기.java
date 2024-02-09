import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++) {
            String str = s.substring(i, s.length()) + s.substring(0, i);
            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            
            for(int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if(ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else {
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    
                    if(stack.peek() == '(' && ch == ')') {
                        stack.pop();
                    } else if(stack.peek() == '{' && ch == '}') {
                        stack.pop();
                    } else if(stack.peek() == '[' && ch == ']') {
                        stack.pop();
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag && stack.isEmpty()) answer++;
        }
        return answer;
    }
}