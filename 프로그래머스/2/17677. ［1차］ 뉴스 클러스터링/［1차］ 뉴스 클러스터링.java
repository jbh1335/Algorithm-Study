import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < str1.length(); i++) {
            if(i == str1.length()-1) break;
            char ch1 = str1.charAt(i);
            char ch2 = str1.charAt(i+1);
            
            if(checkAlpha(ch1)) {
                if(checkAlpha(ch2)) {
                    String s = "" + ch1 + ch2;
                    list.add(s);
                } else {
                    i++;
                }
            }
        }
        
        int common = 0, total = 0;
        for(int i = 0; i < str2.length(); i++) {
            if(i == str2.length()-1) break;
            char ch1 = str2.charAt(i);
            char ch2 = str2.charAt(i+1);
            
            if(checkAlpha(ch1)) {
                if(checkAlpha(ch2)) {
                    String s = "" + ch1 + ch2;
                    if(list.contains(s)) {
                        common++;
                        list.remove(s);
                    } else {
                        total++;
                    }
                } else {
                    i++;
                }
            }
        }
        
        total += common + list.size();
        if(total == 0) return 65536;
        
        answer = (int) ((double) common / total * 65536);
        return answer;
    }
    
    public static boolean checkAlpha(char ch) {
        if('a' <= ch && ch <= 'z') return true;
        return false;
    }
}