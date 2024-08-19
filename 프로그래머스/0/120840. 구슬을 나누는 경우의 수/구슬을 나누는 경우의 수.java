class Solution {
    static int answer;
    public int solution(int balls, int share) {
        com(0, 1, balls, share);
        return answer;
    }
    
    public static void com(int cnt, int start, int balls, int share) {
        if(cnt == share) {
            answer++;
            return;
        }
        
        for(int i = start; i <= balls; i++) {
            com(cnt+1, i+1, balls, share);
        }
    }
}