import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        ArrayList<int[]> list = new ArrayList<>();
        
        int start = 0, end = 0, sum = sequence[0];
        while(start <= end) {
            if(sum >= k) {
                if(sum == k) list.add(new int[] {start, end});
                sum -= sequence[start++];
            } else {
                if(++end == sequence.length) break;
                sum += sequence[end];
            }
        }
        
        Collections.sort(list, (arr1, arr2) -> {
            int length1 = arr1[1] - arr1[0];
            int length2 = arr2[1] - arr2[0];
            if(length1 == length2) {
                return arr1[0] - arr2[0];
            }
            return length1 - length2;
        });
        
        answer[0] = list.get(0)[0];
        answer[1] = list.get(0)[1];
        return answer;
    }
}