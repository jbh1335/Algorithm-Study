class Solution {
    static int zero, one;
    public int[] solution(int[][] arr) {
        divide(0, 0, arr.length, arr);
        int[] answer = {zero, one};
        return answer;
    }
    
    public static void divide(int startX, int startY, int size, int[][] arr) {
        boolean isOk = true;
        int num = arr[startX][startY];
        // 같은 수로 이루어졌는지 검사
        loop:
        for(int i = startX; i < startX+size; i++) {
            for(int j = startY; j < startY+size; j++) {
                if(arr[i][j] != num) {
                    isOk = false;
                    break loop;
                }
            }
        }
        
        if(isOk) { // 같은 수
            if(num == 0) zero++;
            else one++;
        } else { // 아니면 4개로 분할
            divide(startX, startY, size/2, arr);
            divide(startX+size/2, startY, size/2, arr);
            divide(startX, startY+size/2, size/2, arr);
            divide(startX+size/2, startY+size/2, size/2, arr);
        }
    }
}