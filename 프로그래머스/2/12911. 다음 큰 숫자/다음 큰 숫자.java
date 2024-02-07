class Solution {
    public int solution(int n) {
        int answer = 0;
        int length = makeBinary(n);
        
        while(true) {
            if(makeBinary(++n) == length) {
                answer = n;
                break;
            }
        }
        return answer;
    }
    
    public static int makeBinary(int num) {
        String sNum = Integer.toBinaryString(num);
        sNum = sNum.replace("0", "");
        return sNum.length();
    }
}