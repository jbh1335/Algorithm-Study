import java.util.*;
class Solution {
    static int count;
    static boolean[][] pillars, beams;
    public int[][] solution(int n, int[][] build_frame) {
        pillars = new boolean[n+1][n+1]; // 기둥
        beams = new boolean[n+1][n+1]; // 보
        
        for(int[] build : build_frame) {
            create(build[0], build[1], build[2], build[3]);
        }
        
        int[][] answer = new int[count][3];
        int idx = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(pillars[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx][2] = 0;
                    idx++;
                }
                if(beams[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx][2] = 1;
                    idx++;
                }
            }
        }
        return answer;
    }
    
    public static void create(int x, int y, int type, int action) {    
        if(action == 0) { // 삭제
            // 일단 삭제하고
            if(type == 0) pillars[x][y] = false;
            else beams[x][y] = false;
            
            // 삭제 가능한지 검사
            if(checkDelete(x, y)) count--;
            else {
                // 못하면 삭제한거 되돌리기
                if(type == 0) pillars[x][y] = true;
                else beams[x][y] = true;
            }
        } else { // 설치
            if(type == 0 && checkPillars(x, y)) { // 기둥
                pillars[x][y] = true;
                count++;
            } else if(type == 1 && checkBeams(x, y)) { // 보
                beams[x][y] = true;
                count++;
            }
        }
    }
    
    // 삭제할 수 있는지
    public static boolean checkDelete(int x, int y) {
        for(int i = 0; i < pillars.length; i++) {
            for(int j = 0; j < pillars[0].length; j++) {
                if(pillars[i][j] && !checkPillars(i, j)) return false;
                if(beams[i][j] && !checkBeams(i, j)) return false;
            }
        }
        return true;
    }
    
    // 기둥 검사
    public static boolean checkPillars(int x, int y) {
        // 바닥에 있거나
        if(y == 0) return true;
        // 보의 오른쪽 끝에 있거나
        if(x >= 1 && beams[x-1][y]) return true;
        // 보의 왼쪽 끝에 있거나
        if(beams[x][y]) return true;
        // 다른 기둥 위에 있어야 함
        if(y >= 1 && pillars[x][y-1]) return true;
        return false;
    }
    
    // 보 검사
    public static boolean checkBeams(int x, int y) {
        // 왼쪽 끝 부분이 기둥이거나
        if(pillars[x][y-1]) return true;
        // 오른쪽 끝 부분이 기둥이거나
        if(x < beams.length-1 && pillars[x+1][y-1]) return true;
        // 양쪽 끝이 다른 보와 연결되어야 함
        if(1 <= x && x < beams.length-1 && beams[x-1][y] && beams[x+1][y]) return true;
        return false;
    }
}