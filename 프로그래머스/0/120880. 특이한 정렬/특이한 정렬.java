import java.util.*;
class Solution {
    public int[] solution(int[] numlist, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int num : numlist) {
            map.put(num, Math.abs(n - num));
        }
        
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> {
            int dist1 = map.get(o1);
            int dist2 = map.get(o2);
            
            if(dist1 == dist2) return o2 - o1;
            return dist1 - dist2;
        });
        
        int[] answer = new int[numlist.length];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}