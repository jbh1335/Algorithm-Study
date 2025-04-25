import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        int gcdNumA = arrayA[0];
        for(int i = 1; i < arrayA.length; i++) {
            gcdNumA = gcd(gcdNumA, arrayA[i]);
        }
        
        int gcdNumB = arrayB[0];
        for(int i = 1; i < arrayB.length; i++) {
            gcdNumB = gcd(gcdNumB, arrayB[i]);
        }
        
        boolean isAble = true;
        for(int i : arrayA) {
            if(i % gcdNumB == 0) {
                isAble = false;
                break;
            }
        }
        
        if(isAble) answer = gcdNumB;
        
        isAble = true;
        for(int i : arrayB) {
            if(i % gcdNumA == 0) {
                isAble = false;
                break;
            }
        }
        
        if(isAble) answer = Math.max(answer, gcdNumA);
        
        return answer;
    }
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}