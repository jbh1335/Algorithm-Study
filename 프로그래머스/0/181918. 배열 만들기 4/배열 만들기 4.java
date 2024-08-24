import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < arr.length; i++) {
            if(stack.isEmpty() || stack.peek() < arr[i]) {
                stack.push(arr[i]);
            } else {
                stack.pop();
                i--;
            }
        }
        
        int[] stk = new int[stack.size()];
        for(int i = stk.length-1; i >= 0; i--) {
            stk[i] = stack.pop();
        }
        
        return stk;
    }
}