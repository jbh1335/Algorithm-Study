import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(stack.isEmpty()) {
                stack.push(ch);
                continue;
            }
            
            if(stack.peek() == ch) stack.pop();
            else stack.push(ch);
        }
        
        if(stack.isEmpty()) answer = 1;
        return answer;
    }
}