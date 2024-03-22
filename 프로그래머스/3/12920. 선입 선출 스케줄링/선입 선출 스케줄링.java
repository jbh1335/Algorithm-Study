class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        if(n <= cores.length) return n;
        
        int start = 0, end = cores.length*10000;
        int time = 0, work = 0; // time 시간동안 work개 코어 사용함
        
        while(start <= end) {
            // 이분탐색으로 작업 시간 찾기
            int mid = (start + end) / 2;
            // mid 시간까지 사용한 코어 수
            int count = countWork(mid, cores);
            
            if(count >= n) {
                end = mid - 1;
                time = mid;
                work = count;
            } else {
                start = mid + 1;
            }
        }
        
        // n번째 코어를 찾아야하므로 총 사용한 코어 수에서 빼줌 -> 초과한 코어 수
        work -= n;
        for(int i = cores.length-1; i >= 0; i--) {
            // 마지막 시간(time)에 들어간 코어라면
            if(time % cores[i] == 0) {
                // n번째 코어라면
                if(work == 0) {
                    answer = i + 1;
                    break;
                }
                // 초과한 코어 -1
                work--;
            }
        }
        return answer;
    }
    
    public static int countWork(int time, int[] cores) {
        // 시간이 0일 때 작업 시작한 코어
        int count = cores.length;
        // time까지 몇개의 코어를 사용했는지
        for(int i = 0; i < cores.length; i++) {
            count += (time / cores[i]);
        }
        return count;
    }
}