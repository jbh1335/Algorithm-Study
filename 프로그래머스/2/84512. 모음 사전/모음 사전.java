import java.util.*;
class Solution {
    static char[] arr = {'A', 'E', 'I', 'O', 'U'};
    static ArrayList<String> list;
    public int solution(String word) {
        int answer = 0;
        list = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            per(0, i, "");
        }
        
        Collections.sort(list);
        for(String str : list) {
            answer++;
            if(str.equals(word)) break;
        }
        
        return answer;
    }
    
    public static void per(int cnt, int N, String str) {
        if(cnt == N) {
            list.add(str);
            return;
        }
        
        for(int i = 0; i < 5; i++) {
            per(cnt+1, N, str+arr[i]);
        }
    }
}