class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long dist = (long) d * d;
        for(int i = 0; i <= d; i+=k) {
            answer += findNum(i, k, dist);
        }
        return answer;
    }
    
    public static int findNum(int n, int k, long dist) {
        long num = (long) n * n;
        long minus = dist - num;
        int sqrt = (int) Math.sqrt(minus);
        return sqrt/k + 1;
    }
}