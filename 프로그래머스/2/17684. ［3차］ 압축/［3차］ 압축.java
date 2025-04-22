import java.util.*;
class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        char ch = 'A';
        for(int i = 1; i <= 26; i++) {
            map.put(String.valueOf(ch++), i);
        }
        
        int idx = 0, num = 27;
        while(true) {
            String str = String.valueOf(msg.charAt(idx++));
            
            while(map.containsKey(str) && idx < msg.length()) {
                str += msg.charAt(idx++);
            }
            
            if(idx == msg.length()) {
                if(str.length() == 1 || map.containsKey(str)) {
                    list.add(map.get(str));
                } else {
                    list.add(map.get(str.substring(0, str.length()-1)));
                    list.add(map.get(String.valueOf(msg.charAt(idx-1))));
                }
                break;
            }
            
            list.add(map.get(str.substring(0, str.length()-1)));
            map.put(str, num++);
            idx--;
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}