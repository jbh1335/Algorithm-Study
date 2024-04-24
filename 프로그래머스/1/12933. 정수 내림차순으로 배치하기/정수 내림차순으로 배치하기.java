import java.util.*;
class Solution {
    public long solution(long n) {
        long answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(n > 0) {
            list.add((int) (n % 10));
            n /= 10;
        }
        
        Collections.sort(list, Collections.reverseOrder());
        String str = "";
        for(int i : list) {
            str += i;
        }
        answer = Long.valueOf(str);
        return answer;
    }
}