import java.util.*;
class Solution {
    static char[][] map;
    static boolean[][] visited;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            visited = new boolean[m][n];
            // 같은 모양의 2*2 찾기
            for(int i = 0; i < m-1; i++) {
                for(int j = 0; j < n-1; j++) {
                    char ch = map[i][j];
                    
                    if(ch == 'X') continue;
                    if(map[i][j+1] == ch && map[i+1][j] == ch && map[i+1][j+1] == ch) {
                        visited[i][j] = visited[i][j+1] = visited[i+1][j] = visited[i+1][j+1] = true;
                    }
                }
            }
            
            // 지워진 블록 수
            int count = deleteBlock(m, n);
            if(count == 0) break;
            answer += count;
            
            // 지워진 블록은 아래로 떨어짐
            fallBlock(m, n);
        }
        
        return answer;
    }
    
    public static void fallBlock(int m, int n) {
        Queue<Character> que = new LinkedList<>();
        
        for(int j = 0; j < n; j++) {
            for(int i = m-1; i >= 0; i--) {
                if(!visited[i][j]) {
                    que.offer(map[i][j]);
                }
            }
            
            for(int i = m-1; i >= 0; i--) {
                if(!que.isEmpty()) map[i][j] = que.poll();
                else map[i][j] = 'X';
            }
        }
    }
    
    public static int deleteBlock(int m, int n) {
        int count = 0;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j]) count++;
            }
        }
        
        return count;
    }
}