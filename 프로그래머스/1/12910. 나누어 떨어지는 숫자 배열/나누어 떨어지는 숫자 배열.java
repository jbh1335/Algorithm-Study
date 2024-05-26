import java.util.*;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int num : arr) {
            if(num % divisor == 0) list.add(num);
        }
        
        int[] answer = list.size() > 0 ? new int[list.size()] : new int[] {-1};
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
}