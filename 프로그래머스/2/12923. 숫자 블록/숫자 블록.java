class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin)+1];
        int idx = 0;
        
        for(int i = (int)begin; i <= (int)end; i++) {
            answer[idx++] = find(i);
        }
        return answer;
    }
    
    public static int find(int num) {
        if(num == 1) return 0;
        int sqrt = (int) Math.sqrt(num);
        int max = 1;
        
        for(int i = sqrt; i >= 2; i--) {
            if(num % i == 0) {
                max = Math.max(max, i);
                if(num/i <= 10000000) max = Math.max(max, num/i);
            }
        }
        return max;
    }
}