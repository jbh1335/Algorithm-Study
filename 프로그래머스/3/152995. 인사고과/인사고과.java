import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        // 인센티브 받을 수 있는 사람들의 인덱스, 점수합을 저장하는 resultMap
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        // 모든 사람의 인덱스, scores배열 저장하는 map
        HashMap<Integer, int[]> map = new HashMap<>();
        for(int i = 0 ; i < scores.length; i++) {
            map.put(i, scores[i]);
        }
        
        // 태도 점수 내림차순, 평가 점수에 따라 오름차순 정렬
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> {
            if(map.get(o1)[0] == map.get(o2)[0]) return map.get(o1)[1] - map.get(o2)[1];
            return map.get(o2)[0] - map.get(o1)[0];
        });
        
        int firstIdx = list.get(0);
        resultMap.put(firstIdx, map.get(firstIdx)[0]+map.get(firstIdx)[1]);
        int maxScore = map.get(firstIdx)[1];
        for(int i = 1; i < list.size(); i++) {
            int idx = list.get(i);
            // 동료평가 점수가 더 낮으면 (태도점수는 내림차순이기 때문에 검사 X)
            if(map.get(idx)[1] < maxScore) {
                if(idx == 0) return -1;
            } else {
                maxScore = map.get(idx)[1];
                resultMap.put(idx, map.get(idx)[0]+map.get(idx)[1]);
            }
        }
        
        // 총합 높은 순으로 정렬
        list = new ArrayList<>(resultMap.keySet());
        Collections.sort(list, (o1, o2) -> resultMap.get(o2) - resultMap.get(o1));
        
        answer = list.indexOf(0) + 1;
        return answer;
    }
}