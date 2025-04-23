import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        
        for(int i : scoville) {
            pque.offer(i);
        }
        
        while(pque.size() >= 2) {
            if(pque.peek() < K) {
                pque.offer(pque.poll() + (pque.poll() * 2));
            } else {
                break;
            }
            answer++;
        }
        
        if(pque.peek() < K) answer = -1;
        return answer;
    }
}