import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        int count = 0;
        
        for(int i = 0; i < weights.length-1; i++) {
            // 똑같은 숫자가 나오면 그전의 경우와 같으므로 -1을 한 개수만큼 추가하고 끝내기
            if(i != 0 && weights[i-1] == weights[i]) {
                answer += --count;
                continue;
            }
            
            count = 0;
            for(int j = i+1; j < weights.length; j++) {
                if(2*weights[i] < weights[j]) break;
                
                if(weights[i] == weights[j] ||
                  (3*weights[i] == 2*weights[j]) ||
                  (2*weights[i] == 1*weights[j]) ||
                  (4*weights[i] == 3*weights[j])) {
                    count++;
                }
            }
            answer += count;
        }
        
        return answer;
    }
}