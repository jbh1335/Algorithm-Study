class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int denom = lcm(denom1, denom2);
        int numer = (denom / denom1) * numer1 + (denom / denom2) * numer2;
        
        int num = gcd(numer, denom);
        int[] answer = new int[] {numer/num, denom/num};
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
