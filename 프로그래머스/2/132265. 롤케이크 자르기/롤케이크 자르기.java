import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> leftMap = new HashMap<>(); // 형
        HashMap<Integer, Integer> rightMap = new HashMap<>(); // 동생
        
        leftMap.put(topping[0], 1);
        for(int i = 1; i < topping.length; i++) {
            rightMap.put(topping[i], rightMap.getOrDefault(topping[i], 0) + 1);
        }
        
        if(leftMap.size() == rightMap.size()) answer++;
        
        for(int i = 1; i < topping.length; i++) {
            int num = topping[i];
            // 형 추가하고
            leftMap.put(num, leftMap.getOrDefault(num, 0) + 1);
            
            // 동생은 하나 빼주기
            if(rightMap.get(num) == 1) rightMap.remove(num);
            else rightMap.put(num, rightMap.getOrDefault(num, 0) - 1);
            
            if(leftMap.size() == rightMap.size()) answer++;
        }
        return answer;
    }
}