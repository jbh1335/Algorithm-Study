import java.io.*;

public class Main {
    static int k, n;
    static String arrive;
    static char[] upLine, downLine;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        arrive = br.readLine();

        map = new char[n][k-1];
        upLine = new char[k];
        downLine = new char[k];
        int targetLine = 0;
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < k-1; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '?') targetLine = i;
            }
        }

        moveDown(targetLine);
        moveUp(targetLine);

        StringBuilder sb = new StringBuilder();
        boolean isAble = true;
        for(int i = 0; i < k-1; i++) {
            if(upLine[i] == downLine[i]) sb.append("*");
            else {
                if(upLine[i+1] == downLine[i]) {
                    sb.append("-");
                    upLine[i+1] = upLine[i];
                } else {
                   isAble = false;
                   break;
                }
            }
        }

        if(isAble) System.out.println(sb);
        else {
            for(int i = 0; i < k-1; i++) {
                System.out.print("x");
            }
        }
    }

    // 목표 지점까지 아래로 사다리 타기
    public static void moveDown(int targetLine) {
        for(int j = 0; j < k; j++) {
            // 열
            int idx = j;

            for(int i = 0; i < targetLine; i++) {
                // 선을 만나면 오른쪽 열로 이동
                if(idx < k-1 && map[i][idx] == '-') idx++;
                // 왼쪽 열로 이동
                else if(idx-1 >= 0 && map[i][idx-1] == '-') idx--;
            }

            upLine[idx] = (char) ('A' + j);
        }
    }

    // 목표 지점까지 위로 사다리 타기
    public static void moveUp(int targetLine) {
        for(int j = 0; j < k; j++) {
            // 열
            int idx = j;

            for(int i = n-1; i > targetLine; i--) {
                if(idx < k-1 && map[i][idx] == '-') idx++;
                else if(idx-1 >= 0 && map[i][idx-1] == '-') idx--;
            }

            downLine[idx] = arrive.charAt(j);
        }
    }
}
