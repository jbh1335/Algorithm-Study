class Solution {
    public String solution(String my_string, int[] indices) {
        String answer = "";
        String[] sArr = my_string.split("");
        
        for(int idx : indices) {
            sArr[idx] = "";
        }
        
        for(String s : sArr) {
            answer += s;
        }
        
        return answer;
    }
}