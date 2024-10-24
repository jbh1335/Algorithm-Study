import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        for(int num : B) {
            pque.offer(num);
        }       
        
        for(int aNum : A) {
            while(!pque.isEmpty()) {
                int bNum = pque.poll();
                if(bNum > aNum) {
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}