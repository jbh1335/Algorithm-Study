class Solution {
    static int answer;
    public int solution(int[] number) {
        com(0, 0, 0, number);
        return answer;
    }
    
    public static void com(int cnt, int start, int sum, int[] number) {
        if(cnt == 3) {
            if(sum == 0) answer++;
            return;
        }
        
        for(int i = start; i < number.length; i++) {
            com(cnt+1, i+1, sum+number[i], number);
        }
    }
}