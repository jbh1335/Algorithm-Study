import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + 1;
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
        }
        System.out.println(dp[N]);

        StringBuilder sb = new StringBuilder();
        int num = N;
        while(num > 0) {
            sb.append(num + " ");
            if(num % 3 == 0 && dp[num] == dp[num/3]+1) num /= 3;
            else if(num % 2 == 0 && dp[num] == dp[num/2]+1) num /= 2;
            else num--;
        }
        System.out.println(sb);
    }
}
