class Solution {
    public int solution(int n) {
        int firstCnt = countOneNum(Integer.toBinaryString(n));
        
        while(true) {
            int cnt = countOneNum(Integer.toBinaryString(++n));
            if(firstCnt == cnt) break;
        }
        
        return n;
    }
    
    public static int countOneNum(String sNum) {
        int count = 0;
        
        for(int i = 0; i < sNum.length(); i++) {
            if(sNum.charAt(i) == '1') count++;
        }
        
        return count;
    }
}