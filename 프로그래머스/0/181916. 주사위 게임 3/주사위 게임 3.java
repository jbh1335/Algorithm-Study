import java.util.*;
class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(a, map.getOrDefault(a, 0) + 1);
        map.put(b, map.getOrDefault(b, 0) + 1);
        map.put(c, map.getOrDefault(c, 0) + 1);
        map.put(d, map.getOrDefault(d, 0) + 1);
        
        if(map.size() == 1) {
            // 4
            answer = 1111 * a;
        } else if(map.size() == 2) {
            int num1 = 0, num2 = 0;
            for(int x : map.keySet()) {
                if(num1 == 0) num1 = x;
                else num2 = x;
            }
            
            // 3, 1
            if(map.get(num1) == 1) {
                answer = (int) Math.pow(10*num2+num1, 2);
            } else if(map.get(num1) == 3) {
                answer = (int) Math.pow(10*num1+num2, 2);
            } else {
                // 2, 2
                answer = (num1 + num2) * Math.abs(num1 - num2);
            }
        } else if(map.size() == 3) {
            // 2, 1, 1
            int num1 = 0, num2 = 0;
            for(int x : map.keySet()) {
                if(map.get(x) == 2) continue;
                if(num1 == 0) num1 = x;
                else num2 = x;
            }
            
            answer = num1 * num2;
        } else {
            // 1, 1, 1, 1
            answer = Math.min(a, Math.min(b, Math.min(c, d)));
        }
        
        return answer;
    }
}