import java.util.*;
class Solution {
    static boolean[] visited;
    static HashSet<Integer> set;
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        set = new HashSet<>();
        
        for(int i = 1; i <= numbers.length(); i++) {
            per(0, i, "", numbers);
        }
        
        return set.size();
    }
    
    public static void per(int cnt, int length, String str, String numbers) {
        if(cnt == length) {
            if(checkPrimeNumber(Integer.parseInt(str))) set.add(Integer.parseInt(str));
            return;
        }
        
        for(int i = 0; i < numbers.length(); i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            per(cnt+1, length, str+numbers.charAt(i), numbers);
            visited[i] = false;
        }
    }
    
    public static boolean checkPrimeNumber(int num) {
        if(num == 0 || num == 1) return false;
        
        int sqrt = (int) Math.sqrt(num);
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) return false;
        }
        
        return true;
    }
}