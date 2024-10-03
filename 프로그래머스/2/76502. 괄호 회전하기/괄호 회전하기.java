import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++) {
            String str = s.substring(i, s.length()) + s.substring(0, i);
            Stack<Character> stack = new Stack<>();
            boolean isAble = true;
            
            for(int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if(ch == '[' || ch == '(' || ch == '{') {
                    stack.push(ch);
                } else {
                    if(!stack.isEmpty()) {
                        char before = stack.peek();
                        
                        if(ch == ']') {
                            if(before != '[') isAble = false;
                        } else if(ch == ')') {
                            if(before != '(') isAble = false;
                        } else {
                            if(before != '{') isAble = false;
                        }
                    } else {
                        isAble = false;
                    }
                    
                    if(isAble) stack.pop();
                    else break;
                }
            }
            
            if(isAble && stack.isEmpty()) answer++;
        }
        
        return answer;
    }
}