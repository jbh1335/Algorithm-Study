class Solution {
    public int solution(int[][] board) {
        int answer = board.length * board[0].length;
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 1) {
                    answer--;
                    for(int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(checkRange(nx, ny, board.length, board[0].length) && board[nx][ny] == 0) {
                            board[nx][ny] = -1;
                            answer--;
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    public static boolean checkRange(int nx, int ny, int x, int y) {
        if(nx >= 0 && ny >= 0 && nx < x && ny < y) return true;
        return false;
    }
}