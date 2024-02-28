import java.util.*;
class Solution {
    // bitwise XOR 한 값을 반환 -> 같으면 0 다르면 1 (^)
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, (arr1, arr2) -> {
            if(arr1[col-1] == arr2[col-1]) return arr2[0] - arr1[0];
            return arr1[col-1] - arr2[col-1];
        });
        
        for(int i = row_begin-1; i < row_end; i++) {
            int sum = 0;
            for(int j = 0; j < data[i].length; j++) {
                sum += data[i][j] % (i+1);
            }
            answer ^= sum;
        }
        return answer;
    }
}