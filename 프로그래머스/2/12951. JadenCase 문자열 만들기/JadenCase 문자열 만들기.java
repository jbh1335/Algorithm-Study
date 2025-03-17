import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        String[] splitArr = s.split("\\s+");
        String[] splitSpace = s.split("[^ ]+");
        
        for(int i = 0; i < splitArr.length; i++) {
            String str = splitArr[i];
            sb.append(String.valueOf(str.charAt(0)).toUpperCase()).append(str.substring(1, str.length()));
            if(i+1 < splitSpace.length) sb.append(splitSpace[i+1]);
        }
        
        return sb.toString();
    }
}