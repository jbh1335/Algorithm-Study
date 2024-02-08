import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        while(true) {
            if(n == 1) {
                ans++;
                break;
            }
            
            if(n % 2 == 0) {
                n = divide(n);
            } else {
                ans++;
                n = divide(n-1);
            }
        }

        return ans;
    }
    
    public static int divide(int num) {
        while(true) {
            if(num == 1) break;
            if(num % 2 == 0) num /= 2;
            else break;
        }
        return num;
    }
}