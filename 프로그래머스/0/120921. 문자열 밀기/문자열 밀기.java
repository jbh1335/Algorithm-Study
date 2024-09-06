class Solution {
    public int solution(String A, String B) {
        int answer = 0;
        if(A.equals(B)) return 0;
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < A.length(); i++) {
            sb.append(A.charAt(i));
        }
        
        for(int i = 0; i < A.length(); i++) {
            sb.insert(0, sb.charAt(A.length()-1));
            sb.deleteCharAt(A.length());
            answer++;
            
            if(sb.toString().equals(B)) break;
        }
        
        if(answer == A.length()) answer = -1;
        return answer;
    }
}