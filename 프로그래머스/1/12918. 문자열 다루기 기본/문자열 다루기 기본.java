class Solution {
    public boolean solution(String s) {
        boolean answer = false;
        
        if(s.length() == 4 || s.length() == 6) {
            String str = s.replaceAll("[0-9]", "");
            if(str.equals("")) answer = true;
        }
        
        return answer;
    }
}