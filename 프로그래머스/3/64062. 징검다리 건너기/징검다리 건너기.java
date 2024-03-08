class Solution {
    // 건널 수 있는 친구 수를 이분탐색으로 찾기
    public int solution(int[] stones, int k) {
        int answer = 0;
        // 친구 수의 최소, 최대
        int start = 0, end = 200000000;
        
        while(start <= end) {
            int mid = (start+end)/2;
            
            if(checkCross(mid, k, stones)) {
                start = mid + 1;
                answer = mid;
            } else {
                end = mid - 1;
            }
        }
        return answer;
    }
    
    public static boolean checkCross(int friend, int k, int[] stones) {
        int count = 0;
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] < friend) {
                if(++count == k) return false;
            } else {
                count = 0;
            }
        }
        return true;
    }
}