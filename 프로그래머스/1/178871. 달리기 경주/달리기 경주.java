import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = players;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        
        for(String call : callings) {
            int idx = map.get(call);
            String frontPlayer = answer[idx-1];
            
            answer[idx-1] = answer[idx];
            answer[idx] = frontPlayer;
            
            map.replace(call, idx-1);
            map.replace(frontPlayer, idx);
        }
        
        return answer;
    }
}