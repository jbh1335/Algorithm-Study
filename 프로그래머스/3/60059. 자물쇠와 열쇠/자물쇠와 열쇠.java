import java.util.*;
class Solution {
    static int N, M, totalLock;
    static int[][] map;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        // 열쇠는 회전과 이동이 가능
        // 열쇠의 돌기(1) 부분을 자물쇠의 홈(0) 부분에 맞으면 열림
        // 열쇠의 돌기(1)와 자물쇠의 돌기(1)가 만나서는 안됨
        // 영역 벗어나도 괜찮으나, 자물쇠의 모든 홈(0)을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있음
        
        // 격자판을 늘려서 키를 회전시킨(총 3번) 후 한칸씩 이동하여 조건에 맞는지 확인
        N = lock.length + (key.length-1) * 2;
        M = lock.length + (key[0].length-1) * 2;
        map = new int[N][M];
        
        // 중간에 자물쇠 저장
        int x = 0, y = 0;
        for(int i = key.length-1; i <= N-key.length; i++) {
            for(int j = key[0].length-1; j <= M-key[0].length; j++) {
                map[i][j] = lock[x][y++];
                // 자물쇠의 홈을 10으로 변경
                if(map[i][j] == 0) {
                    map[i][j] = 10;
                    totalLock++;
                }
            }
            x++;
            y = 0;
        }
        
        // 4번 회전 (자기 자신 포함)
        for(int i = 0; i < 4; i++) {
            key = rotate(key);
            if(move(key)) {
                answer = true;
                break;
            }
        }
        return answer;
    }
    
    public static boolean move(int[][] key) {
        for(int i = 0; i <= N-key.length; i++) {
            for(int j = 0; j <= M-key[0].length; j++) {
                int x = 0, y = 0, count = 0;
                // 열쇠로 자물쇠 열 수 있는지 확인
                loop:
                for(int a = i; a < i+key.length; a++) {
                    for(int b = j; b < j+key[0].length; b++) {
                        // 돌기끼리 만나면 안됨
                        if(key[x][y] == 1 && map[a][b] == 1) {
                            count = 0;
                            break loop;
                        }
                        
                        // 잘 열림
                        if(key[x][y] == 1 && map[a][b] == 10) count++;
                        y++;
                    }
                    x++;
                    y = 0;
                }
                // 다 열었으면 통과
                if(count == totalLock) return true;
            }
        }
        return false;
    }
    
    // 시계방향 90도 회전
    public static int[][] rotate(int[][] key) {
        int[][] newKey = new int[key.length][key[0].length];
        int x = 0, y = 0;
        for(int j = 0; j < key[0].length; j++) {
            for(int i = key.length-1; i >= 0; i--) {
                newKey[x][y++] = key[i][j];
            }
            x++;
            y = 0;
        }
        
        return newKey;
    }
}