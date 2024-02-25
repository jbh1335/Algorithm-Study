import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        long fact = 1;
        ArrayList<Integer> list = new ArrayList<>();
        // 번호 순서대로 저장
        for(int i = 1; i <= n; i++) {
            list.add(i);
            fact *= i;
        }
        int cnt = 0;
        // 인덱스는 0부터 시작하므로 -1
        k--;
        
        // 0번째 자리부터 시작해서 찾기
        while(n > 0) {
            // 전체개수 / 숫자개수(배열)
            // 각 숫자가 앞자리로 올 때 만들 수 있는 경우의 수: fact개
            fact /= n;
            
            // 몇번째 요소인지 찾기
            int idx = (int) (k / fact);
            answer[cnt++] = list.get(idx);
            // 숫자를 사용했으니 제거
            list.remove(idx);
            
            // 하나의 숫자를 뽑았으니 -1
            n--;
            // 나머지가 그 다음으로 찾아야 하는 순서
            k %= fact;
        }
        return answer;
    }
}