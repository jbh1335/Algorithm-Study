import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = arr[0];
        int max = dp[0];
        for(int i = 1; i < N; i++) {
            dp[i] = arr[i];
            for(int j = i-1; j >= 0; j--) {
                if(arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j]+arr[i]);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
