import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pQue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<Integer> que = new LinkedList<>();
        
        for(int i = 0; i < priorities.length; i++) {
            pQue.offer(priorities[i]);
            que.offer(i);
        }
        
        while(!que.isEmpty()) {
            int idx = que.poll();
            
            if(priorities[idx] == pQue.peek()) {
                answer++;
                pQue.poll();
                
                if(idx == location) break;
            } else {
                que.offer(idx);
            }
        }
        
        return answer;
    }
}