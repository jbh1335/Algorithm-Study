class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            int left = storey % 10;
            if(left == 5) {
                // 5는 그 앞의 숫자에 따라 +할지 -할지 결정
                // 앞의 숫자가 5이상이면 올리는게 더 빠름
                if(storey / 10 % 10 >= 5) storey += 10;
                answer += 5;
            } else if(left > 5) {
                storey += 10;
                answer += 10 - left;
            } else {
                answer += left;
            }
            storey /= 10;
        }
        return answer;
    }
}