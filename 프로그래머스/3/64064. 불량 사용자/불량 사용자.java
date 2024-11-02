import java.util.*;
class Solution {
    static int N;
    static boolean[] visited;
    static String[] select;
    static HashSet<String> set;
    public int solution(String[] user_id, String[] banned_id) {
        N = banned_id.length;
        visited = new boolean[user_id.length];
        select = new String[N];
        set = new HashSet<>();
        
        per(0, user_id, banned_id);
        
        return set.size();
    }
    
    public static void per(int cnt, String[] user_id, String[] banned_id) {
        if(cnt == N) {
            if(checkOk(banned_id)) {
                String[] newSelect = select.clone();
                Arrays.sort(newSelect);
                
                String str = "";
                for(String id : newSelect) {
                    str += id;
                }
                
                set.add(str);
            } 
            
            return;
        }
        
        for(int i = 0; i < user_id.length; i++) {
            if(visited[i]) continue;
            
            select[cnt] = user_id[i];
            visited[i] = true;
            per(cnt+1, user_id, banned_id);
            visited[i] = false;
        }
    }
    
    public static boolean checkOk(String[] banned_id) {
        for(int i = 0; i < N; i++) {
            String id = banned_id[i];
            if(id.length() != select[i].length()) return false;
            
            for(int j = 0; j < id.length(); j++) {
                if(id.charAt(j) == '*') continue;
                if(id.charAt(j) != select[i].charAt(j)) return false;
            }
        }
        
        return true;
    }
}