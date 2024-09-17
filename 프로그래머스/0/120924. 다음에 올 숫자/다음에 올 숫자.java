class Solution {
    public int solution(int[] common) {
        int diff = common[1] - common[0];
        int answer = diff == common[2]-common[1] ? common[common.length-1] + diff : common[common.length-1] * (common[1] / common[0]);
        
        return answer;
    }
}