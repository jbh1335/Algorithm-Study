import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        int count = 0;
        
        for(int i = 0; i < weights.length-1; i++) {
            int a = weights[i];
            // 똑같은 숫자가 나오면 그전의 경우와 같으므로 -1을 한 개수만큼 추가하고 끝내기
            if(i != 0 && a == weights[i-1]) {
                // answer += (count - 1) 로 하면 안됨
                // 같은 숫자가 연속으로 3번나오면 3번째는 -1이 한번 더 적용되지 않음
                answer += --count;
                continue;
            }
            
            count = 0;
            for(int j = i+1; j < weights.length; j++) {
                int b = weights[j];
                if(a == b || 2*a == b || 3*a == 2*b || 4*a == 3*b) count++;
            }
            
            answer += count;
        }
        return answer;
    }
}