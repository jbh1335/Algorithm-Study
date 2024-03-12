import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        dp[1] = arr[1];
        for(int i = 2; i <= N; i++) {
            int max = arr[i];
            for(int j = 1; j < i; j++) {
                max = Math.max(max, dp[j]+arr[i-j]);
            }
            dp[i] = max;
        }
        System.out.println(dp[N]);
    }
}
