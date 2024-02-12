class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    public static void dfs(int cnt, int num, int[] numbers, int target) {
        if(cnt == numbers.length) {
            if(num == target) answer++;
            return;
        }
        
        dfs(cnt+1, num+numbers[cnt], numbers, target);
        dfs(cnt+1, num-numbers[cnt], numbers, target);
    }
}