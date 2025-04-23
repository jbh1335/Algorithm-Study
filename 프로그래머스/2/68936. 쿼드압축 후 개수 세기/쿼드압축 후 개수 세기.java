class Solution {
    static boolean[][] visited;
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        int N = arr.length;
        visited = new boolean[N][N];
        
        while(N >= 1) {
            for(int i = 0; i < arr.length; i+=N) {
                for(int j = 0; j < arr.length; j+=N) {
                    if(visited[i][j]) continue;
                    // 해당 영역이 같은 숫자이면 압축
                    if(checkSame(i, j, N, arr)) {
                        compress(i, j, N, arr);
                        answer[arr[i][j]]++;
                    }
                }
            }
            
            N /= 2;
        }
        
        return answer;
    }
    
    public static void compress(int x, int y, int N, int[][] arr) {
        for(int i = x; i < x+N; i++) {
            for(int j = y; j < y+N; j++) {
                visited[i][j] = true;
            }
        }
    }
    
    public static boolean checkSame(int x, int y, int N, int[][] arr) {
        boolean isAble = true;
        int target = arr[x][y];
        
        for(int i = x; i < x+N; i++) {
            for(int j = y; j < y+N; j++) {
                if(arr[i][j] != target) {
                    isAble = false;
                    break;
                }
            }
        }
        
        return isAble;
    }
}