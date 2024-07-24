class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String[] splitArr = my_string.split("[a-z|A-Z]+");
        
        for(String str : splitArr) {
            if(str.equals("")) continue;
            answer += Integer.parseInt(str);
        }
        
        return answer;
    }
}