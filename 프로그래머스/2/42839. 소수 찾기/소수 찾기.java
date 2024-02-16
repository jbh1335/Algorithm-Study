import java.util.*;
class Solution {
    static int answer;
    static int[] select;
    static boolean[] visited;
    static HashSet<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        
        for(int i = 1; i <= numbers.length(); i++) {
            select = new int[i];
            per(0, i, numbers);
        }
        return answer;
    }
    
    public static void per(int cnt, int size, String numbers) {
        if(cnt == size) {
            String num = "";
            for(int i = 0; i < size; i++) {
                num += select[i];
            }
            
            if(checkOk(Integer.parseInt(num))) {
                if(set.add(Integer.parseInt(num))) answer++;
            }
            return;
        }
        
        for(int i = 0; i < numbers.length(); i++) {
            if(visited[i]) continue;
            
            select[cnt] = numbers.charAt(i) - '0';
            visited[i] = true;
            per(cnt+1, size, numbers);
            visited[i] = false;
        }
    }
    
    public static boolean checkOk(int num) {
        if(num == 0 || num == 1) return false;
        
        int sqrt = (int) Math.sqrt(num);
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}