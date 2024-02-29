class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int gcd = gcd(w, h);
        // 사용할 수 없는 수
        int noUse = gcd * (w/gcd + h/gcd - 1);
        // 사용할 수 있는 수 = 전체 - 사용할 수 없는 수
        answer = (long) w * h - noUse;
        return answer;
    }
    
    public int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}