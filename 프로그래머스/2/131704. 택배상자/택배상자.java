import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0, idx = 0;
        while(idx < order.length) {
            if(stack.isEmpty()) {
                stack.push(++num);
                continue;
            }
            
            if(stack.peek() == order[idx]) {
                answer++;
                idx++;
                stack.pop();
            } else {
                if(order[idx] < ++num) break;
                stack.push(num);
            }
        }
        return answer;
    }
}