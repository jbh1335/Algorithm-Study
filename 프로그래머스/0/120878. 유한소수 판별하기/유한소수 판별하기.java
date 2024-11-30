class Solution {
    public int solution(int a, int b) {
        int gcdNum = gcd(a, b);
        int deno = b / gcdNum;
        
        while(true) {
            if(deno % 2 == 0 || deno % 5 == 0) {
                if(deno % 2 == 0) deno /= 2;
                if(deno % 5 == 0) deno /= 5;
            } else {
                break;
            }
        }
         
        return deno == 1 ? 1 : 2;
    }
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}