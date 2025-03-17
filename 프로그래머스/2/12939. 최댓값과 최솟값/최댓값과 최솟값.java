import java.util.*;
class Solution {
    public String solution(String s) {
        String[] splitArr = s.split(" ");
        int[] numArr = new int[splitArr.length];
        
        for(int i = 0; i < numArr.length; i++) {
            numArr[i] = Integer.parseInt(splitArr[i]);
        }
        
        Arrays.sort(numArr);
        String answer = numArr[0] + " " + numArr[numArr.length-1];
        
        return answer;
    }
}