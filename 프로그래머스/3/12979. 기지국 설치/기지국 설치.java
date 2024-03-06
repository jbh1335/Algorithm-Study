class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int beforeStart = 1;
        for(int i = 0; i < stations.length; i++) {
            int start = stations[i] - w;
            
            if(beforeStart < start) {
                int num = start - beforeStart;
                answer += num / (2*w+1);
                if(num % (2*w+1) != 0) answer++;
            }
            
            beforeStart = stations[i] + w + 1;
        }
        
        if(beforeStart <= n) {
            int num = n - beforeStart + 1;
            answer += num / (2*w+1);
            if(num % (2*w+1) != 0) answer++;
        }
        return answer;
    }
}