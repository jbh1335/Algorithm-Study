class Solution {
    public String[] solution(String[] picture, int k) {
        String[] answer = new String[picture.length*k];
        int idx = 0;
        
        for(String pic : picture) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < pic.length(); i++) {
                String s = String.valueOf(pic.charAt(i));
                sb.append(s.repeat(k));
            }
            
            for(int i = 0; i < k; i++) {
                answer[idx++] = sb.toString();
            }
        }
        
        return answer;
    }
}