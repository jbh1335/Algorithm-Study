import java.util.*;
class Solution {
    public int solution(int[] sides) {
        int answer = 0;
        Arrays.sort(sides);
        
        // sides[1]이 가장 긴 변일 경우
        answer += sides[1] - (sides[1] - sides[0]);
        
        // sides[0]과 sides[1]을 제외한 변이 가장 길 경우
        // sides[1]과 같은 길이는 위에서 포함했으므로 -1
        answer += (sides[0] + sides[1]) - sides[1] - 1;
        
        return answer;
    }
}