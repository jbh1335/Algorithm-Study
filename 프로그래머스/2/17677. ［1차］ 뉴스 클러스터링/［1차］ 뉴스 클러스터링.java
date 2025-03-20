import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        
        str1 = str1.toLowerCase();
        for(int i = 0; i < str1.length()-1; i++) {
            String str = str1.substring(i, i+2);
            
            if(str.replaceAll("[^a-z]", "").equals(str)) {
                map1.put(str, map1.getOrDefault(str, 0) + 1);
            }
        }
        
        str2 = str2.toLowerCase();
        for(int i = 0; i < str2.length()-1; i++) {
            String str = str2.substring(i, i+2);
            
            if(str.replaceAll("[^a-z]", "").equals(str)) {
                map2.put(str, map2.getOrDefault(str, 0) + 1);
            }
            
            if(map1.containsKey(str)) set.add(str);
        }
        
        if(map1.size() == 0 && map2.size() == 0) return 65536;
        
        int common = 0, total = 0;
        for(String str : set) {
            common += Math.min(map1.get(str), map2.get(str));
            total += Math.max(map1.get(str), map2.get(str));
            
            map1.remove(str);
            map2.remove(str);
        }
        
        for(String str : map1.keySet()) {
            total += map1.get(str);
        }
        
        for(String str : map2.keySet()) {
            total += map2.get(str);
        }
        
        answer = (int) ((double) common / total * 65536);
        return answer;
    }
}