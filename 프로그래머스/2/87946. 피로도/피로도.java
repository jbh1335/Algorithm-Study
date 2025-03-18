class Solution {
    static int answer;
    static int[] select;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        select = new int[dungeons.length];
        visited = new boolean[select.length];
        per(0, k, dungeons);
        return answer;
    }
    
    public static void per(int cnt, int k, int[][] dungeons) {
        if(cnt == dungeons.length) {
            answer = Math.max(answer, playDungeon(k, dungeons));
            return;
        }
        
        for(int i = 0; i < select.length; i++) {
            if(visited[i]) continue;
            
            select[cnt] = i;
            visited[i] = true;
            per(cnt+1, k, dungeons);
            visited[i] = false;
        }
    }
    
    public static int playDungeon(int k, int[][] dungeons) {
        for(int i = 0; i < select.length; i++) {
            if(k >= dungeons[select[i]][0]) k -= dungeons[select[i]][1];
            else return i;
        }
        
        return select.length;
    }
}