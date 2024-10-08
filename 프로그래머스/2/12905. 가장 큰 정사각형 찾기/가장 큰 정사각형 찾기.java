class Solution
{
    public int solution(int [][]board)
    {
        int answer = 1;
        int[][] map = new int[board.length][board[0].length];
        boolean flag = false;
        
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(board[i][j] == 1) flag = true;
                
                if(i == 0 || j == 0) {
                    map[i][j] = board[i][j];
                    continue;
                }
                
                if(board[i][j] == 1) {
                    map[i][j] = Math.min(map[i-1][j-1], Math.min(map[i][j-1], map[i-1][j])) + 1;
                    answer = Math.max(answer, map[i][j]*map[i][j]);
                }
            }
        }

        if(!flag) answer = 0;
        return answer;
    }
}