class Solution {
    public String solution(int[] numLog) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < numLog.length; i++) {
            int num = numLog[i] - numLog[i-1];
            
            if(num == 1) sb.append("w");
            else if(num == -1) sb.append("s");
            else if(num == 10) sb.append("d");
            else sb.append("a");
        }
        
        return sb.toString();
    }
}