class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for(int[] quer : queries) {
            for(int i = quer[0]; i <= quer[1]; i++) {
                if(i % quer[2] == 0) arr[i]++;
            }
        }
        
        return arr;
    }
}