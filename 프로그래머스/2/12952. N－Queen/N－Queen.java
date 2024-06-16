class Solution {
    static int answer;
    static int[] select;
    public int solution(int n) {
        // 인덱스를 행, 값을 열로 인식
        select = new int[n];
        dfs(0, n);
        return answer;
    }
    
    public static void dfs(int cnt, int n) {
        if(cnt == n) {
            answer++;
            return;
        }
        
        for(int i = 0; i < n; i++) {
            select[cnt] = i;
            if(checkOk(cnt)) {
                dfs(cnt+1, n);
            }
        }
    }
    
    public static boolean checkOk(int x) {
        for(int i = 0; i < x; i++) {
            // 가로, 세로 확인
            if(select[i] == select[x]) return false;
            // 대각선 확인 -> 두 지점의 행 차이와 열 차이가 같으면 같은 대각선임 (기울기)
            if(Math.abs(i-x) == Math.abs(select[i]-select[x])) return false;
        }
        return true;
    }
}