class Solution {
    public String solution(int[] food) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < food.length; i++) {
            int num = food[i] / 2;
            while(num-- > 0) {
                sb.append(i);
            }
        }
        
        answer = sb.toString() + "0" + sb.reverse();
        return answer;
    }
}