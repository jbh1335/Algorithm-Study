import java.util.*;
class Solution {
    public static HashMap<String, Integer> wantMap, buyMap;
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        wantMap = new HashMap<>();
        for(int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        buyMap = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            buyMap.put(discount[i], buyMap.getOrDefault(discount[i], 0)+1);
        }
        
        if(checkOk()) answer++;    
        for(int i = 10; i < discount.length; i++) {
            buyMap.put(discount[i], buyMap.getOrDefault(discount[i], 0)+1);
            buyMap.put(discount[i-10], buyMap.get(discount[i-10])-1);
            
            if(checkOk()) answer++;
        }
        return answer;
    }
    
    public static boolean checkOk() {
        for(String s : wantMap.keySet()) {
            if(!buyMap.containsKey(s)) return false;
            if(wantMap.get(s) > buyMap.get(s)) return false;
        }
        return true;
    }
}