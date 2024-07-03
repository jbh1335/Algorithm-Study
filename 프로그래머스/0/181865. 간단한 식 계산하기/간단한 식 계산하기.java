class Solution {
    public int solution(String binomial) {
        int answer = 0;
        String[] splitArr = binomial.split(" ");
        int num1 = Integer.parseInt(splitArr[0]);
        int num2 = Integer.parseInt(splitArr[2]);
        
        if(splitArr[1].equals("+")) {
            answer = num1 + num2;
        } else if(splitArr[1].equals("-")) {
            answer = num1 - num2;
        } else {
            answer = num1 * num2;
        }
        return answer;
    }
}