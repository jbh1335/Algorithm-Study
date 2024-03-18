import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N+1][3];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][3];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    if(j == k) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k]+arr[i][j]);
                }
                if(i == N) answer = Math.min(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }
}
