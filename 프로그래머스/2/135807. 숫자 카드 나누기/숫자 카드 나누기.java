class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        // arrayA의 최대공약수
        int gcdA = arrayA[0];
        for(int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
        }
        
        // arrayB의 최대공약수
        int gcdB = arrayB[0];
        for(int i = 1; i < arrayB.length; i++) {
            gcdB = gcd(gcdB, arrayB[i]);
        }
        
        // gcdA가 arrayB에 나누어 떨어지는지 확인
        if(checkOk(gcdA, arrayB)) answer = Math.max(answer, gcdA);
        
        // gcdB가 arrayA에 나누어 떨어지는지 확인
        if(checkOk(gcdB, arrayA)) answer = Math.max(answer, gcdB);
        return answer;
    }
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
    // 해당 배열에서 나누어 떨어지는 수가 있는지 확인
    public static boolean checkOk(int num, int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] % num == 0) return false;
        }
        return true;
    }
}