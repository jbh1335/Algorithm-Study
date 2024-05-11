class Solution {
    public String solution(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        
        int idx = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ' ') {
                sb.append(" ");
                idx = 0;
                continue;
            }
            
            if(idx++ % 2 == 0) sb.append(String.valueOf(ch).toUpperCase());
            else sb.append(ch);
        }
        return sb.toString();
    }
}