import java.util.*;
class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        
        for(int s = 1; s <= elements.length; s++) {
            for(int i = 0; i < elements.length; i++) {
                int sum = 0;
                
                for(int j = i; j < i+s; j++) {
                    int idx = j;
                    if(idx >= elements.length) idx -= elements.length;
                    sum += elements[idx];
                }
                
                set.add(sum);
            }
        }
        
        return set.size();
    }
}