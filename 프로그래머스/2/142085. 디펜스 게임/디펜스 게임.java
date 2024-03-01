import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        // 무적권이 적보다 크거나 같으면 다 사용 가능
        if(k >= enemy.length) return enemy.length;
        // 적을 내림차순 정렬로 저장
        PriorityQueue<Integer> pque = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < enemy.length; i++) {
            // 적 저장
            pque.offer(enemy[i]);
            // 병사수 감소
            n -= enemy[i];
            
            // 만약 병사수가 0보다 작으면
            if(n < 0) {
                // 가장 큰 적(내림차순 정렬했음)을 제거하고 무적권을 사용해서 병사수 다시 증가 
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