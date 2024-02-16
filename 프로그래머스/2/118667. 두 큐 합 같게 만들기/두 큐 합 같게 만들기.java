import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        
        long sum1 = 0;
        for(int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            que1.offer(queue1[i]);
        }
        
        long sum2 = 0;
        for(int i = 0; i < queue2.length; i++) {
            sum2 += queue2[i];
            que2.offer(queue2[i]);
        }
        
        if((sum1 + sum2) % 2 != 0) return -1;
        long target = (sum1 + sum2) / 2;
        int count = 0;
        boolean flag = true;
        while(true) {
            if(count >= (queue1.length+queue2.length)*2) {
                flag = false;
                break;
            }
            
            if(sum1 == sum2) {
                break;
            } else if(sum1 > sum2) {
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
            count++;
        }
        
        if(flag) answer = count;
        return answer;
    }
}