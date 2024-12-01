class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        
        if(slope(dots[0], dots[1]) == slope(dots[2], dots[3])) answer = 1;
        else if(slope(dots[0], dots[2]) == slope(dots[1], dots[3])) answer = 1;
        else if(slope(dots[0], dots[3]) == slope(dots[1], dots[2])) answer = 1;
        
        return answer;
    }
    
    public static double slope(int[] arr1, int[] arr2) {
        return (double) (arr2[1] - arr1[1]) / (arr2[0] - arr1[0]);
    }
}