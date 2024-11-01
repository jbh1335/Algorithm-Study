class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0, start = 0, dist = 0, length = w * 2 + 1;
        
        for(int i = 0; i < stations.length; i++) {
            int end = stations[i] - w - 1;
            
            if(end > 0) {
                dist = end - start + 1;
                if(dist > 0) answer += dist % length == 0 ? dist / length : dist / length + 1;
            }
            
            start = stations[i] + w + 1;
        }
        
        if(start <= n) {
            dist = n - start + 1;
            answer += dist % length == 0 ? dist / length : dist / length + 1;
        }
        
        return answer;
    }
}