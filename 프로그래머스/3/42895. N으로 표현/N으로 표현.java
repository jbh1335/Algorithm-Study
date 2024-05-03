import java.util.*;
class Solution {
    public int solution(int N, int number) {
        // 8보다 크면 -1이므로 8번까지만 구하면 됨
        HashSet<Integer>[] set = new HashSet[9];
        for(int i = 0; i <= 8; i++) {
            set[i] = new HashSet<>();
        }
        
        // N을 1번부터 8번 사용한 경우의 수 구하기
        String strNum = "";
        for(int i = 1; i <= 8; i++) {    
            // 그냥 N을 붙인 수 저장
            strNum += N;
            if(Integer.parseInt(strNum) == number) return i;
            set[i].add(Integer.parseInt(strNum));
            
            // 1과 i-1, 2와 i-2, 3와 i-3... -> 둘씩 더하면 i가 나옴
            // 즉 두 수를 더해서 i가 나오는 경우를 사칙연산을 통해 추가하면 됨
            for(int j = 1; j < i; j++) {
                for(int a : set[j]) {
                    for(int b : set[i-j]) {
                        if((a+b) == number) return i;
                        set[i].add(a+b);

                        if((a-b) == number) return i;
                        set[i].add(a-b);

                        if((a*b) == number) return i;
                        set[i].add(a*b);

                        if(b == 0) continue;
                        if((a/b) == number) return i;
                        set[i].add(a/b);              
                    }
                }
            }
        }
        
        return -1;
    }
}