import java.io.*;
import java.util.*;

/*
    1 ~ 25까지의 자연수로 이루어진 5*5 빙고가 있다.
    사회자가 몇 번째 수를 부른 후 빙고를 외치게 되는지 출력
    (빙고: 3개 이상의 선을 그으면 끝)
 */
public class Main {
    static int[][] bingo;
    static int[] numberOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        bingo = new int[5][5]; // 철수의 빙고판을 저장한 배열
        numberOrder = new int[25]; // 사회자가 부르는 수를 저장한 배열
        int answer = 0;

        // 철수의 빙고판 저장
        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 사회자가 부르는 수 저장
        int idx = 0;
        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                numberOrder[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 25; i++) {
            // 숫자 제거
            deleteNumber(numberOrder[i]);
            // 빙고판 검사
            if(checkBingo()) {
                answer = i+1;
                break;
            }
        }

        System.out.println(answer);
    }

    // 숫자 제거 -> 사회자가 부른 숫자는 0으로 만들기
    public static void deleteNumber(int num) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(bingo[i][j] == num) {
                    bingo[i][j] = 0;
                    return;
                }
            }
        }
    }

    // 빙고 찾기
    public static boolean checkBingo() {
        int lineNum = 0;
        for(int i = 0; i < 5; i++) {
            int count = 0;
            // 가로
            for(int j = 0; j < 5; j++) {
                // 사회자가 부른 숫자라면
                if(bingo[i][j] == 0) count++;
            }

            if(count == 5) lineNum++;
            if(lineNum >= 3) return true;

            count = 0;
            // 세로
            for(int j = 0; j < 5; j++) {
                // 사회자가 부른 숫자라면
                if(bingo[j][i] == 0) count++;
            }

            if(count == 5) lineNum++;
            if(lineNum >= 3) return true;
        }

        // 대각선
        if(bingo[0][0] == 0 && bingo[1][1] == 0 && bingo[2][2] == 0 && bingo[3][3] == 0 && bingo[4][4] == 0) lineNum++;
        if(bingo[0][4] == 0 && bingo[1][3] == 0 && bingo[2][2] == 0 && bingo[3][1] == 0 && bingo[4][0] == 0) lineNum++;

        if(lineNum >= 3) return true;
        return false;
    }
}
