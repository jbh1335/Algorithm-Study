class Solution {
    public String solution(String s) {   
        String[] splitArr = s.split(" ");
        
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int i = 0; i < splitArr.length; i++) {
            min = Math.min(min, Integer.parseInt(splitArr[i]));
            max = Math.max(max, Integer.parseInt(splitArr[i]));
        }
        
        String answer = min + " " + max;
        return answer;
    }
}