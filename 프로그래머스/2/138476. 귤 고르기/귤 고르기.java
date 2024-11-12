import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o2) - map.get(o1));
        
        for(int i : list) {
            k -= map.get(i);
            answer++;
            if(k <= 0) break;
        }
        
        return answer;
    }
}