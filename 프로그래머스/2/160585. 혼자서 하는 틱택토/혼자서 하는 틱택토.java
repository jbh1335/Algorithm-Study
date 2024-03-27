class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int o = 0, x = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                char ch = board[i].charAt(j);
                if(ch == 'O') o++;
                else if(ch == 'X') x++;
            }
        }
        // 후공이 더 많을 수는 없음
        if(o < x) answer = 0;
        else {
            // 선공과 후공 차이가 2개 이상이면 불가능
            if(o-x >= 2) answer = 0;
            // 개수가 같은데 선공이 이겼을 경우 불가능
            if(o == x) {
                if(findWin('O', board)) answer = 0;
            } else { // 후공이 이기면 무조건 개수가 같아야하는데 다르면 불가능
                if(findWin('X', board)) answer = 0;
            }
        }
        return answer;
    }
    
    public static boolean findWin(char target, String[] board) {
        for(int i = 0; i < 3; i++) {
            // 가로
            boolean win = true;
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) != target) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
            
            // 세로
            win = true;
            for(int j = 0; j < 3; j++) {
                if(board[j].charAt(i) != target) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }
        
        // 대각선
        if(board[0].charAt(0) == target &&
           board[1].charAt(1) == target &&
           board[2].charAt(2) == target) return true;
        if(board[0].charAt(2) == target &&
           board[1].charAt(1) == target &&
           board[2].charAt(0) == target) return true;
        return false;
    }
}