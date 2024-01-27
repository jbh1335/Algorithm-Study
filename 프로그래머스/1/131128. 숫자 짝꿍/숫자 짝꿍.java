import java.util.*;
class Solution {
    public String solution(String X, String Y) {
        int[] xArr = new int[10];
        int[] yArr = new int[10];
        
        for(int i = 0; i < X.length(); i++) {
            xArr[X.charAt(i)-'0']++;
        }
        
        for(int i = 0; i < Y.length(); i++) {
            yArr[Y.charAt(i)-'0']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 9; i >= 0; i--) {
            while(true) {
                if(xArr[i] > 0 && yArr[i] > 0) {
                    sb.append(i);
                    xArr[i]--;
                    yArr[i]--;
                } else {
                    break;
                }
            }
        }
        
        if(sb.length() == 0) return "-1";
        if(sb.toString().charAt(0) == '0') return "0";
        return sb.toString();
    }
}