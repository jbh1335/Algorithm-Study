class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int[][] dp = new int[board.length][board[0].length];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = board[i][j];
                    answer = Math.max(answer, dp[i][j]);
                    continue;
                }
                
                // 1이면 정사각형 가능한지 확인
                if(board[i][j] == 1) {
                    int up = dp[i-1][j]; // 위
                    int left = dp[i][j-1]; // 왼쪽
                    int cross = dp[i-1][j-1]; // 왼쪽위 대각선
                    
                    // 3개의 값중 최솟값 +1
                    dp[i][j] = Math.min(Math.min(up, left), cross) + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        return answer*answer;
    }
}