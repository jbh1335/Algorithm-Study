class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int num = 0;
        String strNum = "";
        while(true) {
            strNum += Integer.toString(num++, n).toUpperCase();
            if(strNum.length() >= t*m) break;
        }
        
        int idx = p-1;
        while(t-- > 0) {
            answer += strNum.charAt(idx);
            idx += m;
        }
        return answer;
    }
}