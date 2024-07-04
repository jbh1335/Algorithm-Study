class Solution {
    public int solution(int n) {
        int answer = lcm(n, 6) / 6;
        return answer;
    }
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
    public static int lcm(int a, int b) {
        return (a*b) / gcd(a, b);
    }
}