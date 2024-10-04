import java.util.*;
class Solution {
    public class Point {
        int num, idx;
        public Point(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        PriorityQueue<Point> pque = new PriorityQueue<>((Point p1, Point p2) -> p1.num - p2.num);
        
        for(int i = 0; i < numbers.length; i++) {
            while(!pque.isEmpty()) {
                if(pque.peek().num < numbers[i]) answer[pque.poll().idx] = numbers[i];
                else break;
            }
            
            pque.offer(new Point(numbers[i], i));
        }
        
        while(!pque.isEmpty()) {
            answer[pque.poll().idx] = -1;
        }
        
        return answer;
    }
}