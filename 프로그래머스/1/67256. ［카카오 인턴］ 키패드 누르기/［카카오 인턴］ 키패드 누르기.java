class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int leftX = 3, leftY = 0;
        int rightX = 3, rightY = 2;
        int[][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2}};
        
        for(int i = 0; i < numbers.length; i++) {
            String who = "";
            int num = numbers[i];
            int[] arr = findIndex(num, keypad);
            
            if(num == 1 || num == 4 || num == 7) {
                who = "left";
            } else if(num == 3 || num == 6 || num == 9) {
                who = "right";
            } else {
                int leftDist = Math.abs(leftX - arr[0]) + Math.abs(leftY - arr[1]);
                int rightDist = Math.abs(rightX - arr[0]) + Math.abs(rightY - arr[1]);
                
                if(leftDist > rightDist) {
                    who = "right";
                } else if(leftDist < rightDist) {
                    who = "left";
                } else {
                    who = hand.equals("left") ? "left" : "right";
                }
            }
            
            if(who.equals("left")) {
                leftX = arr[0];
                leftY = arr[1];
                sb.append("L");
            } else {
                rightX = arr[0];
                rightY = arr[1];
                sb.append("R");
            }
        }
        return sb.toString();
    }
    
    public static int[] findIndex(int num, int[][] keypad) {
        int[] arr = new int[2];
        for(int i = 0; i < keypad.length; i++) {
            for(int j = 0; j < keypad[0].length; j++) {
                if(keypad[i][j] == num) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
    }
}