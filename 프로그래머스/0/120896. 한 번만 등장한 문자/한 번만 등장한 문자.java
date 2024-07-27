import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        ArrayList<Character> list = new ArrayList<>();
        for(char ch : map.keySet()) {
            if(map.get(ch) == 1) list.add(ch);
        }
        
        Collections.sort(list);
        for(char ch : list) {
            answer += ch;
        }
        
        return answer;
    }
}