class Solution {
    static int answer;
    static char[] kakao = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited;
    public int solution(int n, String[] data) {
        answer = 0;
        visited = new boolean[8];
        per(0, "", data);
        return answer;
    }
    
    public static void per(int cnt, String str, String[] data) {
        if(cnt == 8) {
            if(checkOk(str, data)) answer++;
            return;
        }
        
        for(int i = 0; i < kakao.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            per(cnt+1, str+kakao[i], data);
            visited[i] = false;
        }
    }
    
    public static boolean checkOk(String str, String[] data) {
        for(String s : data) {
            int idx1 = str.indexOf(s.charAt(0));
            int idx2 = str.indexOf(s.charAt(2));
            int between = Math.abs(idx1-idx2) - 1;
            char op = s.charAt(3);
            int num = s.charAt(4) - '0';
            
            if(op == '=') { // 같음
                if(between != num) return false;
            } else if(op == '<') { // 미만
                if(between >= num) return false;
            } else { // 초과
                if(between <= num) return false;
            }
        }
        return true;
    }
}