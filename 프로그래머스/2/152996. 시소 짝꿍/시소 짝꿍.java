import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int count = 0;
        Arrays.sort(weights);
        
        for(int i = 0; i < weights.length-1; i++) {
            int a = weights[i];
            
            if(i != 0 && a == weights[i-1]) {
                answer += --count;
                continue;
            }
            
            count = 0;
            for(int j = i+1; j < weights.length; j++) {
                int b = weights[j];
                
                if(a*2 < b) break;
                if(a == b || a*2 == b || a*3 == b*2 || a*4 == b*3) {
                    count++;
                }
            }
            
            answer += count;
        }
        
        return answer;
    }
}