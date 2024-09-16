class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i = 0; i < quiz.length; i++) {
            String[] splitArr = quiz[i].split(" \\+ | - | = ");
            int num1 = Integer.parseInt(splitArr[0]);
            int num2 = Integer.parseInt(splitArr[1]);
            int result = Integer.parseInt(splitArr[2]);
            
            if(quiz[i].contains(" + ")) {
                answer[i] = num1 + num2 == result ? "O" : "X";
            } else {
                answer[i] = num1 - num2 == result ? "O" : "X";
            }
        }
        
        return answer;
    }
}