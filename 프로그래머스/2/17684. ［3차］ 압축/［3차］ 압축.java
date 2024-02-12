import java.util.*;
class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        int num = 1;
        for(char c = 'a'; c <= 'z'; c++) {
            map.put(String.valueOf(c), num++);
        }
        
        msg = msg.toLowerCase();
        ArrayList<Integer> list = new ArrayList<>();
        int start = 0, end = 1;
        String w = "";
        while(end <= msg.length()) {
            while(true) {
                if(end > msg.length()) {
                    list.add(map.get(w));
                    break;
                }
                
                String str = msg.substring(start, end);
                if(map.containsKey(str)) {
                    w = str;
                    end++;
                } else {
                    list.add(map.get(w));
                    map.put(str, num++);
                    start = end-1;
                    break;
                }
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}