class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int[][] dist = new int[land.length][4];
        for(int j = 0; j < 4; j++) {
            dist[0][j] = land[0][j];
            answer = Math.max(answer, dist[0][j]);
        }
        
        for(int i = 1; i < land.length; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    if(j == k) continue;
                    dist[i][j] = Math.max(dist[i][j], land[i][j]+dist[i-1][k]);
                    answer = Math.max(answer, dist[i][j]);
                }
            }
        }

        return answer;
    }
}