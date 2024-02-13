import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        for(int i : scoville) {
            pque.offer(i);
        }
        
        boolean flag = false;
        while(true) {
            if(pque.peek() >= K) {
                flag = true;
                break;
            }
            
            if(pque.size() == 1) break;
            
            // 새로운 스코빌 지수: 가장 작은 수 + (2번째로 작은 수*2)
            int newNum = pque.poll() + (pque.poll() * 2);
            pque.offer(newNum);
            answer++;
        }
        
        if(!flag) answer = -1;
        return answer;
    }
}