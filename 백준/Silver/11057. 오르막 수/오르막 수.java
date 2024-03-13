import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 누적합
        long[] sArr = new long[11];
        for(int i = 1; i <= 10; i++) {
            sArr[i] = i;
        }

        int[] dp = new int[N+1];
        dp[1] = 10;
        for(int i = 2; i <= N; i++) {
            for(int j = 1; j <= 10; j++) {
                sArr[j] = (sArr[j-1] + sArr[j]) % 10007;
            }
            dp[i] = (int) (sArr[10] % 10007);
        }

        System.out.println(dp[N]);
    }
}
