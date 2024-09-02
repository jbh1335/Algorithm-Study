class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        int x = board[0] / 2, y = board[1] / 2;
        
        for(String key : keyinput) {
            if(key.equals("left")) {
                if(answer[0] > -x) answer[0]--;
            } else if(key.equals("right")) {
                if(answer[0] < x) answer[0]++;
            } else if(key.equals("down")) {
                if(answer[1] > -y) answer[1]--;
            } else {
                if(answer[1] < y) answer[1]++;
            }
        }
        
        return answer;
    }
}