import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0, end = people.length - 1;
        Arrays.sort(people);
        
        for(int i = 0; i <= end; i++) {
            for(int j = end; j > i; j--) {
                end--;
                if(people[i] + people[j] <= limit) break;
                answer++;
            }
            
            answer++;
        }
        
        return answer;
    }
}