import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        
        String[] strNumArr = new String[numbers.length];
        for(int i = 0; i < strNumArr.length; i++) {
            strNumArr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strNumArr, (str1, str2) -> (str2+str1).compareTo(str1+str2));
        
        if(strNumArr[0].equals("0")) return "0";
        
        for(String str : strNumArr) {
            sb.append(str);
        }
        
        return sb.toString();
    }
}