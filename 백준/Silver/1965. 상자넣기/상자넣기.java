import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int answer = 1;
        int[] dp = new int[max+1];
        for(int i = 0; i < N; i++) {
            int num = arr[i];
            for(int j = num-1; j >= 0; j--) {
                dp[num] = Math.max(dp[num], dp[j]+1);
                answer = Math.max(answer, dp[num]);
            }
        }

        System.out.println(answer);
    }
}
