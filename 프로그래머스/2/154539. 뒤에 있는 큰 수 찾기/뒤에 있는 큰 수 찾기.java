import java.util.*;
class Solution {
    static class Point {
        int num, idx;
        public Point(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<Point> stack = new Stack<>();
        for(int i = 0; i < numbers.length; i++) {
            if(stack.isEmpty()) {
                stack.push(new Point(numbers[i], i));
                continue;
            }
            
            while(true) {
                if(stack.isEmpty()) break;
                
                Point p = stack.peek();
                if(p.num < numbers[i]) {
                    answer[p.idx] = numbers[i];
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(new Point(numbers[i], i));
        }
        
        while(!stack.isEmpty()) {
            answer[stack.pop().idx] = -1;
        }
        return answer;
    }
}