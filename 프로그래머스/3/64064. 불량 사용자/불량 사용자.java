import java.util.*;
class Solution {
    static int answer;
    static boolean isFound;
    static String[] select;
    public int solution(String[] user_id, String[] banned_id) {
        select = new String[banned_id.length];
        
        com(0, 0, user_id, banned_id);
        return answer;
    }
    
    public static void com(int cnt, int start, String[] user_id, String[] banned_id) {
        if(cnt == banned_id.length) {
            isFound = false;
            per(0, new boolean[cnt], new String[cnt], banned_id);
            return;
        }
        
        for(int i = start; i < user_id.length; i++) {
            select[cnt] = user_id[i];
            com(cnt+1, i+1, user_id, banned_id);
        }
    }
    
    public static void per(int cnt, boolean[] visited, String[] perSel, String[] banned_id) {
        if(isFound) return;
        if(cnt == select.length) {
            boolean isOk = true;
            loop:
            for(int i = 0; i < cnt; i++) {
                String id = perSel[i];
                String target = banned_id[i];
                
                if(id.length() != target.length()) {
                    isOk = false;
                    break;
                }
                
                for(int j = 0; j < id.length(); j++) {
                    if(target.charAt(j) == '*') continue;
                    if(id.charAt(j) != target.charAt(j)) {
                        isOk = false;
                        break loop;
                    }
                }
            }
            
            if(isOk) {
                isFound = true;
                answer++;
            }
            return;
        }
        
        for(int i = 0; i < select.length; i++) {
            if(isFound) return;
            if(visited[i]) continue;
            
            perSel[cnt] = select[i];
            visited[i] = true;
            per(cnt+1, visited, perSel, banned_id);
            visited[i] = false;
        }
    }
}