import java.util.*;
class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = new int[emergency.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < emergency.length; i++) {
            map.put(i, emergency[i]);
        }
        
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o2) - map.get(o1));
        
        int num = 1;
        for(int i : list) {
            answer[i] = num++;
        }
        
        return answer;
    }
}