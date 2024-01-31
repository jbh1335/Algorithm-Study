class Solution {
    public int[] solution(String[] wallpaper) {
        int lux = Integer.MAX_VALUE, luy = Integer.MAX_VALUE, 
            rdx = Integer.MIN_VALUE, rdy = Integer.MIN_VALUE;
        
        for(int i = 0; i < wallpaper.length; i++) {
            String str = wallpaper[i];
            for(int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == '#') {
                    lux = Math.min(lux, i);
                    luy = Math.min(luy, j);
                    rdx = Math.max(rdx, i);
                    rdy = Math.max(rdy, j);
                }
            }
        }
        
        int[] answer = {lux, luy, rdx+1, rdy+1};
        return answer;
    }
}