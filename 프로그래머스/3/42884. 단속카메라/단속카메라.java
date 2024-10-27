import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int beforeEnd = -30001;
        Arrays.sort(routes, (arr1, arr2) -> arr1[1] - arr2[1]);
        
        for(int[] route : routes) {
            if(route[0] <= beforeEnd) {
                continue;
            } else {
                answer++;
                beforeEnd = route[1];
            }
        }
        
        return answer;
    }
}