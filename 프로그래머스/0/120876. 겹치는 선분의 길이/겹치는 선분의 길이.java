class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        int[] visited = new int[201];
        
        for(int[] arr : lines) {
            for(int i = arr[0]+1; i <= arr[1]; i++) {
                visited[i+100]++;
            }
        }
        
        for(int i = 1; i <= 200; i++) {
            if(visited[i] >= 2) answer++;
        }
        
        return answer;
    }
}