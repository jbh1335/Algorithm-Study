class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || j == 0) map[i][j] = 1;
                else map[i][j] = -1;
            }
        }
        
        for(int[] puddle : puddles) {
            int x = puddle[1] - 1, y = puddle[0] - 1;
            map[x][y] = 0;
            
            if(x == 0) {
                for(int j = y+1; j < m; j++) {
                    map[0][j] = 0;
                }
            }
            
            if(y == 0) {
                for(int i = x+1; i < n; i++) {
                    map[i][0] = 0;
                }
            }
        }
        
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(map[i][j] == 0) continue;
                map[i][j] = (map[i-1][j] + map[i][j-1]) % 1000000007;
            }
        }
        
        return map[n-1][m-1];
    }
}