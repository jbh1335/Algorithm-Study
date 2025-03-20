class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "", str = "";
        int num = 0;
        
        while(str.length() < t*m) {
            str += Integer.toString(num++, n);
        }
        
        for(int i = p-1; i < str.length(); i+=m) {
            if(t-- == 0) break;
            answer += str.charAt(i);
        }
        
        return answer.toUpperCase();
    }
}