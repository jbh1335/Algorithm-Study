import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < 10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }
        
        if(checkOk(map, want, number)) answer++;
        
        for(int i = 1; i <= discount.length-10; i++) {
            map.put(discount[i-1], map.getOrDefault(discount[i-1], 0) - 1);
            map.put(discount[i+9], map.getOrDefault(discount[i+9], 0) + 1);
            
            if(checkOk(map, want, number)) answer++;
        }
        
        return answer;
    }
    
    public static boolean checkOk(HashMap<String, Integer> map, String[] want, int[] number) {
        for(int i = 0; i < want.length; i++) {
            if(map.getOrDefault(want[i], 0) < number[i]) return false;
        }
        
        return true;
    }
}