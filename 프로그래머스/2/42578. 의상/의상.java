import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        
        // 의상 종류마다 몇개 있는지 저장
        for(int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        // 종류마다 의상의 수+1을 곱하고 마지막에 -1해주면 된다
        // +1을 하는 이유는 안입는 경우를 포함한 것이고 마지막에 -1을 하는 이유는 모두 안입었을 경우를 제외한 경우임
        // ex) 상의 2, 하의 3 있으면
        // (2+1) * (3+1) - 1 = 11
        for(String s : map.keySet()) {
            answer *= map.get(s) + 1;
        }
        
        answer--;
        return answer;
    }
}