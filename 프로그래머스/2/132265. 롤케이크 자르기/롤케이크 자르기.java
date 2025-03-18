import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        HashMap<Integer, Integer> leftMap = new HashMap<>();
        HashMap<Integer, Integer> rightMap = new HashMap<>();
        
        for(int i = 0; i < topping.length; i++) {
            rightMap.put(topping[i], rightMap.getOrDefault(topping[i], 0) + 1);
        }
        
        for(int i = 0; i < topping.length; i++) {
            int type = topping[i];
            
            leftMap.put(type, leftMap.getOrDefault(type, 0) + 1);
            if(rightMap.get(type) == 1) rightMap.remove(type);
            else rightMap.put(type, rightMap.get(type) - 1);
            
            if(leftMap.size() == rightMap.size()) answer++;
            if(leftMap.size() > rightMap.size()) break;
        }
        
        return answer;
    }
}