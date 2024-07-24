import java.util.*;
class Solution {
    public int solution(String before, String after) {
        int answer = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < before.length(); i++) {
            char ch = before.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        for(int i = 0; i < after.length(); i++) {
            char ch = after.charAt(i);
            if(!map.containsKey(ch) || map.get(ch) == 0) {
                answer = 0;
                break;
            }
            
            map.put(ch, map.get(ch)-1);
        }
        
        return answer;
    }
}