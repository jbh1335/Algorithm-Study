class Solution {
    public int solution(String my_string) {
        String[] splitArr = my_string.split(" ");
        int answer = Integer.parseInt(splitArr[0]);
        
        for(int i = 2; i < splitArr.length; i+=2) {
            int num = Integer.parseInt(splitArr[i]);
            if(splitArr[i-1].equals("+")) answer += num;
            else answer -= num;
        }
        
        return answer;
    }
}