class Solution {
    public int solution(String A, String B) {
        int answer = B.repeat(2).indexOf(A);
        return answer;
    }
}