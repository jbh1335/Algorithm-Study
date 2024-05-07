import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            map = new char[3][3];
            int idx = 0, xCnt = 0, oCnt = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    map[i][j] = str.charAt(idx++);
                    if(map[i][j] == 'X') xCnt++;
                    else if(map[i][j] == 'O') oCnt++;
                }
            }

            String answer = "valid";
            int diff = xCnt - oCnt;
            // X가 먼저 시작하므로 O보다 개수가 적을 수 없음
            // 번갈아가며 말을 놓기 때문에 X가 O보다 2개 이상이면 안됨
            if(diff < 0 || diff >= 2) answer = "invalid";
            else if(!checkGame(xCnt, oCnt)) answer = "invalid";

            System.out.println(answer);
        }
    }

    public static boolean checkGame(int xCnt, int oCnt) {
        boolean xWin = false, oWin = false;
        // 가로, 세로 검사
        for(int i = 0; i < 3; i++) {
            // 가로
            if(map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                if(map[i][0] == 'X') xWin = true;
                else if(map[i][0] == 'O') oWin = true;
            }
            // 세로
            if(map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                if(map[0][i] == 'X') xWin = true;
                else if(map[0][i] == 'O') oWin = true;
            }
        }

        // 대각선
        if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            if(map[0][0] == 'X') xWin = true;
            else if(map[0][0] == 'O') oWin = true;
        }
        if(map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            if(map[0][2] == 'X') xWin = true;
            else if(map[0][2] == 'O') oWin = true;
        }

        // X가 이겼을 때는 O의 개수는 X보다 1개 적어야함
        if(xWin && !oWin) {
            if(xCnt - oCnt == 1) return true;
        }

        // O가 이겼을 때는 개수가 같아야함 (경기가 즉시 끝나야하는데 X의 개수가 더 많으면 이어서 말을 놓은 것임)
        if(!xWin && oWin) {
            if(xCnt == oCnt) return true;
        }

        // 아무도 이기지 않았는데 빈칸이 있으면 안됨
        if(!xWin && !oWin) {
            if(xCnt + oCnt == 9) return true;
        }

        return false;
    }
}
