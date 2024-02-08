import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int answer = 1;
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        
        for(int i = 2; i <= max; i++) {
            int count = 0;
            for(int j : arr) {
                if(j % i == 0) {
                    if(++count >= 2) break;
                }
            }
            
            if(count >= 2) {
                max = 0;
                for(int j = 0; j < arr.length; j++) {
                    if(arr[j] % i == 0) arr[j] /= i;
                    max = Math.max(max, arr[j]);
                }
                answer *= i;
                i = 1;
            }
        }
        
        for(int i : arr) {
            answer *= i;
        }
        return answer;
    }
}