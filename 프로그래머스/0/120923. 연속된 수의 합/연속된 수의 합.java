class Solution {
    public int[] solution(int num, int total) {
        int sum = 0;
        for(int i = 1; i <= num; i++) {
            sum += i;
        }
        
        int start = 1, end = num;
        while(sum != total) {
            if(sum < total) {
                sum -= start++;
                sum += ++end;
            } else {
                sum -= end--;
                sum += --start;
            }
        }
        
        int[] answer = new int[num];
        int idx = 0;
        for(int i = start; i <= end; i++) {
            answer[idx++] = i;
        }
        
        return answer;
    }
}