import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        if(k >= enemy.length) return enemy.length;
        PriorityQueue<Integer> pque = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        for(int i = 0; i < enemy.length; i++) {
            pque.offer(enemy[i]);
            n -= enemy[i];
            
            if(n < 0) {
                if(k > 0) {
                    n += pque.poll();
                    k--;
                } else {
                    break;
                }
            }
            
            answer++;
        }
        
        return answer;
    }
}