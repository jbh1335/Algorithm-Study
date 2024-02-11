import java.util.*;
class Solution {
    static class Point {
        int prior, idx;
        public Point(int prior, int idx) {
            this.prior = prior;
            this.idx = idx;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Point> que = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            que.offer(new Point(priorities[i], i));
        }
        
        Arrays.sort(priorities);
        int priIdx = priorities.length-1;
        while(!que.isEmpty()) {
            Point p = que.poll();
            if(p.prior == priorities[priIdx]) {
                answer++;
                priIdx--;
                if(p.idx == location) break;
            } else {
                que.offer(p);
            }
        }
        
        return answer;
    }
}