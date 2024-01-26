class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int sameNum = 0, zero = 0;
        for(int i = 0; i < lottos.length; i++) {
            if(lottos[i] == 0) {
                zero++;
                continue;
            }
            
            boolean isSame = false;
            for(int j = 0; j < win_nums.length; j++) {
                if(lottos[i] == win_nums[j]) {
                    isSame = true;
                    break;
                }
            }
            
            if(isSame) sameNum++;
        }
        
        int max = sameNum + zero;
        int min = sameNum;
        
        int[] answer = {6, 6};
        int idx = 1;
        for(int i = 6; i >= 2; i--) {
            if(max == i) answer[0] = idx;
            if(min == i) answer[1] = idx;
            idx++;
        }
        return answer;
    }
}