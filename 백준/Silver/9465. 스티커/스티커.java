import java.io.*;
import java.util.*;

/*
    1 2 3
    4 5 6
    0행 스티커를 고를 때는 오른쪽 아래, 오른쪽오른쪽 아래 대각선을 확인하면 된다.
    ex) 1을 선택했을 경우 5+3, 6 중에 더 큰 값을 선택하면 된다.
    반대로 1행 스티커를 고를 때는 오른쪽 위, 오른쪽오른쪽 위 대각선을 확인하면 된다.
    ex) 4를 선택했을 경우 2+6, 3 중에 더 큰 값을 선택하면 된다.
    따라서 dp 배열에 현재 칸까지 오는데 선택한 스티커 최댓값을 저장하면서 넘어가면 된다.
    그래서 0행은 dp[1][j-1], dp[1][j-2] 중에 더 큰 값을 선택하고
          1행은 dp[0][j-1], dp[0][j-2] 중에 거 큰 값을 선택하면 된다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n+1];

            for(int i = 0 ; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= n; j ++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 크기가 1이면 둘 중 더 큰 스티커 선택
            if(n == 1) {
                System.out.println(Math.max(arr[0][1], arr[1][1]));
                continue;
            }

            int[][] dp = new int[2][n+1];
            // 첫번째 값은 그대로
            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];
            for(int j = 2; j <= n; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
