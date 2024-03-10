import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        long start = 0, end = (long) times[times.length-1] * n;
        
        while(start <= end) {
            long mid = (start + end) / 2;
            long num = 0;
            // mid분동안 심사하면 총 몇명할 수 있는지 검사
            for(int i = 0; i < times.length; i++) {
                num += mid / times[i];
            }
            // 가능
            if(n <= num) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return answer;
    }
}