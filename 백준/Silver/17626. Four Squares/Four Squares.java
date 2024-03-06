import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            int sqrt = (int) Math.sqrt(i);
            int min = 5;

            for(int j = 1; j <= sqrt; j++) {
                int num = i - (j * j);
                min = Math.min(min, 1 + dp[num]);
            }
            dp[i] = min;
        }

        System.out.println(dp[n]);
    }
}
