import java.util.*;
class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];
        
        for(int i = 0; i < queries.length; i++) {
            int s = queries[i][0], e = queries[i][1], k = queries[i][2];
            int min = Integer.MAX_VALUE;
            
            for(int j = s; j <= e; j++) {
                if(arr[j] > k) {
                    min = Math.min(min, arr[j]);
                }
            }
            
            answer[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        
        return answer;
    }
}