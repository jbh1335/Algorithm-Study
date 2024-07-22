class Solution {
    public int solution(int n) {
        int answer = 0, fact = 1;
        
        while(fact <= n) {
            fact *= ++answer;
        }
        
        return answer - 1;
    }
}