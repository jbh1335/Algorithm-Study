import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N+1];

        for(int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        if(N == 1) answer = stair[1];
        else if(N == 2) answer = stair[1] + stair[2];
        else {
            int[] dp = new int[N+1];
            dp[1] = stair[1];
            dp[2] = stair[1] + stair[2];
            for(int i = 3; i <= N; i++) {
                dp[i] = Math.max(stair[i]+stair[i-1]+dp[i-3], stair[i]+dp[i-2]);
            }
            answer = dp[N];
        }

        System.out.println(answer);
    }
}
