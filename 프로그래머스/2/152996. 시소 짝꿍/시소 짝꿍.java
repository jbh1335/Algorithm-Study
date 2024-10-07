import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        
        for(int i = 0; i < weights.length-1; i++) {
            int target = weights[i];
            for(int j = i+1; j < weights.length; j++) {
                // 1:1 2:1 3:2 4:3
                if(target == weights[j] || target*2 == weights[j] || 
                   target*3 == weights[j]*2 || target*4 == weights[j]*3) {
                    answer++;
                }
                
                if(target*2 < weights[j]) break;
            }
        }
        
        return answer;
    }
}