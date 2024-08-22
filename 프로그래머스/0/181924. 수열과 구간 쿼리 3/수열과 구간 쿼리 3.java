class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for(int[] quer : queries) {
            int tmp = arr[quer[0]];
            arr[quer[0]] = arr[quer[1]];
            arr[quer[1]] = tmp;
        }
        
        return arr;
    }
}