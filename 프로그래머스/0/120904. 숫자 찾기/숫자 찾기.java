class Solution {
    public int solution(int num, int k) {
        String sNum = String.valueOf(num);
        int answer = sNum.indexOf(String.valueOf(k));
        if(answer != -1) answer++;
        return answer;
    }
}