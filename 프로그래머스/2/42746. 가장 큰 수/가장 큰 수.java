import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strNum = new String[numbers.length];
        int count = 0;
        
        for(int i = 0; i < strNum.length; i++) {
            if(numbers[i] == 0) count++;
            strNum[i] = String.valueOf(numbers[i]);
        }
        
        // 원소가 모두 0이면 0리턴
        if(count == numbers.length) return "0";
        
        Arrays.sort(strNum, (o1, o2) -> {
            int num1 = Integer.parseInt(o1+o2);
            int num2 = Integer.parseInt(o2+o1);
            
            return num2 - num1;
        });
        
        for(String str : strNum) {
            answer += str;
        }
        
        return answer;
    }
}