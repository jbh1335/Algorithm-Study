import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {-1};
        int start = -1, end = 0;
        
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 2) {
                if(start == -1) start = i;
                end = i;
            }
        }
        
        if(start != -1) answer = Arrays.copyOfRange(arr, start, end+1);
        return answer;
    }
}