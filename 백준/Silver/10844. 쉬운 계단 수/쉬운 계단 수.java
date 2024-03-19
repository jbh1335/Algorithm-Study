import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // i번째 수(자릿수)가 j인 수의 개수
        long[][] dp = new long[N+1][10];
        long answer = 0;
        // 1번째 자리가 j인 수의 개수 저장 -> 1개
        for(int j = 1; j < 10; j++) {
            dp[1][j] = 1;
        }
        if(N >= 2) {
            for(int i = 2; i <= N; i++) {
                dp[i][0] = dp[i-1][1];
                for(int j = 1; j <= 8; j++) {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
                }
                dp[i][9] = dp[i-1][8];
            }
        }

        for(int j = 0; j < 10; j++) {
            answer = (answer + dp[N][j]) % 1000000000;
        }
        System.out.println(answer);
    }
}
