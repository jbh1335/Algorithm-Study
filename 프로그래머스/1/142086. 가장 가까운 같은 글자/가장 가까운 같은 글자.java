import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            int idx = map.getOrDefault(s.charAt(i), -1);
            answer[i] = idx == -1 ? idx : i - idx;
            map.put(s.charAt(i), i);
        }
        
        return answer;
    }
}