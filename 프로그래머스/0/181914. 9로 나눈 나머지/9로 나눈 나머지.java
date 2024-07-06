class Solution {
    public int solution(String number) {
        int num = 0;
        for(int i = 0; i < number.length(); i++) {
            num += number.charAt(i) - '0';
        }
        
        int answer = num % 9;
        return answer;
    }
}