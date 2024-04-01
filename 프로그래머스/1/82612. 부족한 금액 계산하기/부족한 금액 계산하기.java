class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        while(count > 0) {
            answer += price * count;
            count--;
        }
        if(answer > money) answer -= money;
        else answer = 0;
        return answer;
    }
}