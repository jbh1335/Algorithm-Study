import java.util.*;
class Solution {
    public int solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o2) - map.get(o1));
        
        int answer = list.get(0);
        if(list.size() > 1 && map.get(list.get(0)) == map.get(list.get(1))) answer = -1;
        
        return answer;
    }
}