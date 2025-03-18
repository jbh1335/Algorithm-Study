import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        char before = words[0].charAt(words[0].length() - 1);
        
        HashSet<String> set = new HashSet<>();
        set.add(words[0]);

        for(int i = 1; i < words.length; i++) {
            if(!set.add(words[i]) || words[i].charAt(0) != before) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            
            before = words[i].charAt(words[i].length() - 1);
        }
        
        return answer;
    }
}