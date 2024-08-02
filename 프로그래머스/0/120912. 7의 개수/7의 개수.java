class Solution {
    public int solution(int[] array) {
        String answer = "";
        
        for(int num : array) {
            String str = String.valueOf(num).replaceAll("[^7]", "");
            answer += str;
        }
        
        return answer.length();
    }
}