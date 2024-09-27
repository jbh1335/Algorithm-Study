import java.util.*;
class Solution {
    static String[] strArr = {"aya", "ye", "woo", "ma"};
    static boolean[] visited;
    static ArrayList<String> list;
    public int solution(String[] babbling) {
        int answer = 0;
        list = new ArrayList<>();
        visited = new boolean[4];
        
        for(int i = 1; i <= 4; i++) {
            per(0, i, "");
        }
        
        for(String str : babbling) {
            for(String s : list) {
                if(str.equals(s)) {
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    public static void per(int cnt, int N, String str) {
        if(cnt == N) {
            list.add(str);
            return;
        }
        
        for(int i = 0; i < strArr.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            per(cnt+1, N, str+strArr[i]);
            visited[i] = false;
        }
    }
}