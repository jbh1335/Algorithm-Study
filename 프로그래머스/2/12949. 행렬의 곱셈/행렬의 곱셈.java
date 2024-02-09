class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for(int i = 0; i < arr1.length; i++) {
            for(int j = 0; j < arr1[0].length; j++) {
                for(int a = 0; a < arr2[0].length; a++) {
                    int sum = 0;
                    for(int b = 0; b < arr2.length; b++) {
                        sum += (arr1[i][b] * arr2[b][a]);
                    }
                    answer[i][a] = sum;
                }
            }
        }
        return answer;
    }
}