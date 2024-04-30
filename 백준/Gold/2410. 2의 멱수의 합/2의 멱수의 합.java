import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        dp[0] = 1;
        for(int i = 1; i <= N; i*=2) {
            for(int j = i; j <= N; j++) {
                dp[j] = (dp[j] + dp[j-i]) % 1000000000;
            }
        }
        System.out.println(dp[N]);
    }
}
