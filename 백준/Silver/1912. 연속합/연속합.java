import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = arr[0];
        int max = dp[0];
        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], arr[i]+dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
