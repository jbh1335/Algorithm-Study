import java.util.*;
class Solution {
    public int solution(String[] strArr) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(String str : strArr) {
            int length = str.length();
            map.put(length, map.getOrDefault(length, 0) + 1);
            answer = Math.max(answer, map.get(length));
        }
        
        return answer;
    }
}