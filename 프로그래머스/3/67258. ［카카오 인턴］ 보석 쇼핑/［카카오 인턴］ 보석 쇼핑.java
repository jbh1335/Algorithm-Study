import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[] {1, gems.length};
        HashSet<String> gemType = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String gem : gems) {
            gemType.add(gem);
        }
        
        int start = 0, end = 0, count = 1, min = gems.length;
        map.put(gems[0], 1);
        
        while(end < gems.length) {
            if(count < gemType.size()) {
                if(++end == gems.length) break;
                
                if(map.containsKey(gems[end])) {
                    map.put(gems[end], map.get(gems[end]) + 1);
                } else {
                    map.put(gems[end], 1);
                    count++;
                }
            } else {
                if(count == gemType.size()) {
                    if(end-start+1 < min) {
                        answer[0] = start + 1;
                        answer[1] = end + 1;
                        min = end - start + 1;
                    }
                }
                
                if(map.get(gems[start]) == 1) {
                    map.remove(gems[start]);
                    count--;
                } else {
                    map.put(gems[start], map.get(gems[start]) - 1);
                }
                start++;
            }
        }
        
        return answer;
    }
}