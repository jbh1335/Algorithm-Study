class Solution {
    /*
        type r1, c1, r2, c2, degree
        type: 1은 적의 공격, 2는 아군의 회복
        r1 c1부터 r2 c2까지 직사각형 모양 범위가 degree만큼 낮추거나 높임
        누적합으로 계산 (그냥은 시간초과)
        가로로 누적하면
        n 0 0 -n
        n 0 0 -n
        n 0 0 -n
        
        이걸 세로로 누적하면
        n 0 0 -n
        0 0 0 0
        0 0 0 0
        -n 0 0 n
    */
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        // 누적합을 위한 배열
        int[][] sArr = new int[board.length+1][board[0].length+1];
        for(int i = 0; i < skill.length; i++) {
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][0] == 1 ? -skill[i][5] : skill[i][5];
            
            sArr[r1][c1] += degree;
            sArr[r1][c2+1] += -degree;
            sArr[r2+1][c1] += -degree;
            sArr[r2+1][c2+1] += degree;
        }
        
        // 누적합 계산 -> 가로
        for(int i = 0; i < sArr.length; i++) {
            int sum = 0;
            for(int j = 0; j < sArr[0].length; j++) {
                sArr[i][j] += sum;
                sum = sArr[i][j];
            }
        }
        
        // 누적합 계산 -> 세로
        for(int j = 0; j < sArr[0].length; j++) {
            int sum = 0;
            for(int i = 0; i < sArr.length; i++) {
                sArr[i][j] += sum;
                sum = sArr[i][j];
            }
        }
        
        // 누적합을 board에 적용 -> 둘이 합치기
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] += sArr[i][j];
                // 파괴되지 않은 건물
                if(board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}