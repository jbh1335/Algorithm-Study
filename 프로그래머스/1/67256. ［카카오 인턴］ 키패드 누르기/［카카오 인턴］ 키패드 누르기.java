class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int[][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2}};
        int left = -1, right = -2;
        String who = "";
        
        for(int num : numbers) {
            if(num == 1 || num == 4 || num == 7) {
                who = "L";
            } else if(num == 3 || num == 6 || num == 9) {
                who = "R";
            } else {
                int[] targetIdx = findIdx(num, keypad);
                int[] leftIdx = findIdx(left, keypad);
                int[] rightIdx = findIdx(right, keypad);
                
                int leftDist = Math.abs(leftIdx[0] - targetIdx[0]) + Math.abs(leftIdx[1] - targetIdx[1]);
                int rightDist = Math.abs(rightIdx[0] - targetIdx[0]) + Math.abs(rightIdx[1] - targetIdx[1]);
                
                if(leftDist == rightDist) {
                    if(hand.equals("left")) who = "L";
                    else who = "R";
                } else if(leftDist < rightDist) {
                    who = "L";
                } else {
                    who = "R";
                }
            }
            
            sb.append(who);
            if(who.equals("L")) left = num;
            else right = num;
        }
        
        return sb.toString();
    }
    
    public static int[] findIdx(int num, int[][] keypad) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                if(keypad[i][j] == num) return new int[] {i, j};
            }
        }
        
        return new int[] {-1, -1};
    }
}