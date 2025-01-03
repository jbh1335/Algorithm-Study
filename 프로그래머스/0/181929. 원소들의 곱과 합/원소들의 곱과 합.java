class Solution {
    public int solution(int[] num_list) {
        int answer = 0, sum = 0, multi = 1;
        
        for(int num : num_list) {
            sum += num;
            multi *= num;
        }
        
        answer = multi < Math.pow(sum, 2) ? 1 : 0;
        return answer;
    }
}