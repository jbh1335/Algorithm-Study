class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        
        boolean isFirst = true;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ' ') {
                sb.append(" ");
                isFirst = true;
            } else {
                if(isFirst) {
                    ch = String.valueOf(ch).toUpperCase().charAt(0);
                    isFirst = false;
                }
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}