import java.util.*;
class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        int[] answer = {-1};
        int a = slicer[0], b = slicer[1], c = slicer[2];
        
        if(n == 1) {
            answer = Arrays.copyOf(num_list, b+1);
        } else if(n == 2) {
            answer = Arrays.copyOfRange(num_list, a, num_list.length);
        } else if(n == 3) {
            answer = Arrays.copyOfRange(num_list, a, b+1);
        } else {
            int length = (b - a + 1) / c;
            if((b - a + 1) % c != 0) length++;
            
            answer = new int[length];
            int idx = 0;
            
            for(int i = a; i <= b; i+=c) {
                answer[idx++] = num_list[i];
            }
        }
        
        return answer;
    }
}