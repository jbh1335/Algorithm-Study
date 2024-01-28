class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int count = index;
            
            while(count > 0) {
                ch = ch == 'z' ? 'a' : ++ch;
                if(skip.indexOf(ch) == -1) count--;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}