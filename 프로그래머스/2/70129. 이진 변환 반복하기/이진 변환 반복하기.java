class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while(!s.equals("1")) {
            int count = s.length();
            s = s.replace("0", "");
            answer[1] += count - s.length();
            s = Integer.toBinaryString(s.length());
            answer[0]++;
        }
        
        return answer;
    }
}