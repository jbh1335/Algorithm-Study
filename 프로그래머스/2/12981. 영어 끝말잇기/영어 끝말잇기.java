import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        char beforeLastCh = words[0].charAt(words[0].length()-1);
        ArrayList<String> list = new ArrayList<>();
        list.add(words[0]);
        for(int i = 1; i < words.length; i++) {
            String str = words[i];
            
            if(list.contains(str) || beforeLastCh != str.charAt(0)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            
            beforeLastCh = str.charAt(str.length()-1);
            list.add(str);
        }

        return answer;
    }
}