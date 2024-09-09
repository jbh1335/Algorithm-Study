import java.util.*;
class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < rank.length; i++) {
            map.put(i, rank[i]);
        }
        
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o1) - map.get(o2));
        
        int[] student = new int[3];
        int idx = 0;
        for(int i : list) {
            if(attendance[i]) {
                student[idx] = i;
                if(++idx == 3) break;
            }
        }
        
        answer = 10000 * student[0] + 100 * student[1] + student[2];
        return answer;
    }
}