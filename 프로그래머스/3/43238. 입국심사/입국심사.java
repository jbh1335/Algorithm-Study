import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long start = 0, end = (long) n * times[times.length-1], mid = 0;
        
        while(start <= end) {
            mid = (start + end) / 2;
            
            long count = 0;
            for(int i : times) {
                count += mid / i;
            }
            
            if(count >= n) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }
}