import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++) {
            Stack<Character> stack = new Stack<>();
            String str = s.substring(i, s.length()) + s.substring(0, i);
            boolean isAble = true;
            
            for(int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                
                if(ch == ')' || ch == ']' || ch == '}') {
                    if(stack.isEmpty()) {
                        isAble = false;
                        break;
                    }
                    
                    if(ch == ')') {
                        if(stack.peek() == '(') stack.pop();
                        else isAble = false;
                    } else if(ch == ']') {
                        if(stack.peek() == '[') stack.pop();
                        else isAble = false;
                    } else if(ch == '}') {
                        if(stack.peek() == '{') stack.pop();
                        else isAble = false;
                    }
                } else {
                    stack.push(ch);
                }
                
                if(!isAble) break;
            }
            
            if(isAble && stack.isEmpty()) answer++;
        }
        
        return answer;
    }
}