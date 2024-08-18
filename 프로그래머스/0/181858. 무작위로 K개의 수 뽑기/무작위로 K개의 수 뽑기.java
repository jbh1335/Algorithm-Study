import java.util.*;
class Solution {
    public int[] solution(int[] arr, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int num : arr) {
            if(!list.contains(num)) list.add(num);
        }
        
        int[] answer = new int[k];
        for(int i = 0; i < k; i++) {
            if(i < list.size()) answer[i] = list.get(i);
            else answer[i] = -1;
        }
        
        return answer;
    }
}