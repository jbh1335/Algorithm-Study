import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        int length = 1;
        while(length < arr.length) {
            length *= 2;
        }
        
        int[] answer = Arrays.copyOf(arr, length);   
        return answer;
    }
}