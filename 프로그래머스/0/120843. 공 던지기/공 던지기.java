class Solution {
    public int solution(int[] numbers, int k) {
        int answer = (k - 1) * 2 % numbers.length + 1;
        return answer;
    }
}