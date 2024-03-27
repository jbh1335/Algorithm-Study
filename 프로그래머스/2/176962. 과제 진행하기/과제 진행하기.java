import java.util.*;
class Solution {
    static class Point {
        String name;
        int start, end, left;
        public Point(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
        
        public Point(String name, int left) {
            this.name = name;
            this.left = left;
        }
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> p1.start - p2.start);
        
        for(int i = 0; i < plans.length; i++) {
            String[] time = plans[i][1].split(":");
            int hour = Integer.parseInt(time[0]) * 60;
            // 시간을 분으로 변경해서 합치기
            int min = Integer.parseInt(time[1]) + hour;
            // 끝나는 시간
            int end = Integer.parseInt(plans[i][2]) + min;
            pque.offer(new Point(plans[i][0], min, end));
        }
        
        int idx = 0;
        Stack<Point> stack = new Stack<>();
        // 이전 과제가 끝나는 시간
        while(!pque.isEmpty()) {
            Point now = pque.poll();
            // 마지막은 바로 저장하고 끝내기
            if(pque.isEmpty()) {
                answer[idx++] = now.name;
                break;
            }
            
            Point next = pque.peek();
            // 현재 과제를 무사히 마쳤음
            if(now.end <= next.start) {
                answer[idx++] = now.name;
                int left = next.start - now.end;
                // 공백이 있다면 이전에 완성하지 못한 과제 이어서 수행
                while(left > 0) {
                    if(stack.isEmpty()) break;
                    Point before = stack.pop();
                    if(before.left <= left) {
                        left -= before.left;
                        answer[idx++] = before.name;
                    } else {
                        // 현재 공백으로 이전 과제를 완성하지 못하면 남은 시간을 다시 저장
                        stack.push(new Point(before.name, before.left-left));
                        left = 0;
                    }
                }
            } else { // 현재 과제 보류
                stack.push(new Point(now.name, now.end-next.start));
            }
        }
        
        while(!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }
        return answer;
    }
}