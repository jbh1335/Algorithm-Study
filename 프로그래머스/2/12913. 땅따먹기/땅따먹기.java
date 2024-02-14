class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int[][] distance = new int[land.length][4];
        
        for(int j = 0; j < 4; j++) {
            distance[0][j] = land[0][j];
        }
        
        int idx = 1;
        while(true) {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    if(i == j) continue;
                    distance[idx][j] = Math.max(distance[idx][j], distance[idx-1][i] + land[idx][j]);
                    answer = Math.max(answer, distance[idx][j]);
                }
            }
            if(++idx == land.length) break;
        }
        
        return answer;
    }
}