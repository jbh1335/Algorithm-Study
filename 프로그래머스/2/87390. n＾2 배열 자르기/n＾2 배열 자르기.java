class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        long num = left;
        
        for(int i = 0; i < answer.length; i++) {
            answer[i] = Math.max((int) (num / n), (int) (num % n)) + 1;
            num++;
        }
        
        return answer;
    }
}