import java.util.*;
class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 2;
        
        for(String str : dic) {
            HashMap<Character, Integer> map = new HashMap<>();
            boolean isAble = true;
            
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
            
            for(String s : spell) {
                if(map.getOrDefault(s.charAt(0), 0) != 1) {
                    isAble = false;
                    break;
                }
            }
            
            if(isAble) {
                answer = 1;
                break;
            }
        }
        
        return answer;
    }
}