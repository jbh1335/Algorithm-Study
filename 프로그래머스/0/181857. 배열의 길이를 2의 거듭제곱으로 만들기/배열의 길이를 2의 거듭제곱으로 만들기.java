class Solution {
    public int[] solution(int[] arr) {
        int num = 1;
        while(num < arr.length) {
            num *= 2;
        }
        
        int[] answer = new int[num];
        for(int i = 0; i < arr.length; i++) {
            answer[i] = arr[i];
        }
        
        return answer;
    }
}