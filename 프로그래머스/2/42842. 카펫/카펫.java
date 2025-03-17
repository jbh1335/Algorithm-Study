class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sqrt = (int) Math.sqrt(yellow);
        
        for(int i = 1; i <= sqrt; i++) {
            if(yellow % i == 0) {
                int width = yellow / i, height = i;
                
                if(width * 2 + height * 2 + 4 == brown) {
                    answer[0] = width + 2;
                    answer[1] = height + 2;
                    break;
                }
            }
        }
        
        return answer;
    }
}