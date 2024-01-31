import java.util.*;
class Solution {
    static HashMap<Character, Integer> map = new HashMap<>();
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        char[] chArr = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        for(int i = 0; i < 8; i++) {
            map.put(chArr[i], 0);
        }
        
        for(int i = 0; i < survey.length; i++) {
            giveScore(survey[i].charAt(0), survey[i].charAt(1), choices[i]);
        }
        
        for(int i = 0; i < 8; i+=2) {
            int win = 0;
            if(map.get(chArr[i]) > map.get(chArr[i+1])) win = i;
            else if(map.get(chArr[i]) < map.get(chArr[i+1])) win = i+1;
            else {
                if(chArr[i] < chArr[i+1]) win = i;
                else win = i+1;
            }
            answer += chArr[win];
        }
        return answer;
    }
    
    public static void giveScore(char c1, char c2, int choice) {
        if(choice == 1) {
            map.put(c1, map.get(c1) + 3);
        } else if(choice == 2) {
            map.put(c1, map.get(c1) + 2);
        } else if(choice == 3) {
            map.put(c1, map.get(c1) + 1);
        } else if(choice == 5) {
            map.put(c2, map.get(c2) + 1);
        } else if(choice == 6) {
            map.put(c2, map.get(c2) + 2);
        } else if(choice == 7) {
            map.put(c2, map.get(c2) + 3);
        }
    }
}