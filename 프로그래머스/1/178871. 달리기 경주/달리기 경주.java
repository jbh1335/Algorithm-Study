import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        
        for(int i = 0; i < callings.length; i++) {
            int idx = map.get(callings[i]);
            String frontPlayer = players[idx-1];
            
            players[idx-1] = callings[i];
            players[idx] = frontPlayer;
            
            map.replace(callings[i], idx-1);
            map.replace(frontPlayer, idx);
        }
        
        return players;
    }
}