import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        int[] cntArr = new int[26];
        Arrays.fill(cntArr, 101);
        
        for(String key : keymap) {
            for(int i = 0; i < key.length(); i++) {
                cntArr[key.charAt(i)-'A'] = Math.min(i+1, cntArr[key.charAt(i)-'A']);
            }
        }
        
        for(int i = 0; i < targets.length; i++) {
            for(int j = 0; j < targets[i].length(); j++) {
                if(cntArr[targets[i].charAt(j)-'A'] == 101) {
                    answer[i] = -1;
                    break;
                }
                
                answer[i] += cntArr[targets[i].charAt(j)-'A'];
            }
        }
        
        return answer;
    }
}