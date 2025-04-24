class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            int num = storey % 10;
            storey /= 10;
            
            if(num == 5) {
                if(storey % 10 >= 5) {
                    answer += 10 - num;
                    storey++;
                } else {
                    answer += num;
                }
            } else if(num > 5) {
                answer += 10 - num;
                storey++;
            } else {
                answer += num;
            }
        }
        
        return answer;
    }
}