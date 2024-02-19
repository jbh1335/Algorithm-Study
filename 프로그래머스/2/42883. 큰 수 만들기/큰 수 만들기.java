class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int start = 0;
        for(int i = k; i < number.length(); i++) {
            int max = 0;
            for(int j = start; j <= i; j++) {
                int num = number.charAt(j) - '0';
                if(num > max) {
                    max = num;
                    start = j+1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}