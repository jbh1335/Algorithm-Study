class Solution {
    static int[][] arr;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        arr = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                arr[i][j] = num++;
            }
        }
        
        for(int i = 0; i < queries.length; i++) {
            int[] quer = queries[i];
            answer[i] = rotate(quer[0]-1, quer[1]-1, quer[2]-1, quer[3]-1);
        }
        return answer;
    }
    
    public static int rotate(int x1, int y1, int x2, int y2) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = x1, y = y1;
        int firstNum = arr[x1][y1];
        int min = firstNum;
        
        for(int d = 0; d < 4; d++) {
            while(true) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx >= x1 && ny >= y1 && nx <= x2 && ny <= y2) {
                    arr[x][y] = arr[nx][ny];
                    min = Math.min(min, arr[x][y]);
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
            }
        }
        
        arr[x1][y1+1] = firstNum;
        return min;
    }
}