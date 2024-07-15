import java.util.*;
class Solution {
    public String solution(String my_string, int[] indices) {
        String answer = "";
        ArrayList<Character> list = new ArrayList<>();
        Arrays.sort(indices);
        
        for(int i = 0; i < my_string.length(); i++) {
            list.add(my_string.charAt(i));
        }
        
        for(int i = indices.length-1; i >= 0; i--) {
            list.remove(indices[i]);
        }
        
        for(char ch : list) {
            answer += ch;
        }
        
        return answer;
    }
}