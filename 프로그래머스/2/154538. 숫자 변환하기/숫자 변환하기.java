class Solution {
    public int solution(int x, int y, int n) {
        int[] visited = new int[y+1];
        if(x == y) return 0;
        
        for(int i = x; i < y; i++) {
            if(i != x && visited[i] == 0) {
                continue;
            }
            
            if(i+n <= y) visited[i+n] = visited[i+n] == 0 ? visited[i] + 1 : Math.min(visited[i]+1, visited[i+n]);
            if(i*2 <= y) visited[i*2] = visited[i*2] == 0 ? visited[i] + 1 : Math.min(visited[i]+1, visited[i*2]);
            if(i*3 <= y) visited[i*3] = visited[i*3] == 0 ? visited[i] + 1 : Math.min(visited[i]+1, visited[i*3]);
        }
        
        int answer = visited[y] == 0 ? -1 : visited[y];
        return answer;
    }
}