import java.util.*;
class Solution {
    static ArrayList<int[]> list;
    public int[][] solution(int n) {
        list = new ArrayList<>();
        // 1 -> 2
        // 1 -> 3
        // 2 -> 3
        hanoi(n, 1, 2, 3);
        
        int[][] answer = new int[list.size()][2];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public static void hanoi(int n, int start, int tmp, int end) {
        if(n == 1) {
            list.add(new int[] {start, end});
            return;
        }
        
        hanoi(n-1, start, end, tmp);
        list.add(new int[] {start, end});
        hanoi(n-1, tmp, start, end);
    }
}