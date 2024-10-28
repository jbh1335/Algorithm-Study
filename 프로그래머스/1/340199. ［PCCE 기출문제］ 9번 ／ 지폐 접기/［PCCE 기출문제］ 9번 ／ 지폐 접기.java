import java.util.*;
class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        Arrays.sort(wallet);
        
        while(true) {
            Arrays.sort(bill); 
            if(bill[0] <= wallet[0] && bill[1] <= wallet[1]) break;
            
            bill[1] /= 2;
            answer++;
        }
        
        return answer;
    }
}