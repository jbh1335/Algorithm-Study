import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        
        for(int num : scoville) {
            pque.offer(num);
        }
        
        while(pque.size() >= 2) {
            if(pque.peek() >= K) break;
            
            int newNum = pque.poll() + pque.poll()*2;           
            pque.offer(newNum);
            answer++;
        }
        
        if(answer == scoville.length-1 && pque.peek() < K) answer = -1;
        return answer;
    }
}