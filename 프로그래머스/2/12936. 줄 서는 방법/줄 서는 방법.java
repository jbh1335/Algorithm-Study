import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 1; i <= n; i++) {
            list.add(i);
        }
        
        long num = fact(n);
        int idx = 0;
        k--;
        
        while(n > 0) {
            num /= n--;
            int i = (int) (k / num);
            answer[idx++] = list.get(i);
            list.remove(i);
            k %= num;
        }
        
        return answer;
    }
    
    public static long fact(int n) {
        if(n == 1) return 1;
        return n*fact(n-1);
    }
}