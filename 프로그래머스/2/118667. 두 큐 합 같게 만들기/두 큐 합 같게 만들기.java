import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        
        long sum1 = 0;
        for(int i : queue1) {
            que1.offer(i);
            sum1 += i;
        }
        
        long sum2 = 0;
        for(int i : queue2) {
            que2.offer(i);
            sum2 += i;
        }
        
        long goal = (sum1 + sum2) / 2;
        if((sum1 + sum2) % 2 != 0) return -1;
        
        int maxCnt = que1.size() + que2.size()+ Math.max(que1.size(), que2.size());
        while(sum1 != sum2) {
            if(++answer > maxCnt) {
                answer = -1;
                break;
            }
            
            if(sum1 > sum2) {
                int num = que1.poll();
                que2.offer(num);
                sum1 -= num;
                sum2 += num;
            } else {
                int num = que2.poll();
                que1.offer(num);
                sum1 += num;
                sum2 -= num;
            }
        }
        
        return answer;
    }
}