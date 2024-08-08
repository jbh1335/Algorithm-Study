class Solution {
    public String[] solution(String my_str, int n) {
        int length = my_str.length() % n == 0 ? my_str.length() / n : my_str.length() / n + 1;
        String[] answer = new String[length];
        
        int idx = 0;
        for(int i = 0; i < my_str.length(); i+=n) {
            int end = i + n;
            if(end > my_str.length()) end = my_str.length();
            
            answer[idx++] = my_str.substring(i, end);
        }
        
        return answer;
    }
}