import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int start = 0;
        for(int i = 0; i < A.length; i++) {
            for(int j = start; j < B.length; j++) {
                if(A[i] < B[j]) {
                    answer++;
                    start = j+1;
                    break;
                }
            }
            
            if(start == B.length) break;
        }
        return answer;
    }
}