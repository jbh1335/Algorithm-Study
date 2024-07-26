import java.util.*;
class Solution {
    public int solution(int[] array, int n) {
        int answer = 0, min = 100;
        Arrays.sort(array);
        
        for(int num : array) {
            int dif = Math.abs(num - n);
            if(dif < min) {
                answer = num;
                min = dif;
            }
        }
        
        return answer;
    }
}