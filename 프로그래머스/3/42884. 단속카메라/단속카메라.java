import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (arr1, arr2) -> {
            if(arr1[0] == arr2[0]) return arr1[1] - arr2[1];
            return arr1[0] - arr2[0];
        });
        
        int beforeEnd = routes[0][1];
        for(int i = 1; i < routes.length; i++) {
            int start = routes[i][0];
            
            if(beforeEnd < start) {
                answer++;
                beforeEnd = routes[i][1];
            } else {
                beforeEnd = Math.min(beforeEnd, routes[i][1]);
            }
        }
        return answer;
    }
}