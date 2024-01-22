class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] numArr = new boolean[10];
        
        for(int i : numbers) {
            numArr[i] = true;
        }
        
        for(int i = 0; i < 10; i++) {
            if(!numArr[i]) answer += i;
        }
        return answer;
    }
}