import java.util.*;
class Solution {
    public int[][] solution(int[][] arr) {
        int length = Math.max(arr.length, arr[0].length);
        int[][] answer = new int[length][length];
        
        for(int i = 0; i < arr.length; i++) {
            answer[i] = Arrays.copyOf(arr[i], length);
        }
        
        return answer;
    }
}