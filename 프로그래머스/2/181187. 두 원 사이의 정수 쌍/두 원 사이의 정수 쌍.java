class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long r2Count = countDot(r2);
        r2Count += r2*2;
        r2Count += r2*2+1;
        
        long r1Count = countDot(r1);
        r1Count += r1*2;
        r1Count += r1*2+1;
        
        answer = r2Count - r1Count + surface(r1);
        return answer;
    }
    
    public static long countDot(int r) {
        long count = 0, num = (long)r * r;
        for(long i = 1; i <= r; i++) {
            count += (long) Math.sqrt(num - i*i);
        }
        
        return count*4;
    }
    
    public static long surface(int r) {
        long count = 0, num = (long)r * r;
        for(long i = 1; i <= r; i++) {
            long sqrt = (long) Math.sqrt(num - i*i);
            if(sqrt == Math.sqrt(num - i*i)) count++;
        }
        return count*4;
    }
}