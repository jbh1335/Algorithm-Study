import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i = 0; i < s.length; i++) {
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            
            for(int j = 0; j < s[i].length(); j++) {
                char ch = s[i].charAt(j);
                if(stack.isEmpty()) {
                    stack.push(ch);
                    continue;
                }
                
                if(stack.size() >= 2 && ch == '0') {
                    if(stack.peek() == '1') {
                        char before = stack.pop();
                        if(stack.peek() == '1') {
                            stack.pop();
                            sb.append("110");
                            continue;
                        } else {
                            stack.push(before);
                        }
                    }
                }
                stack.push(ch);
            }
            
            StringBuilder str = new StringBuilder();
            while(!stack.isEmpty()) {
                str.append(stack.pop());
            }
            str.reverse();
            
            if(str.toString().contains("0")) { // 0이 있으면
                // 마자막 0 이후에 모든 110 넣기
                int lastIdx = str.lastIndexOf("0");
                answer[i] = str.toString().substring(0, lastIdx+1) + sb.toString() + str.toString().substring(lastIdx+1, str.length());
            } else { // 모두 1로 이루어져 있으면
                // 모든 110을 맨 앞에다 넣기
                answer[i] = sb.toString() + str.toString();
            }
        }
        return answer;
    }
}