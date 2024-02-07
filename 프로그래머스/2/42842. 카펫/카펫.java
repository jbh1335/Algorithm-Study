class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;
        int sqrt = (int) Math.sqrt(total);
        
        for(int i = 1; i <= sqrt; i++) {
            if(total % i == 0) {
                answer[0] = Math.max(i, (total)/i);
                answer[1] = Math.min(i, (total)/i);
                if(checkOk(answer[0], answer[1], brown, yellow)) break;
            }
        }
        
        return answer;
    }
    
    public static boolean checkOk(int width, int height, int brown, int yellow) {
        int brownNum = 2 * (width+height) - 4;
        int yellowNum = (width * height) - brownNum;
        
        if(brownNum == brown && yellowNum == yellow) return true;
        return false;
    }
}