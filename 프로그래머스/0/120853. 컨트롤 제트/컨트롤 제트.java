class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] splitArr = s.split(" ");
        
        for(int i = 0; i < splitArr.length; i++) {
            if(splitArr[i].equals("Z")) answer -= Integer.parseInt(splitArr[i-1]);
            else answer += Integer.parseInt(splitArr[i]);
        }
        
        return answer;
    }
}