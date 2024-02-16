import java.util.*;
class Solution {
    static char[][] map;
    static boolean[][] visited;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        // board를 이차원 배열 map으로 만들기
        map = new char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            visited = new boolean[m][n];
            boolean flag = false;
            // 2*2모양으로 같은 애들이 붙어있는지 찾기
            for(int i = 0; i < m-1; i++) {
                for(int j = 0; j < n-1; j++) {
                    char ch = map[i][j];
                    if(ch == '.') continue;
                    if(ch == map[i+1][j] && ch == map[i][j+1] && ch == map[i+1][j+1]) {
                        visited[i][j] = visited[i+1][j] = visited[i][j+1] = visited[i+1][j+1] = true;
                        flag = true;
                    }
                }
            }
            
            // 더이상 찾을 것이 없으면 끝내기
            if(!flag) break;
            // 다 찾고 나서 삭제된 애들은 개수 구하고
            answer += countDeleted();
            // 위에서부터 내리기
            drop();
        }
        
        return answer;
    }
    
    // 삭제된 곳에 위에서부터 떨어뜨리기
    public static void drop() {
        for(int j = 0; j < map[0].length; j++) {
            for(int i = map.length-1; i >= 0; i--) {
                if(visited[i][j]) {
                    int idx = i;
                    // 위로 방문한 적 없는 가장 가까운 아이 찾기
                    while(--idx >= 0) {
                        if(map[idx][j] != '.') {
                            // 빈곳으로 내려옴
                            map[i][j] = map[idx][j];
                            // 원래 있던 곳은 빈곳으로 변경
                            map[idx][j] = '.';
                            visited[idx][j] = true;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    // 삭제된 애들 개수 세주기
    public static int countDeleted() {
        int count = 0;
        for(int i = 0; i < visited.length; i++) {
            for(int j = 0; j < visited[0].length; j++) {
                if(visited[i][j]) {
                    count++;
                    // 빈칸으로 변경
                    map[i][j] = '.';
                }
            }
        }
        return count;
    }
}