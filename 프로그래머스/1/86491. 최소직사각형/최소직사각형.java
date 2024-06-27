class Solution {
    public int solution(int[][] sizes) {
        int wMax = 0, hMax = 0;
        
        for(int[] arr : sizes) {
            int w = Math.min(arr[0], arr[1]);
            int h = Math.max(arr[0], arr[1]);
            
            wMax = Math.max(wMax, w);
            hMax = Math.max(hMax, h);
        }
        
        return wMax * hMax;
    }
}