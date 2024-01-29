import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < moves.length; i++) {
            int pickNum = playGame(moves[i]-1, board);
            if(pickNum != -1) {
                if(!stack.isEmpty()) {
                    if(stack.peek() == pickNum) {
                        answer += 2;
                        stack.pop();
                    } else {
                        stack.push(pickNum);
                    }
                } else {
                    stack.push(pickNum);
                }
            }
        }
        return answer;
    }
    
    public static int playGame(int num, int[][] board) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][num] != 0) {
                int pickNum = board[i][num];
                board[i][num] = 0;
                return pickNum;
            }
        }
        return -1;
    }
}