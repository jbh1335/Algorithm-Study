import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];
        dp[0][0] = arr[0][0];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(i == 0 && j == 0) continue;

                if(i == 0) dp[i][j] = dp[i][j-1] + arr[i][j];
                else if(j == 0) dp[i][j] = dp[i-1][j] + arr[i][j];
                else dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1])) + arr[i][j];
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}
