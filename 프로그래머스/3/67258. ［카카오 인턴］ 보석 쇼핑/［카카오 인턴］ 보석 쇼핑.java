import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        // 종류를 저장한 typeSet
        HashSet<String> typeSet = new HashSet<>();
        for(int i = 0; i < gems.length; i++) {
            typeSet.add(gems[i]);
        }
        int totalType = typeSet.size();
        
        // 투포인터로 찾기
        HashMap<String, Integer> map = new HashMap<>();
        map.put(gems[0], 1);
        int start = 0, end = 0, min = gems.length+1;
        while(start < gems.length) {
            if(map.size() >= totalType) {
                if(map.size() == totalType) {
                    int num = end - start + 1;
                    if(num < min) {
                        min = num;
                        answer[0] = start + 1;
                        answer[1] = end + 1;
                    }
                }
                
                if(map.get(gems[start]) == 1) map.remove(gems[start]);
                else map.put(gems[start], map.get(gems[start])-1);
                start++;
            } else {
                if(++end == gems.length) break;
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            }
        }
        return answer;
    }
}