class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while(true) {
            if(s.equals("1")) break;
            
            int totalLength = s.length();
            s = s.replace("0", "");
            int noZeroLength = s.length();
            answer[1] += totalLength - noZeroLength;
            
            s = Integer.toBinaryString(noZeroLength);
            answer[0]++;
        }
        return answer;
    }
}