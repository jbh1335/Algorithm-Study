import java.util.*;
class Solution {
    static String[] select;
    static boolean[] visited;
    static HashSet<String> answerSet;
    public int solution(String[] user_id, String[] banned_id) {
        select = new String[banned_id.length];
        visited = new boolean[user_id.length];
        answerSet = new HashSet<>();
        
        per(0, user_id, banned_id);
        return answerSet.size();
    }
    
    // 순열로 뽑기
    public static void per(int cnt, String[] user_id, String[] banned_id) {
        if(cnt == banned_id.length) {
            // 조건이 맞으면
            if(checkOk(user_id, banned_id)) {
                StringBuilder sb = new StringBuilder();
                String[] newSelect = select.clone();
                // 오름차순 정렬하여 문자로 만들고 저장 -> 중복 제거를 위해
                Arrays.sort(newSelect);
                
                for(String id : newSelect) {
                    sb.append(id);
                }
                answerSet.add(sb.toString());
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
    
    // 조건에 맞는지 검사
    public static boolean checkOk(String[] user_id, String[] banned_id) {
        for(int i = 0; i < select.length; i++) {
            String id = select[i];
            String target = banned_id[i];

            if(id.length() != target.length()) return false;

            for(int j = 0; j < id.length(); j++) {
                if(target.charAt(j) == '*') continue;
                if(id.charAt(j) != target.charAt(j)) return false;
            }
        }

        return true;
    }
}