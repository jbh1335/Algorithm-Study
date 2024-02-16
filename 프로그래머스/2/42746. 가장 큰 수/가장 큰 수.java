import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] strNum = new String[numbers.length];
        int count = 0;
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] == 0) count++;
            strNum[i] = String.valueOf(numbers[i]);
        }
        
        if(count == numbers.length) return "0";
        
        Arrays.sort(strNum, (o1, o2) -> {
            int num1 = Integer.parseInt(o1 + o2);
            int num2 = Integer.parseInt(o2 + o1);
            return num2 - num1;
        });
        
        StringBuilder sb = new StringBuilder();
        for(String s : strNum) {
            sb.append(s);
        }
        return sb.toString();
    }
}