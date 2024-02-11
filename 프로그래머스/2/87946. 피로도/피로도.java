class Solution {
    static int answer;
    static int[] select;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        select = new int[dungeons.length];
        visited = new boolean[dungeons.length];
        
        per(0, dungeons, k);
        return answer;
    }
    
    public static void per(int cnt, int[][] dungeons, int k) {
        if(cnt == dungeons.length) {
            int count = 0;
            for(int i = 0; i < cnt; i++) {
                if(k >= dungeons[select[i]][0]) {
                    k -= dungeons[select[i]][1];
                    count++;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, count);
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if(visited[i]) continue;
            select[cnt] = i;
            visited[i] = true;
            per(cnt+1, dungeons, k);
            visited[i] = false;
        }
    }
}