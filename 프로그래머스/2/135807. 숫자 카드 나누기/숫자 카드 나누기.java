class Solution {
    static int answer;
    public int solution(int[] arrayA, int[] arrayB) {
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
        
        // gcdA로 조건 탐색
        divisor(gcdA, arrayB);
        
        // gcdB로 조건 탐색
        divisor(gcdB, arrayA);
        return answer;
    }
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
    // 약수 중에 조건에 맞는 수 찾기
    public static void divisor(int num, int[] arr) {
        // 최대공약수가 조건에 맞으면 그 약수들은 확인하지 않아도 되므로 리턴
        if(!checkDivided(num, arr)) {
            answer = Math.max(answer, num);
            return;
        }
        
        int sqrt = (int) Math.sqrt(num);       
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) {
                if(!checkDivided(i, arr)) answer = Math.max(answer, i);
                if(!checkDivided(num/i, arr)) answer = Math.max(answer, num/i);
            }
        }
    }
    
    // 해당 배열에서 나누어 떨어지는 수가 있는지 확인
    public static boolean checkDivided(int num, int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] % num == 0) return true;
        }
        return false;
    }
}